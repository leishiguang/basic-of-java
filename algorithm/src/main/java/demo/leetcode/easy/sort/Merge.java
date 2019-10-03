package demo.leetcode.easy.sort;


/**
 * 合并两个有序数组
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/8/sorting-and-searching/52/
 *
 * @author leishiguang
 * @since v1.0
 */
public class Merge {

    /**
     * 数组 2 倒着放进数组 1
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int indexTotal = m + n - 1;
        while(index1 >= 0 && index2 >= 0){
            if(nums1[index1] > nums2[index2]){
                nums1[indexTotal] = nums1[index1];
                index1 --;
            }else{
                nums1[indexTotal] = nums2[index2];
                index2 --;
            }
            indexTotal --;
        }
        while(index2 >=0 ){
            nums1[index2] = nums2[index2];
            index2 --;
        }
    }
}
