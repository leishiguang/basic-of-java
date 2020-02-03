package demo.leetcode.easy.math;

/**
 * 罗马数字转整数 https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/63/
 *
 * @author leishiguang
 * @since v1.0
 */
public class RomanToInt {


  public int romanToInt(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int result = 0;
    int pre = getValue(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
      int cur = getValue(s.charAt(i));
      if (pre < cur) {
        result -= pre;
      } else {
        result += pre;
      }
      pre = cur;
    }
    result += pre;
    return result;
  }

  private int getValue(char ch) {
    switch (ch) {
      case 'I':
        return 1;
      case 'V':
        return 5;
      case 'X':
        return 10;
      case 'L':
        return 50;
      case 'C':
        return 100;
      case 'D':
        return 500;
      case 'M':
        return 1000;
      default:
        return 0;
    }
  }

}
