package demo.leetcode.easy.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/25/
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author leishiguang
 * @since v1.0
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i + 1] != nums[i]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 异或，类似于这个数 a 如果不存在，就会 +a，如果不存在就会 -a，这样最终的结果就是那个只出现一次的数
     */
    public int singleNumber2(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

    /**
     * 用 hash 表
     */
    public int singleNumber3(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if(set.contains(num)){
                set.remove(num);
            }else{
                set.add(num);
            }
        }
        return set.iterator().next();
    }
}
