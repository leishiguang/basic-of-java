package demo.nowcoder.hw.tmp;

import java.util.Scanner;

/**
 * 整数与IP地址间的转换
 * <p>
 * 题目描述 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成 一个长整数。 举例：一个ip地址为10.0.3.193 每段数字
 * 相对应的二进制数 10                   00001010 0                    00000000 3 00000011 193
 * 11000001
 * <p>
 * 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。
 * 的每段可以看成是一个0-255的整数，需要对IP地址进行校验
 * <p>
 * 输入 1 输入IP地址 2 输入10进制型的IP地址
 *
 * @author leishiguang
 * @since v1.0
 */
public class T8 {

  public static void change1(String str) {
    String[] data1 = str.split("\\.");

    data1[0] = Integer.toBinaryString(Integer.parseInt(data1[0]));
    data1[1] = Integer.toBinaryString(Integer.parseInt(data1[1]));
    data1[2] = Integer.toBinaryString(Integer.parseInt(data1[2]));
    data1[3] = Integer.toBinaryString(Integer.parseInt(data1[3]));

    while (data1[0].length() < 8) {
      data1[0] = "0" + data1[0];
    }
    while (data1[1].length() < 8) {
      data1[1] = "0" + data1[1];
    }
    while (data1[2].length() < 8) {
      data1[2] = "0" + data1[2];
    }
    while (data1[3].length() < 8) {
      data1[3] = "0" + data1[3];
    }

    long sum = 0;
    for (String s : data1) {
      for (int j = 0; j < data1[0].length(); j++) {
        sum = sum * 2 + (s.charAt(j) - '0');
      }
    }
    System.out.println(sum);

  }

  public static void change2(String str) {
    long data2 = Long.parseLong(str);
    String bindata2 = Long.toBinaryString(data2);
    String[] data = new String[4];
    data[0] = bindata2.substring(0, bindata2.length() - 3 * 8);
    data[1] = bindata2.substring(data[0].length(), data[0].length() + 8);
    data[2] = bindata2
        .substring(data[0].length() + data[1].length(), data[0].length() + data[1].length() + 8);
    data[3] = bindata2.substring(bindata2.length() - 8, bindata2.length());

    System.out.print(Integer.valueOf(data[0], 2) + ".");
    System.out.print(Integer.valueOf(data[1], 2) + ".");
    System.out.print(Integer.valueOf(data[2], 2) + ".");
    System.out.println(Integer.valueOf(data[3], 2));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.nextLine();
      change1(str);
      str = sc.nextLine();
      change2(str);
    }
  }
}
