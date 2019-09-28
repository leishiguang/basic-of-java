package demo.leetcode.easy.array;


/**
 * 加一
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/27/
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * @author leishiguang
 * @since v1.0
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int[] result;
        boolean allNight = true;
        for (int digit : digits) {
            if (digit != 9) {
                allNight = false;
                break;
            }
        }
        if (allNight) {
            result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        result = digits.clone();
        result[result.length - 1] = result[result.length - 1] + 1;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 10) {
                break;
            } else {
                result[i] = 0;
                result[i - 1] = result[i - 1] + 1;
            }
        }
        return result;

    }
}
