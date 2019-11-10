package demo.leetcode.easy.dp;

/**
 * 最大子序和，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/56/
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author leishiguang
 * @since v1.0
 */
public class MaxSubArray {

  public int maxSubArray(int[] nums) {
    //设置dp数组，dp[i]表示的是以A[i]结尾的连续序列最大和
    //求解 nums 的最大和，就变成了求解dp数组的最大值
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    //构建dp数组，设定了dp[i]必须是A[i]结尾，于是就只有两种情况：1、只有A[i]一个数，2、dp[i-1]+A[i]
    //那么，dp[i]的值就是1和2的最大值；
    for (int i = 1; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
    }
    int max = Integer.MIN_VALUE;
    for (int tmp : dp) {
      if (tmp > max) {
        max = tmp;
      }
    }
    return max;
  }
}
