package demo.nowcoder.hw.tmp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 素数伴侣
 *
 * @author leishiguang
 * @since v1.0
 */
public class T6 {

  /**
   * 题目描述 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的N（N为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
   * <p>
   * 输入: 有一个正偶数N（N≤100），表示待挑选的自然数的个数。后面给出具体的数字，范围为[2,30000]。
   * <p>
   * 输出: 输出一个整数K，表示你求得的“最佳方案”组成“素数伴侣”的对数。
   *
   * 无向图的最大匹配，带花树开花算法
   */
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.nextLine();
      int N = Integer.parseInt(str);
      long[] nums = new long[N];
      String[] str1 = sc.nextLine().split(" ");
      for (int i = 0; i < N; i++) {
        nums[i] = Integer.parseInt(str1[i]);
      }
      // 偶数部分
      ArrayList<Long> evens = new ArrayList<Long>();
      // 奇数部分
      ArrayList<Long> odds = new ArrayList<Long>();
      for (int i = 0; i < N; i++) {
        if (nums[i] % 2 == 0) { // 偶数
          evens.add(nums[i]);
        } else { // 奇数
          odds.add(nums[i]);
        }
      }
      long[] evensMatch = new long[evens.size()];
      int result = 0;
      for (int i = 0; i < odds.size(); i++) {
        int[] used = new int[evens.size()];
        if (find(odds.get(i), evens, used, evensMatch)) {
          result++;
        }
      }
      System.out.println(result);

    }
    sc.close();
  }

  private static boolean isPrime(long num) {
    for (int i = 2; i < Math.sqrt(num); i++) {
      if (num % i == 0) {
        return false;
      }
      if (num == 1) {
        return false;
      }
    }
    return true;
  }

  public static boolean find(long x, ArrayList<Long> evens, int[] used, long[] evensMatch) {
    int j;
    for (j = 0; j < evens.size(); j++) {
      if (isPrime(x + evens.get(j)) && used[j] == 0) {
        used[j] = 1;
        if (evensMatch[j] == 0 || find(evensMatch[j], evens, used, evensMatch)) {
          evensMatch[j] = x;
          return true;
        }
      }
    }
    return false;
  }


}
