package demo.algorithm.base;

import java.util.Random;

/**
 * 常用的排序算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class Sort {

    /**
     * 冒泡排序
     */
    void bubbleSort(int[] nums) {
        boolean isChanged = true;
        for (int i = 0; i < nums.length - 1 && isChanged; i++) {
            isChanged = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                    isChanged = true;
                }
            }
        }
    }

    /**
     * 插入排序
     */
    void insertionSort(int[] nums) {
        for (int i = 1, j, current; i < nums.length; i++) {
            current = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > current; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = current;
        }
    }

    /**
     * 二分排序
     */
    void mergeSort(int[] nums, int low, int high) {
        if (low > high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        mergeSortMerge(nums, low, mid, high);
    }

    private void mergeSortMerge(int[] nums, int low, int mid, int high) {
        int[] copy = nums.clone();
        int k = low, i = low, j = mid + 1;
        while (k <= high) {
            if (i > mid) {
                nums[k++] = copy[j++];
            } else if (j > high) {
                nums[k++] = copy[i++];
            } else if (copy[j] < copy[i]) {
                nums[k++] = copy[j++];
            } else {
                nums[k++] = copy[i++];
            }
        }
    }

    /**
     * 快速排序
     */
    void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        // 利用 partition 函数找到一个随机的基准点
        int p = quickSortPartition(nums, low, high);
        // 递归地对基准点左半边和右半边的数进行排序
        quickSort(nums, low, p - 1);
        quickSort(nums, p + 1, high);
    }

    int quickSortPartition(int[] nums, int low, int high) {
        // 随机选择一个数作为基准值，nums[high] 就是基准值
        swap(nums, randRange(low, high), high);
        int i, j;
        for (i = low, j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, j);
        return i;
    }

    /**
     * 取范围区间
     */
    private int randRange(int low, int high) {
        Random random = new Random();
        return random.nextInt(high - low) + low;
    }


    /**
     * 辅助函数，交换数组两个下标的位置
     */
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
