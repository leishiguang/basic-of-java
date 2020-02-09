package demo.nowcoder.hw.tmp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 人民币转换 题目描述
 * <p>
 * 1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整等字样填写。（30分）
 * <p>
 * 2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如￥ 532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。（30分）
 * <p>
 * 3、阿拉伯数字中间有“0”时，中文大写要写“零”字，阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如￥6007.14，应写成“人民币陆仟零柒元壹角肆分“。（
 *
 * @author leishiguang
 * @since v1.0
 */
public class T9 {

  private static final Map<Integer, String> DICTIONARY = new HashMap<>();

  private static final Map<Integer, String> UNIT = new HashMap<>();

  static {
    DICTIONARY.put(0, "零");
    DICTIONARY.put(1, "壹");
    DICTIONARY.put(2, "贰");
    DICTIONARY.put(3, "叁");
    DICTIONARY.put(4, "肆");
    DICTIONARY.put(5, "伍");
    DICTIONARY.put(6, "陆");
    DICTIONARY.put(7, "柒");
    DICTIONARY.put(8, "捌");
    DICTIONARY.put(9, "玖");

    UNIT.put(10, "拾");
    UNIT.put(100, "佰");
    UNIT.put(1000, "仟");
    UNIT.put(10000, "万");
    UNIT.put(100000000, "亿");
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String input = sc.nextLine();
      String[] inputArray = input.split("\\.");
      int n = Integer.parseInt(inputArray[0]);
      StringBuilder result = new StringBuilder();
      String pre = "";
      int temp = 1;
      if (n > 0) {
        result.append("元");
        if (n > 9 && n < 20) {
          result.insert(0, DICTIONARY.get(n % 10));
          result.insert(0, UNIT.get(10));
        } else {
          while (n > 0) {
            int current = n % 10;
            String unit = UNIT.get(temp);
            if (unit != null && current != 0) {
              result.insert(0, unit);
              if ("万".equals(unit) && n > 9 && n < 20) {
                result.insert(0, DICTIONARY.get(n % 10));
                result.insert(0, UNIT.get(10));
                break;
              }
            }

            if (!"零".equals(pre) || current != 0) {
              result.insert(0, DICTIONARY.get(current));
            }

            n = n / 10;
            temp *= 10;

            pre = DICTIONARY.get(current);
          }
        }
      }

      if (inputArray.length == 2) {
        char[] chars = inputArray[1].toCharArray();
        String value = DICTIONARY.get(Integer.parseInt(String.valueOf(chars[0])));
        if (value != null && !"零".equals(value)) {
          result.append(value).append("角");
        }

        value = DICTIONARY.get(Integer.parseInt(String.valueOf(chars[1])));
        if (value != null && !"零".equals(value)) {
          result.append(value).append("分");
        }
      } else {
        result.append("整");
      }

      System.out.println("人民币" + result);
    }
  }

}
