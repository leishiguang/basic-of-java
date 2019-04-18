package com.coderay.netty.channel.nio;



import com.coderay.netty.channel.EventLoop;

import java.util.concurrent.Executor;

/**
 * 初始化线程组
 */
public class NioEventLoopGroup extends MultithreadEventLoopGroup {

    public NioEventLoopGroup(int nThreads) {
        this(nThreads, (Executor) null);
    }

    public NioEventLoopGroup(int nThreads, Executor executor) {
        super(nThreads, executor);
    }

    @Override //不断创建新的NioEventLoop，其中的 executor 是为了帮助我们创建线程，本身 NioEventLoop 是没有线程的
    protected EventLoop newChild(Executor executor) throws Exception {
        return new NioEventLoop(this, executor);
    }
}
