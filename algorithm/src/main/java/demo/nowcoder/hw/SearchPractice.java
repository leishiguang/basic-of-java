package demo.nowcoder.hw;

import java.util.Scanner;

/**
 * 搜索算法的练习
 *
 * @author leishiguang
 * @since v1.0
 */
public class SearchPractice {

  /**
   * 因式分解，题目描述 分子为1的分数称为埃及分数。现输入一个真分数(分子比分母小的分数，叫做真分数)，请将该分数分解为埃及分数。如：8/11 = 1/2+1/5+1/55+1/110。
   */
  public static void main1(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String realFract = sc.nextLine();
      String[] parts = realFract.split("/");
      int a = Integer.parseInt(parts[0]);
      int b = Integer.parseInt(parts[1]);
      convertRealFractToEgpytFract(a, b);
    }
  }

  /**
   * 贪心算法（又称贪婪算法）是指，在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，他所做出的是在某种意义上的局部最优解 [Baidu百科]
   * <p>
   * 由于贪心算法的每次都是贪婪选择的特性，我们可以用7/8来举例，小一点的埃及分数是怎么算出来的，你完全可以举例，前提是埃及分数必须要 1/x 的形式。
   */
  public static void convertRealFractToEgpytFract(int a, int b) {
    StringBuilder result = new StringBuilder();
    int c = 0;
    while (a != 1) {
      if (b % (a - 1) == 0) {
        result.append("1/").append(b / (a - 1)).append('+');
        a = 1;
      } else {
        c = b / a + 1;
        result.append("1/").append(c).append('+');
        a = a * c - b;
        b = c * b;
        if (b % a == 0) {
          b = b / a;
          a = 1;
        }
      }
    }
    result.append("1/").append(b);
    System.out.println(result);
  }

  /**
   * todo 采用迭代加深搜索：从小到大枚举深度上限。
   * <p>
   * 迭代加深搜索，实质上是限定下界的深度优先搜索。即首先允许深度优先搜索K层，若没有发现可行解，再将K+1后
   * <p>
   * 重复以上步骤搜索，直到搜索到可行解。
   *
   * <p>
   * 任何一个真分数a/b（且a小于b）一定能表示成埃及分数之和，因此一定能搜索到解。但是不剪枝的话是会T掉的哦~~ 按a/b可拆分成的单位分数的个数进行循环搜索。
   * <p>
   * 1）先将a/b分子分母消去因子，化为最简形式。
   * <p>
   * 2）如果a=1则直接输出b。
   * <p>
   * 3）否则从拆分成2个项开始尝试。如果2个单位分数无法拼凑成a/b，则试3个……依此类推。那么我们搜索得到的第一个解一定会满足加数个数最少这个要求。
   * <p>
   * 4）如果当加数个数等于n项，且当前正在试探第m项（m大于等于1，且小于等于n）的时候，因此应从最小分母开始试探。
   */
  private static void convertRealFractToEgpytFractWithDFS(int a, int b) {

  }

  /**
   * 计算24点，不包含括号... 详细说明：
   * <p>
   * 1.运算只考虑加减乘除运算，没有阶乘等特殊运算符号，友情提醒，整数除法要当心；
   * <p>
   * 2.牌面2~10对应的权值为2~10, J、Q、K、A权值分别为为11、12、13、1；
   * <p>
   * 3.输入4张牌为字符串形式，以一个空格隔开，首尾无空格；如果输入的4张牌中包含大小王，则输出字符串“ERROR”，表示无法运算；
   * <p>
   * 4.输出的算式格式为4张牌通过+-* / 四个运算符相连，中间无空格，4张牌出现顺序任意，只要结果正确；
   * <p>
   * 5.输出算式的运算顺序从左至右，不包含括号，如1+2+3*4的结果为24
   * <p>
   * 6.如果存在多种算式都能计算得出24，只需输出一种即可，如果无法得出24，则输出“NONE”表示无解。
   *
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String[] str = sc.nextLine().split(" ");
      if (compute24CheckUnpass(str)) {
        System.out.println("ERROR");
      } else {
        int[] nums = new int[str.length];
        for (int i = 0; i < str.length; i++) {
          nums[i] = cardToNumber(str[i]);
        }
        compute24(nums);
      }
    }
  }

  private static boolean compute24CheckUnpass(String[] strs) {
    boolean result = false;
    for (String tmp : strs) {
      result = result || "joker".equals(tmp) || "JOKER".equals(tmp);
    }
    return result;
  }

  private static Integer cardToNumber(String str) {
    switch (str) {
      case "10":
        return 10;
      case "A":
        return 1;
      case "J":
        return 11;
      case "Q":
        return 12;
      case "K":
        return 13;
      default:
        return Integer.parseInt(str);
    }
  }

  private static String numberToCard(int num) {
    switch (num) {
      case 10:
        return "10";
      case 1:
        return "A";
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      default:
        return String.valueOf(num);
    }
  }

  private String[] math = new String[]{"+", "-", "*", "/"};

  /**
   * 计数24点，不包含括号，暴力搜索，穷举所有直到找到...
   */
  private static void compute24(int[] nums) {
    //记录乱序结果
    int[] result = new int[4];
    //记录算数方式
    String[] method = new String[3];
    //记录是否找到了方式
    boolean flag = false;
    flag = check(result, method);

    if (flag) {
      //找到结果
    } else {
      //未找到
    }
  }

  /**
   * 计算是否符合结果
   */
  private static boolean check(int[] nums, String[] method) {
    if (nums.length != 4 || method.length != 3) {
      return false;
    }
    float result = nums[0];
    for (int i = 0; i < 3; i++) {
      result = mathDo(result, nums[i], method[i]);
    }
    return result == 24;
  }

  private static float mathDo(float a, float b, String math) {
    switch (math) {
      case "+":
        return a + b;
      case "-":
        return a - b;
      case "*":
        return a * b;
      case "/":
        return a / b;
      default:
        throw new UnsupportedOperationException("");
    }
  }

}
