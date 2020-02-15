package demo.structure;

/**
 * 以数组方式实现的队列，先进先出，默认大小是2，如果超过长度，则队列最大值翻倍
 *
 * @author leishiguang
 * @since v1.0
 */
public class ArrayQueue {

  private String[] nodes;
  private int maxSize;
  private int currentSum;
  private int currentLeft;

  public ArrayQueue() {
    maxSize = 2;
    nodes = new String[maxSize];
  }

  /**
   * 往队列中加数据
   *
   * @param str 要添加的数据
   */
  public void add(String str) {
    if (currentSum < maxSize) {
      nodes[currentSum] = str;
    } else {
      maxSize *= 2;
      String[] newNode = new String[maxSize];
      currentSum = nodes.length - currentLeft;
      for (int i = 0; i + currentLeft < nodes.length; i++) {
        newNode[i] = nodes[currentLeft + i];
      }
      newNode[currentSum] = str;
      nodes = newNode;
      currentLeft = 0;
    }
    currentSum++;
  }

  /**
   * 从队列中拿出一个数据
   *
   * @return 如果队列有数据，返回最先添加的数据 如果队列为空，则返回“”
   */
  public String out() {
    if (currentSum == 0 || currentLeft >= nodes.length) {
      return "";
    }
    String result = nodes[currentLeft];
    currentLeft++;
    return result;
  }

  public static void main(String[] args) {
    ArrayQueue myQueue = new ArrayQueue();
    myQueue.add("1");
    myQueue.add("2");
    System.out.println(myQueue.out());
    System.out.println(myQueue.out());
    System.out.println(myQueue.out());
    myQueue.add("3");
    myQueue.add("4");
    myQueue.add("5");
    myQueue.add("6");
    myQueue.add("7");
    System.out.println(myQueue.out());
    System.out.println(myQueue.out());
    System.out.println(myQueue.out());
    myQueue.add("8");
    myQueue.add("9");
  }

}

