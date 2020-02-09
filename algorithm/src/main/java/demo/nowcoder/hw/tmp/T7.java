package demo.nowcoder.hw.tmp;

import java.util.Scanner;

/**
 * 成绩排序
 *
 * @author leishiguang
 * @since v1.0
 */
public class T7 {

  /**
   * 题目：输入任意（用户，成绩）序列，可以获得成绩从高到低或从低到高的排列,相同成绩都按先录入排列在前的规则处理
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.nextLine();
      //获取要排序的人的个数
      int count = Integer.parseInt(str);
      //获取输入的排序方法(升序还是降序)
      int sortType = Integer.parseInt(sc.nextLine());

      String[] users = new String[count];
      int[] scores = new int[count];
      for (int i = 0; i < count; i++) {
        String line = sc.nextLine();
        String[] parts = line.split(" ");
        String user = parts[0];
        int score = Integer.parseInt(parts[1]);
        if (sortType == 0) {
          int j;
          for (j = i - 1; j >= 0; j--) {
            if (scores[j] < score) {
              scores[j + 1] = scores[j];
              users[j + 1] = users[j];
            } else {
              break;
            }
          }
          scores[j + 1] = score;
          users[j + 1] = user;
        } else {
          int j = 0;
          for (j = i - 1; j >= 0; j--) {
            if (scores[j] > score) {
              scores[j + 1] = scores[j];
              users[j + 1] = users[j];
            } else {
              break;
            }
          }
          scores[j + 1] = score;
          users[j + 1] = user;
        }
      }
      for (int i = 0; i < count; i++) {
        System.out.println(users[i] + " " + scores[i]);
      }
    }
  }
}
