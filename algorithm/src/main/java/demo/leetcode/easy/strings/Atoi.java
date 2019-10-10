package demo.leetcode.easy.strings;

/**
 * 字符串转换整数
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/37/
 *
 * @author leishiguang
 * @since v1.0
 */
public class Atoi {

    public int myAtoi(String str) {
        if ("".equals(str)) {
            return 0;
        }
        long result = 0;
        int index = 0;
        int flag = 1;
        char[] chars = str.toCharArray();
        int length = chars.length;
        while (index < length && chars[index] == ' ') {
            index++;
        }
        if (index == length) {
            return 0;
        } else if (chars[index] == '+') {
            index++;
        } else if (chars[index] == '-') {
            flag = -1;
            index++;
        }
        while (index < length && chars[index] >= '0' && chars[index] <= '9') {
            result = result * 10 + chars[index] - '0';
            if (flag == 1) {
                if (result >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (result - 1 > Integer.MAX_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            index++;
        }
        return (int) (result * flag);
    }
}
