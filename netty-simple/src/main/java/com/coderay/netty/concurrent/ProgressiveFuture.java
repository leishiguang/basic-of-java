package com.coderay.netty.concurrent;

import java.util.concurrent.Future;

/**
 * A {@link Future} which is used to indicate the progress of an operation.
 */
public interface ProgressiveFuture<V> extends Future<V> {


    ProgressiveFuture<V> addListener(GenericFutureListener<? extends Future<? super V>> listener);


    ProgressiveFuture<V> addListeners(GenericFutureListener<? extends Future<? super V>>... listeners);


    ProgressiveFuture<V> removeListener(GenericFutureListener<? extends Future<? super V>> listener);


    ProgressiveFuture<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... listeners);


    ProgressiveFuture<V> sync() throws InterruptedException;


    ProgressiveFuture<V> syncUninterruptibly();


    ProgressiveFuture<V> await() throws InterruptedException;


    ProgressiveFuture<V> awaitUninterruptibly();
}
