package demo.concurrent.lock;

import demo.concurrent.lock.aqs.AbstractQueuedSynchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独享锁的实现
 *
 * @author leishiguang
 * @since v1.0
 */
public class ExclusiveLock implements Lock {

    private AbstractQueuedSynchronizer myAqs = new AbstractQueuedSynchronizer() {
        @Override
        public boolean tryAcquire() {
            //如果 owner 为空，就可以写入
            return owner.compareAndSet(null,Thread.currentThread());
        }


        @Override
        public boolean tryRelease() {
            // 这儿如果是当前线程就直接释放，可重入的情况下，要判断资源的占用情况（state 字段保存了资源的占用次数）
            return owner.compareAndSet(Thread.currentThread(),null);
        }
    };

    @Override
    public void lock() {
        myAqs.acquire();
    }

    @Override
    public boolean tryLock() {
        return myAqs.tryAcquire();
    }

    @Override
    public void unlock() {
        myAqs.release();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("当前独享锁未实现该方法");
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit){
        throw new UnsupportedOperationException("当前独享锁未实现该方法");
    }


    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("当前独享锁未实现该方法");
    }
}
