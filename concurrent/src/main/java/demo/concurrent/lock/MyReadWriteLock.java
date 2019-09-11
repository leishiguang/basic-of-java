package demo.concurrent.lock;

import demo.concurrent.lock.aqs.AbstractQueuedSynchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 读写锁的实现
 *
 * @author leishiguang
 * @since v1.0
 */
public class MyReadWriteLock implements ReadWriteLock {

    private AbstractQueuedSynchronizer myAqs = new AbstractQueuedSynchronizer(){

        /**
         * 加写锁
         */
        @Override
        public boolean tryAcquire() {
            //读锁存在时，不允许写
            if(myAqs.getState().get() != 0){
                return false;
            }else{
                return owner.compareAndSet(null,Thread.currentThread());
            }
        }

        /**
         * 加读锁
         */
        @Override
        public int tryAcquireShared() {
            //如果当前有线程占用了写锁，则不允许再加锁，除非是同一个线程
            if (owner.get() != null && !owner.get().equals(Thread.currentThread())) {
                return -1;
            }else {
                return myAqs.getState().incrementAndGet();
            }
        }

        /**
         * 释放写锁
         */
        @Override
        public boolean tryRelease() {
            return owner.compareAndSet(Thread.currentThread(),null);
        }

        /**
         * 释放读锁
         */
        @Override
        public boolean tryReleaseShared() {
            return myAqs.getState().decrementAndGet() >= 0;
        }
    };


    @Override
    public Lock writeLock() {
        return new Lock() {
            @Override
            public void lock() {
                myAqs.acquire();
            }

            @Override
            public void unlock() {
                myAqs.release();
            }

            @Override
            public void lockInterruptibly(){
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean tryLock() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit){
                throw new UnsupportedOperationException();
            }


            @Override
            public Condition newCondition() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Lock readLock() {
        return new Lock() {
            @Override
            public void lock() {
                myAqs.acquireShared();
            }

            @Override
            public void unlock() {
                myAqs.releaseShared();
            }

            @Override
            public void lockInterruptibly() throws InterruptedException {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean tryLock() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                throw new UnsupportedOperationException();
            }


            @Override
            public Condition newCondition() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
