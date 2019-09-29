package demo.leetcode.easy.strings;

/**
 * 移动零
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/28/
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * https://leetcode-cn.com/problems/move-zeroes/solution/
 *
 * @author leishiguang
 * @since v1.0
 */
public class MoveZeroes {

    //冒泡
    public void moveZeroes(int[] nums) {
        int start = 0;
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[start] == 0) {
                zeroCount++;
                int j = start;
                for (; j < nums.length - zeroCount; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[j] = 0;
            } else {
                start++;
            }
        }
    }

    //快捷方法
    public void moveZeroes2(int[] nums) {
        int zeroCount = 0;
        int right = 0;
        int left = 0;
        while (right < nums.length) {
            while (right < nums.length - 1 && nums[right] == 0) {
                right++;
                zeroCount++;
            }
            nums[left] = nums[right];
            left++;
            right++;
        }
        while(zeroCount > 0){
            nums[nums.length - zeroCount] = 0;
            zeroCount--;
        }
    }
}
