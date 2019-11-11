package com.syh.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义类加载器
 */
public class CompileClassLoader extends ClassLoader{

    private byte[] getBytes (String filename) {
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int)len];

        try (
                FileInputStream fis = new FileInputStream(file);
                ) {
            int r = fis.read(raw);
            if (r != len) {
                throw new IOException("无法读取全部文件：" + r + " != " + len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raw;
    }
    //定义编译指定Java文件的方法
    private boolean compile (String javaFile) throws IOException {
        System.out.println("CompileClassLoader:正在编译 " + javaFile + "...");
        Process p = Runtime.getRuntime().exec("javac" + javaFile);
        try {
            //其他线程都等待这个线程完成
            p.waitFor();
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }
        //获取javac线程的退出值
        int ret = p.exitValue();
        //返回编译是否成功
        return ret == 0;
    }

    //重写ClassLoader的findClass方法
    @Override
    protected Class<?> findClass (String name) throws ClassNotFoundException{
        Class clazz = null;
        //将包路径中的点替换成斜线
        String fileStub = name.replace(".", "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);
        //当指定Java源文件存在，且Class文件不存在，或者Java
        // 源文件的修改时间比Class文件的修改时间更晚时，重新编译
        if (javaFile.exists() && (!classFile.exists())
            || javaFile.lastModified() > classFile.lastModified()) {
            //如果编译失败，或者该Class文件不存在
            try {
                if (!compile(javaFilename) || !classFile.exists()) {
                    throw new ClassNotFoundException("ClassNotFoundException:" + javaFilename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //如果Class文件存在，系统负责将文件转换成Class对象
        if (classFile.exists()) {
            byte[] raw = getBytes(classFilename);
            clazz = defineClass(name, raw, 0, raw.length);
        }
        //如果clazz为null，表名加载失败，则抛出异常
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    //定义一个主方法
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (args.length < 1) {
            System.out.println("缺少目标类，请按如下格式运行Java源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        //第一个参数是需要运行的类
        String progClass = args[0];
        //剩下的参数将作为运行目标类时的参数，将这些参数复制到一个新数组中
        String[] proArgs = new String[args.length - 1];
        System.arraycopy(args, 1, proArgs, 0, proArgs.length);
        CompileClassLoader ccl = new CompileClassLoader();
        //加载需要运行的类
        Class<?> clazz = ccl.loadClass(progClass);
        //获取需要运行的类和主方法
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object[] argsArray = {proArgs};
        main.invoke(null, argsArray);
    }
}
