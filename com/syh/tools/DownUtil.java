package com.syh.tools;

import com.syh.multithreading.downloading.Down;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载工具类
 */
public class DownUtil {
    /**
     * 下载路径
     */
    private String path;

    /**
     * 保存路径
     */
    private String savePath;


    /**
     * 使用多少个线程下载资源
     */
    private int threadNum;

    /**
     * 定义下载的线程对象
     */
    private DownThread[] threads;
    /**
     * 默认下载线程数
     */
    private final int initThreadNum = 10;
    public DownUtil() {
    }

    public DownUtil(String path, String savePath) {
        this.path = path;
        this.savePath = savePath;
        threadNum = initThreadNum;
        threads = new DownThread[threadNum];
    }

    public DownUtil(String path, String savePath, int threadNum) {
        this.path = path;
        this.savePath = savePath;
        this.threadNum = threadNum;
        threads = new DownThread[threadNum];
    }

    public void download () throws IOException {
        //根据path获取URL对象
        URL url = new URL(path);

        //获取URL连接的资源对象
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时时间
        conn.setConnectTimeout(5 * 1000);
        //设置conn请求的一些属性
        conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, " +
                "application/x-shockwave-flash, application/xaml+xml, " +
                "application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
                "application/x-ms-application, application/vnd.ms-excel," +
                "application/vnd.ms-powerpoint, application/msword, " +
                "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        //获取文件长度
        long contentLength = conn.getContentLengthLong();
        conn.disconnect();

        //分段
        long currentPartSize = contentLength / threadNum + 1;

        RandomAccessFile file = new RandomAccessFile(savePath, "rw");

        //设置本地文件的大小
        file.setLength(contentLength);
        file.close();

        for (int i = 0; i < threadNum; i++) {
            //计算每个线程下载的开始位置
            long startPos = i * currentPartSize;

            //每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(savePath, "rw");

            //定位该线程的下载位置
            currentPart.seek(startPos);

            //创建下载线程，并启动
            threads[i] = new DownThread(startPos, currentPartSize, currentPart);
            System.out.println("threads[i] = " + threads[i]);
            threads[i].start();
        }
    }
    private class DownThread extends Thread {
        //当前线程的下载位置
        private long startPos;
        //定义当前线程负责下载的文件大小
        private long currentPartSize;
        //当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        //定义该线程已下载的字节数
        private long length;

        public DownThread() {
        }

        public DownThread(long startPos, long currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
            System.out.println("1 = " + 1);
        }
        @Override
        public void run () {
            try {
                System.out.println("2 = " + 2);
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, " +
                        "application/x-shockwave-flash, application/xaml+xml, " +
                        "application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
                        "application/x-ms-application, application/vnd.ms-excel," +
                        "application/vnd.ms-powerpoint, application/msword, " +
                        "*/*");
                conn.setRequestProperty("Accept-Language", "zh-CN");
                conn.setRequestProperty("Charset", "UTF-8");

                InputStream is = conn.getInputStream();

                //跳过startPos字节，表名该线程只下载自己负责的那部分文件
                is.skip(this.startPos);
                byte[] buffer = new byte[10 * 1024];
                int readLength = 0;
                while (length < currentPartSize &&
                        (readLength = is.read(buffer)) != -1) {
                    currentPart.write(buffer, 0, readLength);
                    length += readLength;
                }
                currentPart.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public long getStartPos() {
            return startPos;
        }

        public void setStartPos(long startPos) {
            this.startPos = startPos;
        }

        public long getCurrentPartSize() {
            return currentPartSize;
        }

        public void setCurrentPartSize(long currentPartSize) {
            this.currentPartSize = currentPartSize;
        }

        public RandomAccessFile getCurrentPart() {
            return currentPart;
        }

        public void setCurrentPart(RandomAccessFile currentPart) {
            this.currentPart = currentPart;
        }

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }
}
