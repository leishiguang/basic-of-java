package demo.nowcoder.hw;

import java.util.Scanner;

/**
 * 字符串类的算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class StringPractice {

  /**
   * 题目描述：计算字符串最后一个单词的长度，单词以空格隔开。 输入描述：一行字符串，非空，长度小于5000。 输出描述:整数N，最后一个单词的长度。
   */
  public static void main1(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s;
    while (sc.hasNextLine()) {
      s = sc.nextLine();
      System.out.println(s.length() - 1 - s.lastIndexOf(" "));
    }
  }


  /**
   * 题目描述：写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。 输入描述：第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。
   * 输出描述：输出输入字符串中含有该字符的个数。
   */
  public static void main2(String[] args) {
    Scanner input = new Scanner(System.in);
    String all = input.next().toUpperCase();
    char item = input.next().toUpperCase().charAt(0);
    int i = 0;
    for (char c : all.toCharArray()) {
      if (c == item) {
        i++;
      }
    }
    System.out.print(i);
  }

  /**
   * 题目描述： •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组； •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
   * 输入描述：连续输入字符串(输入2次,每个字符串长度小于100) 输出描述: 输出到长度为8的新字符串数组
   */
  public static void main3(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      split(sc.nextLine());
    }
  }

  public static void split(String str) {
    if (str == null || str.length() == 0) {
      return;
    }
    while (str.length() >= 8) {
      System.out.println(str.substring(0, 8));
      str = str.substring(8);
    }
    if (str.length() > 0) {
      str = str + "00000000";
      System.out.println(str.substring(0, 8));
    }
  }

}
