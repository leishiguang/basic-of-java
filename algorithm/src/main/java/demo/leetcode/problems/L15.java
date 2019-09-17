package demo.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 第15题：三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * https://leetcode-cn.com/problems/3sum/
 * https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-yi-chu-pan-duan-by-ba-qiu-g/
 *
 * @author leishiguang
 * @since v1.0
 */
public class L15 {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            //去重
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            if (nums[left] < 0 && Integer.MIN_VALUE - nums[left] > nums[i]) {
                //如果溢出最小值则跳过
                continue;
            }
            if (nums[i] > 0 && Integer.MAX_VALUE - nums[left] < nums[i]) {
                //溢出最大值直接结束，不可能会有新的三元组出现了
                break;
            }
            while (left < right) {
                if (nums[right] > -nums[i] - nums[left]) {
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--; //右指针去重
                    }
                    right--;
                } else if (nums[right] < -nums[i] - nums[left]) {
                    while (left < right && nums[left + 1] == nums[left]) {
                        //左指针去重
                        left++;
                    }
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //左指针去重
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    //右指针去重
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }
        return result;
    }
}
