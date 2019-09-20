package demo.concurrent.lock;

import demo.concurrent.lock.aqs.AbstractQueuedSynchronizer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环屏障(栅栏)的实现
 * 场景如：
 * 1. 数据库批量插入
 * 2. 游戏大厅 5 人组队
 * todo 未完成
 *
 * @author leishiguang
 * @since v1.0
 */
public class CyclicBarrier {

    private int parties;
    private Runnable barrierAction;

    private AbstractQueuedSynchronizer myAqs = new AbstractQueuedSynchronizer() {

        /**
         * state 初始为 parties，初次进来，一直为锁住的状态
         */
        @Override
        public int tryAcquireShared() {
            //执行计数
            myAqs.getState().incrementAndGet();
            //全部等待
            return 0;
        }

        @Override
        public boolean tryReleaseShared() {
            //未满员，不允许释放（通知其它线程）
            if((myAqs.getState().get() / parties) < 0){
                return false;
            }
            return myAqs.getState().incrementAndGet() >= parties;
        }
    };

    public CyclicBarrier(int parties, Runnable barrierAction) {
        this.parties = parties;
        this.barrierAction = barrierAction;
        this.myAqs.getState().set(parties);
    }

    /**
     * 等待栅栏打开
     */
    public void await() {
        //进来立马占有一个资源，锁住
        myAqs.acquireShared();
        if (checkFull()) {
            pass();
        }
    }

    /**
     * 判断是否满员了
     */
    private boolean checkFull() {
        return myAqs.getState().get() >= parties;
    }

    /**
     * 一次方向线程数
     */
    private void pass() {
        for (int i = 0; i < parties; i++) {
            myAqs.releaseShared();
        }
    }
}
