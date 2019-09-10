package demo.concurrent.lock;

import demo.concurrent.lock.aqs.AbstractQueuedSynchronizer;

/**
 * 栅栏的实现
 *
 * @author leishiguang
 * @since v1.0
 */
public class CountDownLatch {

    private AbstractQueuedSynchronizer myAqs = new AbstractQueuedSynchronizer(){
        @Override
        public int tryAcquireShared() {
            return this.getState().get() == 0 ? 1 : -1;
        }

        @Override
        public boolean tryReleaseShared() {
            return this.getState().decrementAndGet() == 0;
        }
    };

    /**
     * 计数多少
     */
    public CountDownLatch(int count){
        myAqs.getState().set(count);
    }

    /**
     * 阻塞，直到计数为 0
     */
    public void await(){
        myAqs.acquireShared();
    }

    /**
     * 进行一次计数
     */
    public void countDown(){
        myAqs.releaseShared();
    }
}
