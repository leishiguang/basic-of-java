package com.coderay.netty.concurrent;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程启动器的集合
 * date 2019/04/07
 *
 * @author leishiguang
 */
public interface EventExecutorGroup extends ScheduledExecutorService,Iterable<EventExecutor>{

    boolean isShuttingDown();

    Future<?> shutdownGracefully();

    EventExecutor next();

    Iterator<EventExecutor> iterator();

    Future<?> submit(Runnable task);

    <T> Future<T> submit(Callable<T> task);

}
