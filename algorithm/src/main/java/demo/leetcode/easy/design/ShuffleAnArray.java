package demo.leetcode.easy.design;

import java.util.Random;

/**
 * 打乱一个没有重复元素的数组 https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/24/design/58/
 * <p>
 * 示例:
 * <p>
 * // 以数字集合 1, 2 和 3 初始化数组。 int[] nums = {1,2,3}; Solution solution = new Solution(nums);
 * <p>
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。 solution.shuffle();
 * <p>
 * // 重设数组到它的初始状态[1,2,3]。 solution.reset();
 * <p>
 * // 随机返回数组[1,2,3]打乱后的结果。 solution.shuffle();
 * <p>
 * tips：洗牌算法还有：1、Fisher-Yates Shuffle算法，2、Knuth-Durstenfeld Shuffle算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class ShuffleAnArray {

  private int[] nums;
  private Node head;

  /**
   * 构建链表
   */
  class Node {

    int num;
    Node next;

    public Node(int num) {
      this.num = num;
    }
  }

  public ShuffleAnArray(int[] nums) {
    this.nums = nums;
    initNode();
  }

  /**
   * Resets the array to its original configuration and return it.
   */
  public int[] reset() {
    return nums;
  }

  /**
   * 辅助方法，构建循环链表
   */
  public void initNode() {
    this.head = new Node(Integer.MIN_VALUE);
    Node tmp = head;
    for (int num : nums) {
      tmp.next = new Node(num);
      tmp = tmp.next;
    }
    tmp.next = head.next;
  }

  /**
   * Returns a random shuffling of the array.
   */
  public int[] shuffle() {
    if (nums.length == 0) {
      return new int[]{};
    }
    initNode();
    int[] newNums = new int[nums.length];
    Random random = new Random();
    int i = nums.length - 1;
    Node pre = head.next;
    Node cur = pre.next;
    while (i >= 0) {
      int index = random.nextInt(i + 1);
      while (index > 0) {
        pre = cur;
        cur = cur.next;
        index--;
      }
      newNums[i] = cur.num;
      pre.next = cur.next;
      cur = pre.next;
      i--;
    }
    return newNums;
  }

  /**
   * Your Solution object will be instantiated and called as such: Solution obj = new
   * Solution(nums); int[] param_1 = obj.reset(); int[] param_2 = obj.shuffle();
   */
  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    ShuffleAnArray obj = new ShuffleAnArray(nums);
    int[] param1 = obj.reset();
    int[] para2 = obj.shuffle();
  }
}
