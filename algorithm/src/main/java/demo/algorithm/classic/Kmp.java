package demo.algorithm.classic;

/**
 * KMP 算法（Knuth-Morris-Pratt 算法）是一个著名的字符串匹配算法，效率很高
 * <p>
 * 特点：
 * 1. KMP 算法永不回退 txt 的指针 i，不走回头路（不会重复扫描 txt），而是借助 dp 数组中储存的信息把 pat 移到正确的位置继续匹配，时间复杂度只需 O(N)，用空间换时间，也算是一种动态规划算法；
 * <p>
 * 参考：
 * 1. https://leetcode-cn.com/problems/implement-strstr/solution/kmp-suan-fa-xiang-jie-by-labuladong/
 *
 * @author leishiguang
 * @since v1.0
 */
public class Kmp {

    private int[][] dp;
    private String pat;

    /**
     * 通过 pat 构建 dp 数组
     *
     * @param pat 目标字符串
     */
    public Kmp(String pat) {
        this.pat = pat;
        int m = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[m][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int x = 0;
        // 当前状态 j 从 1 开始
        for (int j = 1; j < m; j++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[x][c];
                }
            }
            // 更新影子状态
            x = dp[x][pat.charAt(j)];
        }
    }

    /**
     * 查询匹配的下标
     *
     * @param txt 待查询字符串
     */
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < n; i++) {
            // 计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            // 到达终止态，返回结果
            if (j == m){
                return i - m + 1;
            }
        }
        // 没到达终止态，匹配失败
        return -1;
    }
}
