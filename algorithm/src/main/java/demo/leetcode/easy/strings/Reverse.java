package demo.leetcode.easy.strings;


/**
 * 整数反转
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/33/
 * <p>
 * 输入: 123
 * 输出: 321
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author leishiguang
 * @since v1.0
 */
public class Reverse {

    public int reverse(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        char tmp;
        int left = 0;
        int right = chars.length - 1;
        int start = 0;
        int flag = 1;
        if (chars[0] == '-') {
            left = 1;
            start = 1;
            flag = -1;
        }
        while (left < right) {
            tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        long resultLong = 0;
        for (; start < chars.length; start++) {
            resultLong = resultLong * 10 + (chars[start] - 48);
        }
        resultLong *= flag;
        if (resultLong > Integer.MAX_VALUE || resultLong < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) resultLong;
        }
    }
}




