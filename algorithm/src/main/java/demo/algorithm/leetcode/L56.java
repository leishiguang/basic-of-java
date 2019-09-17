package demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. Merge Intervals (合并区间)
 * 给出一个区间的集合，请合并所有重叠的区间。
 * https://leetcode-cn.com/problems/merge-intervals/
 *
 * @author leishiguang
 * @since v1.0
 */
public class L56 {

    public int[][] merge(int[][] intervals) {
        //排序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        List<int[]> result = new ArrayList<>();
        for(int[] interval : intervals){
            if(result.isEmpty()){
                result.add(interval);
            }else{
                int[] lastInterval = result.get(result.size() - 1);
                if(interval[0] <= lastInterval[1]){
                    lastInterval[1] = Math.max(interval[1],lastInterval[1]);
                }else{
                    result.add(interval);
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
