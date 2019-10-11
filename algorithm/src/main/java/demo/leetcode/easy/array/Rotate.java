package demo.leetcode.easy.array;

/**
 * 旋转数组，给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * https://leetcode-cn.com/problems/rotate-array/
 * <p>
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * <p>
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @author leishiguang
 * @since v1.0
 */
public class Rotate {

    public void rotate(int[] nums, int k) {
        if (nums.length != 0 && k != 0) {
            //去除循环
            k = k % nums.length;
            if (k != 0) {
                for (int i = 0; i < k; i++) {
                    rotate(nums);
                }
            }
        }
    }

    /**
     * 向右边移动一位
     */
    public void rotate(int[] nums) {
        int tmp = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = tmp;
    }

    /**
     * 使用额外的数组，将数据完整的保存到新数组，2 次循环
     */
    public void rotate2(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    /**
     * 在原数组后面，增加一个长度为 k 的数组，把最先被替换掉的 k 个信息都拷贝进去，只用一次循环
     */
    public void rotate3(int[] nums, int k) {
        if (nums.length != 0 && k != 0) {
            //去除循环
            k = k % nums.length;
            if (k != 0) {
                int[] tmp = new int[k];
                for (int i = nums.length - 1; i >= 0; i--) {
                    if (i - k >= 0) {
                        //nums.length - 1 表示第几次循环
                        if (nums.length - i <= k) {
                            //第1次循环，放在tmp[k-1]。第k次循环，放在tmp[0]
                            tmp[k - nums.length + i] = nums[i];
                        }
                        nums[i] = nums[i - k];
                    } else {
                        nums[i] = tmp[i];
                    }
                }
            }
        }
    }

    /**
     * 环状走起...
     */
    public void rotate4(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                //替换掉第 k 个
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                //替换次数的计数
                count++;
            } while (start != current);
        }
    }

    /**
     * 多次反转
     * 这个方法基于这个事实：当我们旋转数组 k 次， k%nk\%nk%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
     * 在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n−kn-kn−k 个元素，就能得到想要的结果。
     * <p>
     * 假设 n=7n=7n=7 且 k=3k=3k=3 。
     * 原始数组                  : 1 2 3 4 5 6 7
     * 反转所有数字后             : 7 6 5 4 3 2 1
     * 反转前 k 个数字后          : 5 6 7 4 3 2 1
     * 反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
     */
    public void rotate5(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
