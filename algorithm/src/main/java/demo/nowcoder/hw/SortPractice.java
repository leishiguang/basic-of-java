package demo.nowcoder.hw;

import java.util.*;

/**
 * 排序类算法练习
 *
 * @author leishiguang
 * @since v1.0
 */
public class SortPractice {

  /**
   * 题目描述 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ） 最后一个数后面也要有空格
   * <p>
   * 详细描述：
   * <p>
   * 函数接口说明：public String getResult(long ulDataInput)
   * <p>
   * 输入参数：long ulDataInput：输入的正整数
   * <p>
   * 返回值：String
   * <p>
   * 输入描述:输入一个long型整数
   * <p>
   * 输出描述: 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
   */
  public static void main1(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      printPrime(scanner.nextLong());
    }
  }

  private static void printPrime(long num) {
    StringBuilder stringBuilder = new StringBuilder();
    int index = 2;
    while (index <= num) {
      if (num % index == 0) {
        stringBuilder.append(index).append(" ");
        num = num / index;
      } else {
        index += 1;
      }
    }
    System.out.println(stringBuilder);
  }

  /**
   * 题目描述: 输入整型数组和排序标识，对其元素按照升序或降序进行排序（一组测试用例可能会有多组数据）
   */
  public static void main2(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int n = Integer.parseInt(sc.nextLine());
      String[] str = sc.nextLine().split(" ");
      int sortFlag = Integer.parseInt(sc.nextLine());
      sortStringArray(str, sortFlag);
    }
  }

  /**
   * @param pStringArray 字符串数组
   * @param iSortFlag    排序标识：0表示按升序，1表示按降序
   */
  private static void sortStringArray(String[] pStringArray, int iSortFlag) {
    //如果直接使用，会按字典排序：Arrays.sort(pStringArray);
    Integer[] intArrays = new Integer[pStringArray.length];
    for (int i = 0; i < pStringArray.length; i++) {
      intArrays[i] = Integer.valueOf(pStringArray[i]);
    }
    Arrays.sort(intArrays);
    StringBuilder tmp = new StringBuilder();
    if (iSortFlag == 0) {
      for (Integer intArray : intArrays) {
        tmp.append(intArray).append(" ");
      }
    } else {
      for (int i = intArrays.length - 1; i >= 0; i--) {
        tmp.append(intArrays[i]).append(" ");
      }
    }
    System.out.println(tmp.substring(0, tmp.length() - 1));
  }

  /**
   * 最长递增子串
   * <p>
   * 题目描述: 计算最少出列多少位同学，使得剩下的同学排成合唱队形
   * <p>
   * 说明：
   * <p>
   * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，
   * 则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
   * <p>
   * 输入描述: 整数N
   * <p>
   * 输出描述: 最少需要几位同学出列
   * <p>
   * 输入示例：
   * <p>
   * 8
   * <p>
   * 186 186 150 200 160 130 197 200
   * <p>
   * 输出示例：
   * <p>
   * 4
   */
  public static void main3(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int n = Integer.parseInt(sc.nextLine());
      String str = sc.nextLine();
      System.out.println(removeMin(str, n));
    }
  }

  private static int removeMin(String str, Integer n) {
    String[] arrs = str.split(" ");
    int[] sort = new int[n];
    int[] asc = new int[n];
    for (int i = 0; i < n; i++) {
      int tmp = Integer.parseInt(arrs[i]);
      sort[i] = tmp;
      asc[n - i - 1] = tmp;
    }
    //最长的递增子串
    int[] dp1 = maxSub(sort);
    //倒过来之后的递增字串，反转之后即最长的递减子串
    int[] dp2 = maxSub(asc);
    int[] total = new int[n];
    int max = 0;
    for (int i = 0; i < n; i++) {
      total[i] = dp1[i] + dp2[n - i - 1];
      max = Math.max(max, total[i]);
    }
    return n - max + 1;
  }

  /**
   * 求解数组中的最长字串，dp算法
   */
  private static int[] maxSub(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    return dp;
  }



}
