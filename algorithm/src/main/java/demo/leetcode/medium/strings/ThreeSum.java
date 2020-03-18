package demo.leetcode.medium.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  public List<List<Integer>> threeSum(int[] nums) {
    //第一步，以某个数值为两数之和，同时执行去重
    Set<Integer> twoSumSet = new HashSet<>();
    List<List<Integer>> result = new ArrayList<>();
    int[] newNums = nums;
    for (int i = 0; i < nums.length; i++) {
      if (twoSumSet.contains(nums[i])) {
        continue;
      }
      twoSumSet.add(nums[i]);
      //第二步，生成新的数组
      newNums = splitNums(newNums, i);
      //第三步，计算两数之和
      List<List<Integer>> twoSum = twoSum(newNums, nums[i] * -1);
      result.addAll(twoSum);
    }
    return result;
  }

  /**
   * 去除第i位的元素，生成新数组
   */
  private int[] splitNums(int[] nums, int index) {
    int[] newNums = new int[nums.length - 1];
    for (int i = 0; i < newNums.length; i++) {
      if (i < index) {
        newNums[i] = nums[i];
      } else if (i > index) {
        newNums[i] = nums[1 + i];
      }
    }
    return newNums;
  }

  /**
   * 两数之和，数组中哪些组合的值等于k
   */
  private List<List<Integer>> twoSum(int[] nums, int k) {
    List<List<Integer>> result = new ArrayList<>();
    HashMap<Integer, Integer> sumMap = new HashMap<>();
    for (int num : nums) {
      if (sumMap.containsKey(k - num)) {
        sumMap.put(num, k - num);
      } else {
        sumMap.put(num, null);
      }
    }
    for (Map.Entry<Integer, Integer> entry : sumMap.entrySet()) {
      if (entry.getValue() != null) {
        List<Integer> tmp = Arrays.asList(-1 * k, entry.getKey(), entry.getValue());
        result.add(tmp);
      }
    }
    return result;
  }
}
