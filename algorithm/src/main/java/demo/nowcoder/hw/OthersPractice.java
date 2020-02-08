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
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()){
      printInteger(scanner.nextFloat());
    }
  }

  private static void printInteger(float floatNum) {
    int num = (int) (floatNum / 1);
    int result = floatNum - num >= 0.5 ? num + 1 : num;
    System.out.println(result);
  }


}
