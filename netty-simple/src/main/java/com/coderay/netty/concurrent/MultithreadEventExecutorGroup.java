package com.coderay.netty.concurrent;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo: DESCRIPTION
 * date 2019/04/07
 *
 * @author leishiguang
 */
public abstract class MultithreadEventExecutorGroup extends AbstractEventExecutorGroup {

    private final EventExecutor[] children;
    private final Set<EventExecutor> readonlyChildren;
    private final AtomicInteger terminatedChildren = new AtomicInteger();

    protected MultithreadEventExecutorGroup(int nThreads, Executor executor) {
        //todo 在这里初始化一个又一个的 EventExecutor
        children = new EventExecutor[nThreads];//这儿就准备开始创建 Evenloop 线程

        Set<EventExecutor> childrenSet = new LinkedHashSet<EventExecutor>(children.length);
        Collections.addAll(childrenSet, children);
        readonlyChildren = Collections.unmodifiableSet(childrenSet);

    }

    protected abstract EventExecutor newChild(Executor executor) throws Exception;


    @Override
    public EventExecutor next() {
        return this.chooserNext();
        //return chooser.next();
    }

    private EventExecutor chooserNext() {
        //todo 用 chooser 接口实现
        return null;
    }

    @Override
    public Iterator<EventExecutor> iterator() {
        return readonlyChildren.iterator();
    }

    /**
     * Return the number of {@link EventExecutor} this implementation uses. This number is the maps
     * 1:1 to the threads it use.
     */
    public final int executorCount() {
        return children.length;
    }

    /**
     * Create a new EventExecutor which will later then accessible via the {@link #next()}  method. This method will be
     * called for each thread that will serve this {@link MultithreadEventExecutorGroup}.
     *
     */
    protected abstract EventExecutor newChild(Executor executor, Object... args) throws Exception;

    @Override
    public Future<?> shutdownGracefully() {
        for (EventExecutor l: children) {
            l.shutdownGracefully();
        }
        return terminationFuture();
    }

    public Future<?> terminationFuture() {
        return null;
    }

    @Override
    @Deprecated
    public void shutdown() {
        for (EventExecutor l: children) {
            l.shutdown();
        }
    }

    @Override
    public boolean isShuttingDown() {
        for (EventExecutor l: children) {
            if (!l.isShuttingDown()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isShutdown() {
        for (EventExecutor l: children) {
            if (!l.isShutdown()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isTerminated() {
        for (EventExecutor l: children) {
            if (!l.isTerminated()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        long deadline = System.nanoTime() + unit.toNanos(timeout);
        loop: for (EventExecutor l: children) {
            for (;;) {
                long timeLeft = deadline - System.nanoTime();
                if (timeLeft <= 0) {
                    break loop;
                }
                if (l.awaitTermination(timeLeft, TimeUnit.NANOSECONDS)) {
                    break;
                }
            }
        }
        return isTerminated();
    }

}
