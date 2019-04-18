package com.coderay.netty.concurrent;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Abstract base class for {@link EventExecutor}s that want to support scheduling.
 */
public abstract class AbstractScheduledEventExecutor extends AbstractEventExecutor {


    PriorityQueue scheduledTaskQueue;

    protected AbstractScheduledEventExecutor() {
    }

    protected AbstractScheduledEventExecutor(EventExecutorGroup parent) {
        super(parent);
    }

    protected static long nanoTime() {
        throw new UnsupportedOperationException();
    }

    PriorityQueue scheduledTaskQueue() {throw new UnsupportedOperationException();
    }

    private static boolean isNullOrEmpty(Queue queue) {
        return queue == null || queue.isEmpty();
    }

    /**
     * Cancel all scheduled tasks.
     *
     * This method MUST be called only when {@link #inEventLoop()} is {@code true}.
     */
    protected void cancelScheduledTasks() {
        assert inEventLoop();
        PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        if (isNullOrEmpty(scheduledTaskQueue)) {
            return;
        }

        final ScheduledFutureTask<?>[] scheduledTasks =
                scheduledTaskQueue.toArray(new ScheduledFutureTask<?>[0]);

        for (ScheduledFutureTask<?> task: scheduledTasks) {
            task.cancelWithoutRemove(false);
        }

        scheduledTaskQueue.clearIgnoringIndexes();
    }

    /**
     * @see #pollScheduledTask(long)
     */
    protected final Runnable pollScheduledTask() {
        return pollScheduledTask(nanoTime());
    }

    /**
     * Return the {@link Runnable} which is ready to be executed with the given {@code nanoTime}.
     * You should use {@link #nanoTime()} to retrieve the correct {@code nanoTime}.
     */
    protected final Runnable pollScheduledTask(long nanoTime) {
        assert inEventLoop();

        Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        ScheduledFutureTask<?> scheduledTask = scheduledTaskQueue == null ? null : scheduledTaskQueue.peek();
        if (scheduledTask == null) {
            return null;
        }

        if (scheduledTask.deadlineNanos() <= nanoTime) {
            scheduledTaskQueue.remove();
            return scheduledTask;
        }
        return null;
    }

    /**
     * Return the nanoseconds when the next scheduled task is ready to be run or {@code -1} if no task is scheduled.
     */
    protected final long nextScheduledTaskNano() {
        Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        ScheduledFutureTask<?> scheduledTask = scheduledTaskQueue == null ? null : scheduledTaskQueue.peek();
        if (scheduledTask == null) {
            return -1;
        }
        return Math.max(0, scheduledTask.deadlineNanos() - nanoTime());
    }

    final ScheduledFutureTask<?> peekScheduledTask() {
        Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        if (scheduledTaskQueue == null) {
            return null;
        }
        return scheduledTaskQueue.peek();
    }

    /**
     * Returns {@code true} if a scheduled task is ready for processing.
     */
    protected final boolean hasScheduledTasks() {
        Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        ScheduledFutureTask<?> scheduledTask = scheduledTaskQueue == null ? null : scheduledTaskQueue.peek();
        return scheduledTask != null && scheduledTask.deadlineNanos() <= nanoTime();
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(unit, "unit");
        if (delay < 0) {
            delay = 0;
        }
        validateScheduled0(delay, unit);

        return schedule(new ScheduledFutureTask<Void>(
                this, command, null, ScheduledFutureTask.deadlineNanos(unit.toNanos(delay))));
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        ObjectUtil.checkNotNull(callable, "callable");
        ObjectUtil.checkNotNull(unit, "unit");
        if (delay < 0) {
            delay = 0;
        }
        validateScheduled0(delay, unit);

        return schedule(new ScheduledFutureTask<V>(
                this, callable, ScheduledFutureTask.deadlineNanos(unit.toNanos(delay))));
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(unit, "unit");
        if (initialDelay < 0) {
            throw new IllegalArgumentException(
                    String.format("initialDelay: %d (expected: >= 0)", initialDelay));
        }
        if (period <= 0) {
            throw new IllegalArgumentException(
                    String.format("period: %d (expected: > 0)", period));
        }
        validateScheduled0(initialDelay, unit);
        validateScheduled0(period, unit);

        return schedule(new ScheduledFutureTask<Void>(
                this, Executors.<Void>callable(command, null),
                ScheduledFutureTask.deadlineNanos(unit.toNanos(initialDelay)), unit.toNanos(period)));
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(unit, "unit");
        if (initialDelay < 0) {
            throw new IllegalArgumentException(
                    String.format("initialDelay: %d (expected: >= 0)", initialDelay));
        }
        if (delay <= 0) {
            throw new IllegalArgumentException(
                    String.format("delay: %d (expected: > 0)", delay));
        }

        validateScheduled0(initialDelay, unit);
        validateScheduled0(delay, unit);

        return schedule(new ScheduledFutureTask<Void>(
                this, Executors.<Void>callable(command, null),
                ScheduledFutureTask.deadlineNanos(unit.toNanos(initialDelay)), -unit.toNanos(delay)));
    }

    @SuppressWarnings("deprecation")
    private void validateScheduled0(long amount, TimeUnit unit) {
        validateScheduled(amount, unit);
    }

    /**
     * Sub-classes may override this to restrict the maximal amount of time someone can use to schedule a task.
     *
     * @deprecated will be removed in the future.
     */
    @Deprecated
    protected void validateScheduled(long amount, TimeUnit unit) {
        // NOOP
    }

    <V> ScheduledFuture<V> schedule(final ScheduledFutureTask<V> task) {
        if (inEventLoop()) {
            scheduledTaskQueue().add(task);
        } else {
            execute(new Runnable() {
                @Override
                public void run() {
                    scheduledTaskQueue().add(task);
                }
            });
        }

        return task;
    }

    final void removeScheduled(final ScheduledFutureTask<?> task) {
        if (inEventLoop()) {
            scheduledTaskQueue().removeTyped(task);
        } else {
            execute(new Runnable() {
                @Override
                public void run() {
                    removeScheduled(task);
                }
            });
        }
    }
}