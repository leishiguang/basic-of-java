package demo.leetcode.easy.dp;

/**
 * 买卖股票的最佳时机，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/55/
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4] 输出: 5 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。 注意利润不能是
 * 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1] 输出: 0 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author leishiguang
 * @since v1.0
 */
public class MaxProfit {

  /**
   * 记录 n-1 天的最低价格，以及最高盈利。新价格出现的时候，重置历史记录。
   */
  public int maxProfit(int[] prices) {
    int winMax = 0;
    int buyMin = Integer.MAX_VALUE;
    // 新的一天，新的价格出现后，判断该价格是不是历史新低，盈利是否历史新高
    for (int price : prices) {
      if (price < buyMin) {
        buyMin = price;
      } else {
        int winTmp = price - buyMin;
        winMax = Math.max(winMax, winTmp);
      }
    }
    return winMax;
  }

}
