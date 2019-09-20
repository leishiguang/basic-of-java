package demo.leetcode.easy.array;

import java.util.*;

/**
 * 两个数组的交集 II
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/26/
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * <p>
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * <p>
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 进阶:
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author leishiguang
 * @since v1.0
 */
public class Intersect {

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        List<Integer> resultList = new ArrayList<>();
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                resultList.add(nums1[index1]);
                index1++;
                index2++;
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> timesMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int num : nums1) {
            if (timesMap.containsKey(num)) {
                timesMap.put(num, timesMap.get(num) + 1);
            } else {
                timesMap.put(num, 1);
            }
        }
        for (int num : nums2) {
            if (timesMap.containsKey(num) && timesMap.get(num) > 0) {
                resultList.add(num);
                timesMap.put(num, timesMap.get(num) - 1);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
