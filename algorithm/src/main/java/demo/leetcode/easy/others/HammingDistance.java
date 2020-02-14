package demo.leetcode.easy.others;

/**
 * 汉明距离，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/65/
 * <p>
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意： 0 ≤ x, y < 231.
 *
 * @author leishiguang
 * @since v1.0
 */
public class HammingDistance {

  public int hammingDistance(int x, int y) {
    String str1 = Integer.toBinaryString(x);
    String str2 = Integer.toBinaryString(y);
    int length1 = str1.length();
    int length2 = str2.length();
    int shortLength = Math.min(length1, length2);
    int result = 0;
    for (int i = 1; i <= shortLength; i++) {
      if (str1.charAt(length1 - i) != str2.charAt(length2 - i)) {
        result++;
      }
    }
    while (shortLength < length1) {
      shortLength ++;
      if(str1.charAt(length1 - shortLength) != '0'){
        result ++;
      }
    }
    while (shortLength < length2) {
      shortLength ++;
      if(str2.charAt(length2 - shortLength) != '0'){
        result ++;
      }
    }
    return result;
  }
}
