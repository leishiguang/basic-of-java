package demo.leetcode.easy.array;

import sun.nio.cs.FastCharsetProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/24/
 * <p>
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 * @author leishiguang
 * @since v1.0
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        boolean result = false;
        HashMap<Integer, Integer> tmp = new HashMap<>();
        for (int num : nums) {
            if (tmp.containsKey(num)) {
                result = true;
                break;
            } else {
                tmp.put(num, 0);
            }
        }
        return result;
    }

    /**
     * 一样的使用 Hash，这儿改用 Set 数据类型
     */
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    /**
     * 排序
     */
    public boolean containsDuplicate3(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i+1] == nums[i]){
                return true;
            }
        }
        return false;
    }


}
