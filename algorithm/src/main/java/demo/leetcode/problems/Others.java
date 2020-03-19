package demo.leetcode.problems;

/**
 * 其它的一些
 *
 * @author leishiguang
 * @since v1.0
 */
public class Others {

  /**
   * 连续子数组，最大值
   */
  public int maxSubArray(int[] sums) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < sums.length; i++) {
      int tmpSum = 0;
      for (int j = i; j < sums.length; j++) {
        tmpSum += sums[j];
        max = Math.max(max, tmpSum);
      }
    }
    return max;
  }

  public int maxSubArrayWithDp(int[] sums) {
    int[] dp = new int[sums.length];
    dp[0] = sums[0];
    int max = sums[0];
    for (int i = 1; i < sums.length; i++) {
      int tmpSum = sums[i] + dp[i - 1];
      dp[i] = Math.max(sums[i], tmpSum);
      max = Math.max(max,dp[i]);
    }
    return max;
  }
}
