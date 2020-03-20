package demo.leetcode.medium.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 三数之和，https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/29/array-and-strings/75/
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * @author leishiguang
 * @since v1.0
 */
public class ThreeSum {

  /**
   * 半暴力的解法，找出一个人，再找其它两个人
   */
  public List<List<Integer>> threeSum(int[] nums) {
    //特判
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }
    Arrays.sort(nums);
    if (nums[0] > 0 || nums[nums.length - 1] < 0) {
      return new ArrayList<>();
    }
    //第一步，以某个数值为两数之和，同时执行第一个数的去重
    Set<Integer> twoSumSet = new HashSet<>();
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < nums.length - 2; i++) {
      if (twoSumSet.contains(nums[i])) {
        continue;
      }
      twoSumSet.add(nums[i]);
      //第二步，生成新的数组
      //int[] newNums = splitNums(nums, i);
      //第三步，方法一，计算两数之和
      //List<List<Integer>> twoSum = twoSum(newNums, nums[i] * -1);
      //第三步，方法二，直接计算
      List<List<Integer>> twoSum = twoSum2(nums, i);
      result.addAll(twoSum);

    }
    return result;
  }

  /**
   * 从第i位的元素，开始生成新数组
   */
  private int[] splitNums(int[] nums, int index) {
    int[] newNums = new int[nums.length - index - 1];
    System.arraycopy(nums, index + 1, newNums, 0, newNums.length);
    return newNums;
  }

  /**
   * 两数之和，数组中哪些组合的值等于k
   */
  private List<List<Integer>> twoSum(int[] nums, int k) {
    List<List<Integer>> result = new ArrayList<>();
    Set<Integer> sumMap = new HashSet<>();
    Set<Integer> numCollect = new HashSet<>();
    for (int num : nums) {
      if (sumMap.contains(k - num)) {
        numCollect.add(num);
      } else {
        sumMap.add(num);
      }
    }
    for (Integer num : numCollect) {
      result.add(Arrays.asList(-1 * k, num, k - num));
    }
    return result;
  }

  /**
   * 两数之和，数组中哪些组合的值等于第i位的负数
   */
  private List<List<Integer>> twoSum2(int[] nums, int index) {
    int k = nums[index] * -1;
    List<List<Integer>> result = new ArrayList<>();
    Set<Integer> sumMap = new HashSet<>();
    Set<Integer> numCollect = new HashSet<>();
    for (int i = index + 1; i < nums.length; i++) {
      if (sumMap.contains(k - nums[i])) {
        numCollect.add(nums[i]);
      } else {
        sumMap.add(nums[i]);
      }
    }
    for (Integer num : numCollect) {
      result.add(Arrays.asList(-1 * k, num, k - num));
    }
    return result;
  }

  /**
   * 优先选择C位，再左右两边找可能的值
   */
  public List<List<Integer>> threeSum2(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    //特判
    if (nums == null || nums.length < 3) {
      return result;
    }
    Arrays.sort(nums);
    if (nums[nums.length - 1] < 0) {
      return result;
    }
    //依次选择C位
    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > 0) {
        break;
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int left = i + 1;
      int last = nums.length - 1;
      while (left < last) {
        int sum = nums[i] + nums[left] + nums[last];
        if (sum == 0) {
          result.add(Arrays.asList(nums[i], nums[left], nums[last]));
          while (left < last && nums[left] == nums[left + 1]) {
            left++;
          }
          while (left < last && nums[last] == nums[last - 1]) {
            last--;
          }
          left ++;
          last --;
        }
        if (sum < 0) {
          left++;
        }
        if (sum > 0) {
          last--;
        }
      }
    }
    return result;
  }
}
