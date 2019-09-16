package demo.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeetCode 1 测试
 *
 * @author leishiguang
 * @since v1.0
 */
class L1Test {
    @Test
    void go() {
        int[] nums = {2, 7, 11, 15};
        int targets = 9;
        //注意数组的顺序可能会影响测试结果
        int[] expected = {1, 0};
        int[] actual = L1.twoSum1(nums, targets);
        assertArrayEquals(expected,actual);

        int[] nums2 = {3,2,4};
        int targets2 = 6;
        L1.twoSum1(nums2, targets2);

    }
}