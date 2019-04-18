package com.coderay.netty.nio;

import com.coderay.netty.concurrent.EventExecutorGroup;

/**
 * todo: DESCRIPTION
 * date 2019/04/07
 *
 * @author leishiguang
 */
public interface EventLoopGroup extends EventExecutorGroup {

    /**
     * Return the next {@link EventLoop} to use
     */
    EventLoop next();

    /**
     * 注册到自己的线程组里面
     */
    //ChannelFuture register(Channel channel);
    Object register(Channel channel);


}
