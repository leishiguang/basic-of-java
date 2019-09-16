package demo.algorithm.leetcode;

/**
 * LeetCode 第8题：字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * @author leishiguang
 * @since v1.0
 */
public class L8 {

    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        int sign = 1;
        int start = 0;
        long res = 0;
        char firstChar = str.charAt(0);
        if (firstChar == '+') {
            start += 1;
        } else if (firstChar == '-') {
            sign = -1;
            start += 1;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                break;
            }
            // -‘0’ 的意思是将字符串数字变为long型的数字，查一下ASCII表就理解了
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && res > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) (res * sign);
    }
}
