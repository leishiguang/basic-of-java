package demo.nowcoder.hw;

import java.util.*;
import java.util.Map.Entry;


/**
 * 栈类算法练习
 *
 * @author leishiguang
 * @since v1.0
 */
public class StackPractice {

  /**
   * 题目描述 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
   * <p>
   * 输入描述: 先输入键值对的个数 然后输入成对的index和value值，以空格隔开
   * <p>
   * 输出描述: 输出合并后的键值对（多行）
   */
  public static void main1(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      int n = Integer.parseInt(scanner.nextLine());
      Map<Integer, Long> map = new HashMap<>(6);
      TreeSet<Integer> set = new TreeSet<>();
      while (--n >= 0 && scanner.hasNext()) {
        String str = scanner.nextLine();
        String[] strs = str.split(" ");
        Integer index = Integer.parseInt(strs[0]);
        Integer value = Integer.parseInt(strs[1]);
        if (map.containsKey(index)) {
          map.put(index, map.get(index) + value);
        } else {
          map.put(index, Long.valueOf(value));
        }
        set.add(index);
      }
      for (Integer index : set) {
        System.out.println(index + " " + map.get(index));
      }
    }
  }

  /**
   * 题目描述，数据表记录包含表索引和数值（int范围的整数）...
   *
   * 说明：使用排序map的方法
   */
  public static void main2(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      int n = Integer.parseInt(scanner.nextLine());
      SortedMap<Integer, Long> map = new TreeMap<>();
      while (--n >= 0 && scanner.hasNext()) {
        String[] strs = scanner.nextLine().split(" ");
        Integer index = Integer.parseInt(strs[0]);
        Integer value = Integer.parseInt(strs[1]);
        if (map.containsKey(index)) {
          map.put(index, map.get(index) + value);
        } else {
          map.put(index, Long.valueOf(value));
        }
      }
      for(Map.Entry<Integer,Long> entry:map.entrySet()){
        System.out.println(entry.getKey() + " " + entry.getValue());
      }
    }

  }
}
