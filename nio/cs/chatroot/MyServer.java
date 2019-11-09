package nio.cs.chatroot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * NIO非阻塞服务器
 */
public class MyServer {
    //定义编码和解码的字符集对象
    private static Charset charset = StandardCharsets.UTF_8;

    private static Selector selector;

    public static void main(String[] args) throws IOException {
        startServer();
    }

    /**
     * 启动聊天室服务器
     * @throws IOException
     */
    public static void startServer () throws IOException {
        //1、运用ServerSocketChannel.open()创建一个ServerSocketChannel对象
        ServerSocketChannel server = ServerSocketChannel.open();
        //2、给ServerSocketChannel绑定一个IP地址和端口号
        InetSocketAddress address = new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT);
        server.bind(address);
        //3、设置ServerSocketChannel为设置非阻塞模式
        server.configureBlocking(false);
        //4、运用Selector.open()方法获取Selector对象
        selector = Selector.open();
        //5、把ServerSocketChannel对象注册到Selector
        server.register(selector, SelectionKey.OP_ACCEPT);
        //6、调用selector.select()方法，获取可用IO的数量
        // 如果所有注册的Channel都需要处理的IO操作时，将会一直阻塞
        while (selector.select() > 0) {
            //7、处理需要IO操作
            solution(server);
        }
    }

    private static void solution(ServerSocketChannel server) throws IOException {
        //1、遍历selector
        for (SelectionKey sk : selector.selectedKeys()) {
            //2、把将要处理的SelectionKey从selector中移除
            selector.selectedKeys().remove(sk);
            //3、判断sk对应的Channel是否包含客户端的连接请求
            if (sk.isAcceptable()) {
                //调用accept方法接受连接，产生服务端的SocketChannel
                SocketChannel socketChannel = server.accept();
                //设置采用非阻塞模式
                socketChannel.configureBlocking(false);
                //将SocketChannel也注册到Selector中
                socketChannel.register(selector, SelectionKey.OP_READ);
                //将sk对应的Channel设置成准备接受其他请求；
                sk.interestOps(SelectionKey.OP_ACCEPT);
            }
            //如果sk对应的Channel有数据需要读取
            if (sk.isReadable()) {
                //获取该SelectionKey对应的Channel，该Channel中有可读的数据
                SocketChannel socketChannel = (SocketChannel)sk.channel();
                //定义准备执行读取数据的ByteBuffer
                ByteBuffer buff = ByteBuffer.allocate(1024);
                StringBuffer content = new StringBuffer("");
                //开始读取数据
                try {
                    while (socketChannel.read(buff) > 0) {
                        buff.flip();
                        content.append(charset.decode(buff));
                    }
                    //打印从该sk对应的Channel里读取到的数据
//                    System.out.println("读取的数据 = " + content);
                    //将sk对应的Channel设置成准备下一次读取
                    sk.interestOps(SelectionKey.OP_READ);
                }
                //如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                //对应的Client出现了问题，所以从Selector中取消sk的注册
                catch (IOException ioe) {
                    sk.cancel();
                    if (sk.channel() != null) {
                        sk.channel().close();
                    }
                }
                //如果content的长度大于0，即聊天信息不为空
                if (content.length() > 0) {
                    //遍历该Selector里注册的所有SelectionKey
                    for (SelectionKey key : selector.keys()) {
                        //获取该key对应的Channel
                        SelectableChannel targetChannel = key.channel();
                        //如果该Channel是SocketChannel对应
                        if (targetChannel instanceof SocketChannel) {
                            //将读到的内容写入该Channel中
                            SocketChannel dest = (SocketChannel)targetChannel;
                            dest.write(charset.encode(content.toString()));
                        }
                    }
                }
            }
        }
    }
}
