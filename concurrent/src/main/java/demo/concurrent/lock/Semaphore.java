package demo.concurrent.lock;

import demo.concurrent.lock.aqs.AbstractQueuedSynchronizer;

/**
 * 自定义信号量实现
 * 使用场景如：
 *   1. 限流
 *
 * @author leishiguang
 * @since v1.0
 */
public class Semaphore {

    private AbstractQueuedSynchronizer myAqs = new AbstractQueuedSynchronizer() {
        /**
         * 信号量的获取，数量-1
         */
        @Override
        public int tryAcquireShared() {
            while (true) {
                int count = getState().get();
                int n = count - 1;
                //资源不足
                if (count <= 0 || n < 0) {
                    return -1;
                }
                //争抢到资源了
                if (getState().compareAndSet(count, n)) {
                    return 1;
                }
            }
        }

        /**
         * 资源的释放
         */
        @Override
        public boolean tryReleaseShared() {
            //释放一个资源成功
            return getState().incrementAndGet() >= 0;
        }
    };

    /**
     * 设置许可数量
     */
    public Semaphore(int count){
        myAqs.getState().set(count);
    }

    /**
     * 获得令牌
     */
    public void acquire(){
        myAqs.acquireShared();
    }

    /**
     * 释放令牌
     */
    public void release(){
        myAqs.releaseShared();
    }

}
