package demo.leetcode.easy.design;

/**
 * 最小栈，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/24/design/59/
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。 pop() -- 删除栈顶的元素。 top() -- 获取栈顶元素。 getMin() -- 检索栈中的最小元素。
 *
 * @author leishiguang
 * @since v1.0
 */
public class MinStack {

  Node head;
  Node tail;
  int count;

  class Node {

    int num;
    Node next;

    Node(int num) {
      this.num = num;
    }
  }

  /**
   * initialize your data structure here.
   */
  public MinStack() {
    head = new Node(0);
    tail = new Node(0);
    head.next = tail;
    count = 0;
  }

  public void push(int x) {
    Node node = new Node(x);
    node.next = head.next;
    head.next = node;
    count++;
  }

  public void pop() {
    if (count > 0) {
      head.next = head.next.next;
      count--;
    }
  }

  public int top() {
    int value = head.next.num;
    //pop();
    return value;
  }

  public int getMin() {
    int min = Integer.MAX_VALUE;
    Node cur = head.next;
    for (int i = 0; i < count; i++) {
      min = Math.min(cur.num, min);
      cur = cur.next;
    }
    return min;
  }

  public static void main(String[] args) {
    MinStack obj = new MinStack();
    obj.push(-2);
    obj.push(0);
    obj.push(-3);
    int param_1 = obj.getMin();
    obj.pop();
    int param_3 = obj.top();
    int param_4 = obj.getMin();
    int param_5 = obj.top();
    int param_6 = obj.top();
    int param_7 = obj.top();

  }
}
