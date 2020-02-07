package demo.nowcoder.hw;

import java.util.*;

/**
 * 字符串类的算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class StringPractice {

  /**
   * 题目描述：计算字符串最后一个单词的长度，单词以空格隔开。
   * <p>
   * 输入描述：一行字符串，非空，长度小于5000。
   * <p>
   * 输出描述:整数N，最后一个单词的长度。
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
   * 题目描述：写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
   * <p>
   * 输入描述：第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。
   * <p>
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
   * <p>
   * 输入描述：连续输入字符串(输入2次,每个字符串长度小于100)
   * <p>
   * 输出描述: 输出到长度为8的新字符串数组
   */
  public static void main3(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      split(sc.nextLine());
    }
  }

  private static void split(String str) {
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

  /**
   * 题目描述：写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
   * <p>
   * 输入描述: 输入一个十六进制的数值字符串。示例：0xA
   * <p>
   * 输出描述: 输出该数值的十进制字符串。示例：10
   */
  public static void main4(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      toTen(sc.nextLine());
    }
  }

  private static void toTen(String str) {
    str = str.toUpperCase();
    long result = 0;
    for (char c : str.toCharArray()) {
      result *= 16;
      result += toTen(c);
    }
    System.out.println(result);
  }

  private static int toTen(char c) {
    switch (c) {
      case '1':
        return 1;
      case '2':
        return 2;
      case '3':
        return 3;
      case '4':
        return 4;
      case '5':
        return 5;
      case '6':
        return 6;
      case '7':
        return 7;
      case '8':
        return 8;
      case '9':
        return 9;
      case 'A':
        return 10;
      case 'B':
        return 11;
      case 'C':
        return 12;
      case 'D':
        return 13;
      case 'E':
        return 14;
      case 'F':
        return 15;
      default:
        return 0;
    }
  }

  /**
   * 题目描述 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
   * <p>
   * 输入描述: 输入N个字符，字符在ACSII码范围内。示例：abc
   * <p>
   * 输出描述: 输出范围在(0~127)字符的个数。输出：3
   */
  public static void main5(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      //countString(sc.nextLine());
      countString2(sc.nextLine());
    }
  }

  private static void countString(String str) {
    Byte[] bytes = new Byte[128];
    Arrays.fill(bytes, (byte) 0);
    int count = 0;
    for (char c : str.toCharArray()) {
      if (bytes[c] == 0) {
        count++;
        bytes[c] = 1;
      }
    }
    System.out.println(count);
  }

  private static void countString2(String str) {
    Byte[] bytes = new Byte[128];
    for (char c : str.toCharArray()) {
      bytes[c] = 1;
    }
    int count = 0;
    for (Byte b : bytes) {
      if (b != null) {
        count++;
      }
    }
    System.out.println(count);
  }

  /**
   * 题目描述 描述： 输入一个整数，将这个整数以字符串的形式逆序输出。 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
   * <p>
   * 输入描述: 输入一个int整数
   * <p>
   * 输出描述: 将这个整数以字符串的形式逆序输出
   */
  public static void main6(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      revertInt(sc.nextLine());
    }
  }


  private static void revertInt(String str) {
    StringBuilder result = new StringBuilder();
    char[] chars = str.toCharArray();
    for (int i = str.length() - 1; i >= 0; i--) {
      result.append(chars[i]);
    }
    System.out.println(result.toString());
  }

  /**
   * 题目描述： 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I” 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
   */
  public static void main7(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      revertLetters(sc.nextLine());
    }
  }

  private static void revertLetters(String str) {
    String[] strArrays = str.split(" ");
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = strArrays.length - 1; i >= 0; i--) {
      stringBuilder.append(strArrays[i]).append(" ");
    }
    System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
  }

  /**
   * 题目描述：给定n个字符串，请对n个字符串按照字典序排列。
   * <p>
   * 输入描述：输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
   * <p>
   * 输出描述：数据输出n行，输出结果为按照字典序排列的字符串。
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int lines = Integer.parseInt(sc.nextLine());
      List<String> strs = new ArrayList<>();
      while (--lines >= 0 && sc.hasNext()) {
        strs.add(sc.nextLine());
      }
      Collections.sort(strs);
      for (String str : strs) {
        System.out.println(str);
      }
    }
  }
}
