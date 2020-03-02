package demo.nowcoder.hw;

import java.util.*;

/**
 * 数组类算法练习
 *
 * @author leishiguang
 * @since v1.0
 */
public class ArrayPractice {


  /**
   * 题目描述 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
   * <p>
   * Input Param：
   * <li>n 输入随机数的个数</li>
   * <li>inputArray n个随机整数组成的数组</li>
   * <p></p>
   * Return Value:
   * <li>OutputArray    输出处理后的随机整数</li>
   */
  public static void main1(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      int n = scanner.nextInt();
      Set<Integer> set = new HashSet<>();
      while (--n >= 0 && scanner.hasNext()) {
        set.add(scanner.nextInt());
      }
      List<Integer> list = new ArrayList<>(set);
      Collections.sort(list);
      for (Integer s : list) {
        System.out.println(s);
      }
    }
  }

  /**
   * 去重及排序的方法2，使用tree
   */
  public static void main2(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      int n = scanner.nextInt();
      TreeSet<Integer> set = new TreeSet<>();
      while (--n >= 0 && scanner.hasNext()) {
        set.add(scanner.nextInt());
      }
      for (Integer s : set) {
        System.out.println(s);
      }
    }
  }


  /**
   * 切最少的砖块，https://leetcode-cn.com/problems/brick-wall/
   */
  public int leastBricks(List<List<Integer>> wall) {
    HashMap<Integer, Integer> maxMap = new HashMap<>();
    int maxValue = 0;
    int maxKey = 0;
    for (List<Integer> param : wall) {
      int sum = 0;
      for (Integer p : param) {
        sum += p;
        if (maxMap.containsKey(sum)) {
          maxMap.put(sum, maxMap.get(sum) + 1);
        } else {
          maxMap.put(sum, 1);
        }
      }
    }
    for (Map.Entry<Integer, Integer> entry : maxMap.entrySet()) {
      maxKey = Math.max(maxKey, entry.getKey());
    }
    maxMap.put(maxKey, 0);
    for (Map.Entry<Integer, Integer> entry : maxMap.entrySet()) {
      maxValue = Math.max(maxValue, entry.getValue());
    }
    int total = wall.size();
    return total - maxValue;
  }
}
