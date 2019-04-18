package com.coderay.netty.concurrent;


import java.util.concurrent.Future;

/**
 * Special {@link ProgressiveFuture} which is writable.
 */
public interface ProgressivePromise<V> extends Promise<V>, ProgressiveFuture<V> {

    /**
     * Sets the current progress of the operation and notifies the listeners that implement
     * {@link GenericProgressiveFutureListener}.
     */
    ProgressivePromise<V> setProgress(long progress, long total);

    /**
     * Tries to set the current progress of the operation and notifies the listeners that implement
     * {@link GenericProgressiveFutureListener}.  If the operation is already complete or the progress is out of range,
     * this method does nothing but returning {@code false}.
     */
    boolean tryProgress(long progress, long total);

    @Override
    ProgressivePromise<V> setSuccess(V result);

    @Override
    ProgressivePromise<V> setFailure(Throwable cause);

    @Override
    ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> listener);

    @Override
    ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... listeners);

    @Override
    ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> listener);

    @Override
    ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... listeners);

    @Override
    ProgressivePromise<V> await() throws InterruptedException;

    @Override
    ProgressivePromise<V> awaitUninterruptibly();

    @Override
    ProgressivePromise<V> sync() throws InterruptedException;

    @Override
    ProgressivePromise<V> syncUninterruptibly();
}