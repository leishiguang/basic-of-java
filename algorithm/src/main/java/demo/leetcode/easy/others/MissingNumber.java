package demo.leetcode.easy.others;

/**
 * 缺失数字,给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * https://leetcode-cn.com/problems/missing-number/
 *
 * @author leishiguang
 * @since v1.0
 */
public class MissingNumber {
  public int missingNumber(int[] nums) {
    boolean[] exitsTable = new boolean[nums.length + 1];
    for(int i:nums){
      exitsTable[i] = true;
    }
    for(int i = 0; i < exitsTable.length; i ++){
      if(!exitsTable[i]){
        return i;
      }
    }
    return 0;
  }
}
