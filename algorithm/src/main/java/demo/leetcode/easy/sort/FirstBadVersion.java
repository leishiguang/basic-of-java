package demo.leetcode.easy.sort;

import javax.management.MXBean;
import java.util.TooManyListenersException;

/**
 * 第一个错误的版本
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/8/sorting-and-searching/53/
 *
 * @author leishiguang
 * @since v1.0
 */
public class FirstBadVersion {


    public static int firstBadVersion(int n) {
        int max = n;
        int min = 0;
        int mid;
        while (max > min) {
            mid = (max - min) / 2 + min;
            if (isBadVersion(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }



    /**
     * 大于某个版本的时候，就是错误版本
     */
    private static boolean isBadVersion(int version) {
        return version >= 2;
    }
}
