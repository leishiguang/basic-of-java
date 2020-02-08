package demo.nowcoder.hw;

import java.util.*;

/**
 * 字符串类的算法练习
 *
 * @author leishiguang
 * @since v1.0
 */
public class StringsPractice {

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
  public static void main8(String[] args) {
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

  /**
   * 题目描述: 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
   * <p>
   * 输入描述: 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
   * <p>
   * 输出描述: 删除字符串中出现次数最少的字符后的字符串。
   */
  public static void main9(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      deleteMinChar(sc.nextLine());
    }
  }

  private static void deleteMinChar(String str) {
    if (str.length() > 20) {
      return;
    }
    int[] sums = new int[26];
    int min = Integer.MAX_VALUE;
    for (char c : str.toCharArray()) {
      int index = c - 'a';
      sums[index] += 1;
      min = Math.min(min, sums[index]);
    }
    for (int i = 0; i < sums.length; i++) {
      if (min == sums[i]) {
        str = str.replaceAll(String.valueOf((char) (i + 'a')), "");
      }
    }
    System.out.println(str);
  }

  /**
   * 密码破解，字符串按规则替换
   */
  public static void main10(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      replaceString(sc.nextLine());
    }
  }

  private static void replaceString(String str) {
    StringBuilder result = new StringBuilder();
    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        result.append(c);
      } else if (Character.isLowerCase(c)) {
        result.append(replaceStringLowerCase(c));
      } else if (Character.isUpperCase(c)) {
        result.append(replaceStringUpcaseCase(c));
      }
    }
    System.out.println(result);
  }

  private static String replaceStringLowerCase(char c) {
    switch (c) {
      case 'a':
      case 'b':
      case 'c':
        return "2";
      case 'd':
      case 'e':
      case 'f':
        return "3";
      case 'g':
      case 'h':
      case 'i':
        return "4";
      case 'j':
      case 'k':
      case 'l':
        return "5";
      case 'm':
      case 'n':
      case 'o':
        return "6";
      case 'p':
      case 'q':
      case 'r':
      case 's':
        return "7";
      case 't':
      case 'u':
      case 'v':
        return "8";
      case 'w':
      case 'x':
      case 'y':
      case 'z':
        return "9";
      default:
        return "";
    }
  }

  private static String replaceStringUpcaseCase(char c) {
    c = Character.toLowerCase(c);
    return c == 'z' ? "a" : String.valueOf((char) (c + 1));
  }

  /**
   * 题目描述：在计算机中，通配符一种特殊语法，广泛应用于文件搜索、数据库、正则表达式等领域。现要求各位实现字符串通配符的算法。
   * <p>
   * 要求：实现如下2个通配符：
   * <p>
   * <li>*：匹配0个或以上的字符（字符由英文字母和数字0-9组成，不区分大小写。下同）</>
   * <p>
   * <li>？：匹配1个字符</>
   * <p>
   * 输入： 通配符表达式； 一组字符串。
   * <p>
   * 输出： 返回匹配的结果，正确输出true，错误输出false
   */
  public static void main11(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String pattern = scanner.nextLine();
      String str = scanner.hasNext() ? scanner.nextLine() : "";
      matchPattern(str, pattern);
    }
  }

  private static void matchPattern(String str, String pattern) {
    pattern = pattern.replaceAll("\\?", "[0-9a-zA-Z]");
    pattern = pattern.replaceAll("\\*", "[0-9a-zA-Z]*");
    System.out.println(str.matches(pattern));
  }

  /**
   * 题目描述: 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
   * <p>
   * 输入描述: 输入一个int型整数，示例：9876673
   * <p>
   * 输出描述: 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数，示例：37689
   */
  public static void main12(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      revertAndReplace(sc.nextLine());
    }
  }

  private static void revertAndReplace(String str) {
    boolean[] map = new boolean[10];
    StringBuilder result = new StringBuilder();
    for (int i = str.length() - 1; i >= 0; i--) {
      int num = str.charAt(i) - '0';
      if (!map[num]) {
        map[num] = true;
        result.append(num);
      }
    }
    System.out.println(result);
  }

  /**
   * 题目描述： 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
   * <p>
   * 输入： 合法坐标为A(或者D或者W或者S) + 数字（两位以内） 坐标之间以;分隔。 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
   * <p>
   * 输出：最终所处的坐标
   */
  public static void main13(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      computeAll(sc.nextLine());
    }
  }

  private static void computeAll(String str) {
    String[] arrs = str.split(";");
    int x = 0;
    int y = 0;
    for (String arr : arrs) {
      if (!check(arr)) {
        continue;
      }
      int val = arr.charAt(1) - '0';
      if (arr.length() == 3) {
        val = val * 10 + (arr.charAt(2) - '0');
      }
      switch (arr.charAt(0)) {
        case 'A':
          x -= val;
          break;
        case 'D':
          x += val;
          break;
        case 'W':
          y += val;
          break;
        case 'S':
          y -= val;
          break;
        default:
          break;
      }
    }
    System.out.println(x + "," + y);
  }

  /**
   * 判断是否合法坐标，3位长度，开始为AWSD，两位数字
   */
  private static boolean check(String str) {
    if (str == null || str.length() == 0 || str.length() > 3) {
      return false;
    }
    return str.matches("[ADWS][0-9]") || str.matches("[ADWS][0-9][0-9]");
  }

  /**
   * 题目描述
   * <p>
   * 密码要求: 1.长度超过8位 2.包括大小写字母.数字.其它符号,以上四种至少三种 3.不能有相同长度超2的子串重复 说明:长度超过2的子串
   * <p>
   * 输入描述: 一组或多组长度超过2的子符串。每组占一行
   * <p>
   * 输出描述: 如果符合要求输出：OK，否则输出NG
   */
  public static void main14(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      goodPassword(sc.nextLine());
    }
  }

  private static void goodPassword(String str) {
    String good = "OK";
    String bad = "NG";
    if (str.length() < 8) {
      System.out.println(bad);
      return;
    }
    boolean[] map = new boolean[4];
    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        map[0] = true;
      } else if (Character.isUpperCase(c)) {
        map[1] = true;
      } else if (Character.isLowerCase(c)) {
        map[2] = true;
      } else {
        map[3] = true;
      }
    }
    for (boolean m : map) {
      if (!m) {
        System.out.println(bad);
        return;
      }
    }
    for (int i = 0; i < str.length() - 3; i++) {
      String str1 = str.substring(i, i + 3);
      String str2 = str.substring(i + 3);
      if (str2.contains(str1)) {
        System.out.println(bad);
        return;
      }
    }
    System.out.println(good);
  }

  /**
   * 找到最长回文
   */
  public static void main15(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.nextLine();
      maxReback1(str);
      maxReback2(str);
    }
  }

  /**
   * 找到最长回文，中间指针解法
   */
  private static void maxReback1(String str) {
    int max = 1;
    //处理基数回文的情况
    for (int center = 1; center <= str.length() - 2; center++) {
      int i = 1;
      int left = center - i;
      int right = center + i;
      while (left >= 0 && right <= str.length() - 1) {
        if (str.charAt(left) != str.charAt(right)) {
          break;
        }
        i++;
        left = center - i;
        right = center + i;
      }
      max = Math.max(max, i * 2 - 1);
    }
    //处理偶数回文的情况
    for (int mid = 0; mid <= str.length() - 2; mid++) {
      int i = 0;
      int left = mid;
      int right = mid + 1;
      while (left >= 0 && right <= str.length() - 1) {
        if (str.charAt(left) != str.charAt(right)) {
          break;
        }
        i++;
        left = mid - i;
        right = mid + i + 1;
      }
      max = Math.max(max, i * 2);
    }
    System.out.println(max);
  }

  /**
   * 找到最长回文，动态规划解法
   */
  private static void maxReback2(String a) {
    String b = new StringBuilder(a).reverse().toString();
    int aLen = a.length() + 1;
    int bLen = b.length() + 1;
    int max = 0;
    int[][] dp = new int[aLen][bLen];
    for (int i = 1; i < aLen; i++) {
      for (int j = 1; j < bLen; j++) {
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = 0;
        }
        if (dp[i][j] > max) {
          max = dp[i][j];
        }
      }
    }
    System.out.println(max);
  }

  /**
   * 字符串合并处理。题目描述： 按照指定规则对输入的字符串进行处理。
   * <p>
   * 详细描述： 将输入的两个字符串合并。
   * <p>
   * 对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。这里的下标意思是字符在字符串中的位置。
   * <p>
   * 对排序后的字符串进行操作，如果字符为‘0’——‘9’或者‘A’——‘F’或者‘a’——‘f’，则对他们所代表的16进制的数进行BIT倒序的操作，并转换为相应的大写字符。如字符为‘4’，为0100b，则翻转后为0010b，也就是2。转换后的字符为‘2’；
   * 如字符为‘7’，为0111b，则翻转后为1110b，也就是e。转换后的字符为大写‘E’。
   * <p>
   * 举例：输入str1为"dec"，str2为"fab"，合并为“decfab”，分别对“dca”和“efb”进行排序，排序后为“abcedf”，转换后为“5D37BF”
   * <p>
   * 示例：输入 dec fab，输出 5D37BF
   */
  public static void main16(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      joinAndChange(sc.nextLine());
    }
  }

  private static void joinAndChange(String tmpStr) {
    String joinedStr = jointAndSort(tmpStr);
    StringBuilder result = new StringBuilder();
    for (char c : joinedStr.toCharArray()) {
      result.append(charRevertAndChange(c));
    }
    System.out.println(result);
  }

  /**
   * 合并与排序
   */
  private static String jointAndSort(String tmpStr) {
    String[] arr = tmpStr.split(" ");
    String str = arr[0] + arr[1];
    StringBuilder str1 = new StringBuilder();
    StringBuilder str2 = new StringBuilder();
    boolean key = true;
    for (char c : str.toCharArray()) {
      if (key) {
        str1.append(c);
      } else {
        str2.append(c);
      }
      key = !key;
    }
    char[] c1 = str1.toString().toCharArray();
    char[] c2 = str2.toString().toCharArray();
    Arrays.sort(c1);
    Arrays.sort(c2);
    StringBuilder jointedStrBuilder = new StringBuilder();
    for (int i = 0; i < c2.length; i++) {
      jointedStrBuilder.append(c1[i]).append(c2[i]);
    }
    if (c1.length > c2.length) {
      jointedStrBuilder.append(c1[c1.length - 1]);
    }
    return jointedStrBuilder.toString();
  }

  /**
   * 二进制转换，十六进制转换
   */
  private static String charRevertAndChange(char c) {
    int num = 0;
    if (Character.isUpperCase(c) && c <= 'F') {
      num = c - 'A' + 10;
    } else if (Character.isLowerCase(c) && c <= 'f') {
      num = c - 'a' + 10;
    } else if (Character.isDigit(c)) {
      num = c - '0';
    } else {
      return String.valueOf(c);
    }
    StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(num));
    //这儿补足长度为4位
    if (binaryString.length() < 4) {
      int n = 4 - binaryString.length();
      do {
        binaryString.insert(0, "0");
      } while (--n > 0);
    }
    String revertString = new StringBuilder(binaryString.toString()).reverse().toString();
    int revertValue = Integer.parseInt(revertString, 2);
    char revertChar = (char) (revertValue >= 10 ? 'A' + revertValue - 10 : revertValue + '0');
    return String.valueOf(revertChar);
  }


}