package demo.nowcoder.hw;

import java.util.*;

/**
 * 其它的一些算法练习
 *
 * @author leishiguang
 * @since v1.0
 */
public class OthersPractice {

  /**
   * 题目描述 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
   * <p>
   * 输入描述: 输入一个正浮点数值
   * <p>
   * 输出描述: 输出该数值的近似整数值
   */
  public static void main1(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      printInteger(scanner.nextFloat());
    }
  }

  private static void printInteger(float floatNum) {
    int num = (int) (floatNum / 1);
    int result = floatNum - num >= 0.5 ? num + 1 : num;
    System.out.println(result);
  }

  /**
   * 题目描述 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
   * <p>
   * 输入描述: 输入一个整数（int类型）
   * <p>
   * 输出描述: 这个数转换成2进制后，输出1的个数
   */
  public static void main2(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      Integer num = sc.nextInt();
      countTwo1(num);
      countTwo2(num);
      countTwo3(num);
    }
  }

  private static void countTwo1(Integer num) {
    int count = 0;
    while (num != 0) {
      count++;
      num = num & (num - 1);
    }
    System.out.println(count);
  }

  private static void countTwo2(Integer num) {
    int count = 0;
    //二进制
    char[] nums = Integer.toBinaryString(num).toCharArray();
    //八进制
    //Integer.toOctalString(num);
    //十六进制
    //Integer.toHexString(num);
    //转十六进制
    //Integer.parseInt("ab",16);
    for (char c : nums) {
      if (c == '1') {
        count++;
      }
    }
    System.out.println(count);
  }

  private static void countTwo3(Integer num) {
    int count = 0;
    while (num > 0) {
      if (num % 2 == 1) {
        count++;
      }
      num = num / 2;
    }
    System.out.println(count);
  }

  /**
   * 题目描述: 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
   * <p>
   * 输入描述: 输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），表示小张手上的空汽水瓶数。n=0表示输入结束，你的程序不应当处理这一行。
   * <p>
   * 输出描述: 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
   */
  public static void main3(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      howManyBots(sc.nextInt());
    }
  }

  private static void howManyBots(Integer num) {
    if (num == null || num == 0) {
      return;
    }
    int count = 0;
    while (num >= 3) {
      int cur = num / 3;
      count += cur;
      num -= 2 * cur;
    }
    if (num == 2) {
      count += 1;
    }
    System.out.println(count);
  }

  /**
   * 题目描述 首先输入要输入的整数个数n，然后输入n个整数。输出为n个整数中负数的个数，和所有正整数的平均值，结果保留一位小数。
   * <p>
   * 输入描述: 首先输入一个正整数n， 然后输入n个整数。
   * <p>
   * 输出描述: 输出负数的个数，和所有正整数的平均值
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int n = Integer.parseInt(sc.nextLine());
      int negativeCount = 0;
      int positiveCount = 0;
      int positiveSum = 0;
      String[] strs = sc.nextLine().split(" ");
      for (String str : strs) {
        int num = Integer.parseInt(str);
        if (num < 0) {
          negativeCount++;
        } else if (num > 0) {
          positiveCount++;
          positiveSum += num;
        }
      }
      System.out.printf("%d %.1f\n", negativeCount, positiveSum * 1.0 / positiveCount);
    }
  }

}
