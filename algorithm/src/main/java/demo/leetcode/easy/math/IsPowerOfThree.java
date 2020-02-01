package demo.leetcode.easy.math;

/**
 * 3的幂 https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/62/
 * <p>
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * <p>
 * 示例，输入: 27，输出: true
 *
 * @author leishiguang
 * @since v1.0
 */
public class IsPowerOfThree {

  public boolean isPowerOfThree(int n) {
    int x = 3;
    if (n == 1) {
      return true;
    }
    while (n >= x) {
      if (n % x != 0) {
        return false;
      }
      if (n == x) {
        return true;
      }
      n = n / x;
    }
    return false;
  }

  /**
   * 3的幂次质因子只有3，而整数范围内的3的幂次最大是1162261467
   */
  public boolean isPowerOfThree2(int n) {
    return n > 0 && 1162261467 % n == 0;
  }

  public boolean isPowerOfThree3(int n) {
    if (n == 1) {
      return true;
    } else if (n == 0) {
      return false;
    } else {
      return n % 3 == 0 && isPowerOfThree3(n / 3);
    }
  }

}
