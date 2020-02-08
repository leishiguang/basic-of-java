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
    for(char c:nums){
      if(c == '1'){
        count ++;
      }
    }
    System.out.println(count);
  }

  private static void countTwo3(Integer num){
    int count = 0;
    while (num > 0){
      if(num % 2 == 1){
        count ++;
      }
      num = num / 2;
    }
    System.out.println(count);
  }

}
