package com.coderay.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO selector 多路复用reactor线程模型
 */
public class NioServerV3 {

    /**
     * 处理业务操作的线程
     */
    private static ExecutorService workPool = Executors.newCachedThreadPool();

    /**
     * 服务的channel
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * 1、创建多个线程 - accept处理reactor线程 (accept线程)
     */
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];

    /**
     * 2、创建多个线程 - io处理reactor线程  (I/O线程)
     */
    private ReactorThread[] subReactorThreads = new ReactorThread[8];

    /**
     * 初始化线程组
     */
    private void initThreadGroup() throws Exception {
        for(int i = 0; i< subReactorThreads.length; i++){
            subReactorThreads[i] = new SubReactorThread(workPool);
        }
        for(int i = 0; i< mainReactorThreads.length; i++){
            mainReactorThreads[i] = new MainReactorThread(subReactorThreads);
        }
    }

    /**
     * 初始化 channel，并绑定一个 eventLoop 线程
     */
    private void initChannelAndRegister() throws Exception{
        // 1、 创建ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 2、 将serverSocketChannel注册到selector
        int index = new Random().nextInt(mainReactorThreads.length);
        mainReactorThreads[index].doStart();
        SelectionKey selectionKey = mainReactorThreads[index].register(serverSocketChannel);
        // 只把接受连接的事件，绑定给 main 线程。
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }

    /**
     * 正式绑定端口，开始对外服务
     */
    private void starup() throws IOException{
        serverSocketChannel.bind(new InetSocketAddress(8080));
        System.out.println("启动完成，端口8080");
    }

    public static void main(String[] args) throws Exception{
        NioServerV3 nioServer = new NioServerV3();
        //创建 main 和 sub 两个线程组
        nioServer.initThreadGroup();
        //创建 serverSocketChannel，并注册到 mainReactor 线程上的 selector 上
        nioServer.initChannelAndRegister();
        //绑定端口并开始服务
        nioServer.starup();
    }
}
