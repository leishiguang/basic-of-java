package demo.nowcoder.hw;

import java.util.Scanner;

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
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()){
      printPrime(scanner.nextLong());
    }
  }

  private static void printPrime(long num){
    StringBuilder stringBuilder = new StringBuilder();
    int index = 2;
    while (index <= num){
      if(num % index == 0){
        stringBuilder.append(index).append(" ");
        num = num / index;
      }else{
        index += 1;
      }
    }
    System.out.println(stringBuilder);
  }
}
