package com.coderay.netty.channel.nio;

import com.coderay.netty.channel.EventLoop;
import com.coderay.netty.channel.EventLoopGroup;
import com.coderay.netty.concurrent.MultithreadEventExecutorGroup;

import java.util.concurrent.Executor;

/**
 * todo: DESCRIPTION
 * date 2019/04/07
 *
 * @author leishiguang
 */
public abstract class MultithreadEventLoopGroup extends MultithreadEventExecutorGroup implements EventLoopGroup {

    protected MultithreadEventLoopGroup(int nThreads, Executor executor) {
        super(nThreads,executor);
    }


        @Override
    public EventLoop next() {
        return (EventLoop) super.next();
    }

    @Override
    protected abstract EventLoop newChild(Executor executor) throws Exception;


    @Override
    public Object register() {
        return next().register();// Tony: 根据选择器，选择一个合适的NioEventLoop进行注册(SingleThreadEventLoop)
    }

    @Override
    protected abstract EventLoop newChild(Executor executor) throws Exception;

}
