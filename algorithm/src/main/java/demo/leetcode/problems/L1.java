package demo.leetcode.problems;

import java.util.HashMap;

/**
 * LeetCode 第一题：两数之和
 * https://leetcode-cn.com/problems/two-sum
 *
 * @author leishiguang
 * @since v1.0
 */
public class L1 {

    public static int[] twoSum1(int[] nums, int target) {
        int[] indexs = new int[2];
        //建立 k-v Hash 表
        HashMap<Integer,Integer> hash = new HashMap<>(6);
        for(int i = 0;i< nums.length;i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                break;
            }
            //将数据存入 key 为补数，value 为下标
            hash.put(target - nums[i],i);
        }
        return indexs;
    }
}
