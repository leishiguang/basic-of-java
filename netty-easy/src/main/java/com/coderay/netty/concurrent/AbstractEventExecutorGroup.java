package com.coderay.netty.concurrent;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import java.util.function.Consumer;

/**
 * todo: DESCRIPTION
 * date 2019/04/07
 *
 * @author leishiguang
 */
public abstract class AbstractEventExecutorGroup implements  EventExecutorGroup {
    public boolean isShuttingDown() {
        return false;
    }

    public Future<?> shutdownGracefully() {
        return null;
    }

    public EventExecutor next() {
        return null;
    }

    public Iterator<EventExecutor> iterator() {
        return null;
    }

    public void forEach(Consumer<? super EventExecutor> action) {

    }

    public Spliterator<EventExecutor> spliterator() {
        return null;
    }

    public Future<?> submit(Runnable task) {
        return null;
    }

    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }
}
