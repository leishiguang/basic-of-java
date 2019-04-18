package com.coderay.netty.nio;

import com.coderay.netty.concurrent.EventExecutor;

/**
 * Will handle all the I/O operations for a {@link Channel} once registered.
 *
 * One {@link EventLoop} instance will usually handle more than one {@link Channel} but this may depend on
 * implementation details and internals.
 *
 */
public interface EventLoop extends EventExecutor,EventLoopGroup{
    EventLoopGroup parent();
}
