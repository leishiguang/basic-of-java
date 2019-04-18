package com.coderay.netty.concurrent;

/**
 * 线程启动器
 * date 2019/04/07
 *
 * @author leishiguang
 */
public interface EventExecutor extends EventExecutorGroup {


    /**
     * Returns a reference to itself.
     */
    EventExecutor next();

    /**
     * Return the {@link EventExecutorGroup} which is the parent of this {@link EventExecutor},
     */
    EventExecutorGroup parent();

    /**
     * Calls {@link #inEventLoop(Thread)} with {@link Thread#currentThread()} as argument
     */
    boolean inEventLoop();

    /**
     * Return {@code true} if the given {@link Thread} is executed in the event loop,
     * {@code false} otherwise.
     */
    boolean inEventLoop(Thread thread);


}
