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
    if(n == 1){
      return true;
    }
    while (n >= x) {
      if(n % x != 0){
        return false;
      }
      if (n == x) {
        return true;
      }
      n = n / x;
    }
    return false;
  }
}
