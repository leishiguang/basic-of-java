package demo.leetcode.easy.design;

import javax.swing.CellRendererPane;

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

  private Node first;
  private Node min;
  private static class Node {
    int val;
    Node next;
    Node(int val) {
      this.val = val;
    }
  }

  /**
   * initialize your data structure here.
   */
  public MinStack() {

  }

  public void push(int x) {
    Node node = new Node(x);
    node.next = first;
    first = node;
    if(min == null){
      min = first;
    }else{
      if(first.val < min.val){
        min = first;
      }
    }
  }

  public void pop() {
    if(first == null){
      return;
    }
    Node oldFirst = first;
    first = oldFirst.next;
    if(oldFirst == min){
      min = first;
      Node cur = first;
      while (cur != null){
        if(cur.val < min.val){
          min = cur;
        }
        cur = cur.next;
      }
    }
  }

  public int top() {
    return first.val;
  }

  public int getMin() {
    if(min == null){
      return 0;
    }
    return min.val;
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
