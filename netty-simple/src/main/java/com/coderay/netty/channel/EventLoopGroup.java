package com.coderay.netty.channel;

import com.coderay.netty.concurrent.EventExecutorGroup;

/**
 * 轮询事件的线程组
 * <p>
 * date 2019/04/07
 *
 * @author leishiguang
 */
public interface EventLoopGroup extends EventExecutorGroup {

    @Override
    EventLoop next();

    //todo
    Object register();
}
