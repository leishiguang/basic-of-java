package demo.leetcode.easy.dp;

import java.util.HashMap;

/**
 * 爬楼梯
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/54/
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * @author leishiguang
 * @since v1.0
 */
public class ClimbStairs {

    private HashMap<Integer, Integer> map = new HashMap<>();


    /**
     * 迭代实现动态规划
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (!map.containsKey(n)) {
            map.put(n, climbStairs(n - 1) + climbStairs(n - 2));
        }
        return map.get(n);
    }

    /**
     * 数组方式记录动态规划
     */
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
