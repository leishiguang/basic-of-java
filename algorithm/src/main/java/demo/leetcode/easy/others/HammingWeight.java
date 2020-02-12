package demo.leetcode.easy.others;

/**
 * 位1的个数，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/64/
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *
 * @author leishiguang
 * @since v1.0
 */
public class HammingWeight {

  /**
   * you need to treat n as an unsigned value
   */
  public int hammingWeight(int n) {
    String tmp = Integer.toBinaryString(n);
    int count = 0;
    for (char c : tmp.toCharArray()) {
      if (c == '1') {
        count++;
      }
    }
    return count;
  }

  public int hammingWeight2(int n) {
    int res = 0;
    while (n != 0) {
      n = n & (n - 1);
      res++;
    }
    return res;
  }
}
