package demo.concurrent.thread;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 多个线程依次执行
 *
 * @author leishiguang
 * @since v1.0
 */
@DisplayName("线程通信")
public class ThreadSortTest {

  int sum = 0;
  AtomicReference<Thread> currentThreadA = new AtomicReference<>();
  volatile Thread currentThreadB = null;

  @Test
  @DisplayName("以AtomicReference方式进行")
  public void testA() throws IOException {
    Thread threadA = new Thread() {
      @Override
      public void run() {
        while (true){
          if (currentThreadA.get() == null) {
            System.out.println("A:" + sum);
            sum++;
            currentThreadA.set(this);
          }
        }
      }
    };
    Thread threadB = new Thread() {
      @Override
      public void run() {
        while (true) {
          if (currentThreadA.get() == threadA) {
            System.out.println("B:" + sum);
            sum++;
            currentThreadA.set(this);
          }
        }

      }
    };
    Thread threadC = new Thread(() -> {
      while (true) {
        if (currentThreadA.get() == threadB) {
          System.out.println("C:" + sum);
          sum++;
          currentThreadA.set(null);
        }
      }
    });
    threadC.start();
    threadA.start();
    threadB.start();
    System.in.read();
  }

  @Test
  @DisplayName("以volatile变量方式进行")
  public void testB() throws IOException {
    Thread threadA = new Thread() {
      @Override
      public void run() {
        while (true){
          if (currentThreadB == null) {
            System.out.println("A:" + sum);
            sum++;
            currentThreadB = this;
          }
        }
      }
    };
    Thread threadB = new Thread() {
      @Override
      public void run() {
        while (true) {
          if (currentThreadB == threadA) {
            System.out.println("B:" + sum);
            sum++;
            currentThreadB = this;
          }
        }
      }
    };
    Thread threadC = new Thread(() -> {
      while (true) {
        if (currentThreadB == threadB) {
          System.out.println("C:" + sum);
          sum++;
          currentThreadB = null;
        }
      }
    });
    threadC.start();
    threadA.start();
    threadB.start();
    System.in.read();
  }
}
