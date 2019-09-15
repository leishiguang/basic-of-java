## concurrent

多线程编程的一些基础，却十分关键的 tips，比如 AQS 抽象队列同步器，比如锁的实现，比如 map、list、tree 这些基本数据结构的实现...

### thread

ThreadClosureTest：线程封闭的实例，无论 ThreadLocal 是公共变量还是私有变量，每个线程都有一个副本，互不干扰。

ThreadSignalTest：三种线程协作通信的方式：suspend/resume（不推荐使用）、wait/notify、park/unpark，看看如何引发和解决死锁。

ThreadSignalWithVariableTest：多个线程之间共享参数的一种方式，公共变量；正因如此，容易引发线程安全的问题；


### lock

AbstractQueuedSynchronizer：自己实现的抽象队列同步器，封装了公共资源占用、释放、线程等待、唤醒等接口和具体实现，可以用来实现锁；

ExclusiveLock/ExclusiveLockTest：用自己实现的抽象队列同步器，实现的一把独享锁；

Semaphore/SemaphoreTest：自己实现的信号量，访问限流；

CountDownLatch/CountDownLatchTest：自己实现的栅栏，等待所有异步线程执行完；

CyclicBarrier/CyclicBarrier：自己实现的循环屏障，如游戏大厅 5 人组队；

MyReadWriteLock/MyReadWriteLockTest：自己实现的读写锁；



### list

SinglyLinkedList：单向链表的实现

