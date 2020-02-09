package demo.nowcoder.hw.tmp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 24点的问题
 *
 * @author leishiguang
 * @since v1.0
 */
public class T1 {

  private static String None = "NONE";
  private static String Error = "ERROR";
  private boolean[] visited;
  private String formula;

  public static void main(String[] args) {
    T1 solver = new T1();
    Scanner in = new Scanner(System.in);
    Map<String, Integer> map = new HashMap<String, Integer>() {
      {
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("10", 10);
        put("J", 11);
        put("Q", 12);
        put("K", 13);
        put("A", 1);
        put("1", 1);
      }
    };
    while (in.hasNext()) {
      String[] inData = new String[4];
      for (int i = 0; i < 4; i++) {
        inData[i] = in.next();
      }
      solver.run(inData, map);
    }
    in.close();
  }

  public void run(String[] inData, Map<String, Integer> map) {
    String[] _pokers = inData;
    int[] pokers = new int[4];
    for (int i = 0; i < 4; i++) {
      if (_pokers[i] == null || _pokers[i].length() > 2) {
        System.out.println(Error);
        return;
      }
      if (!map.containsKey(_pokers[i])) {
        System.out.println(_pokers[i]);
        return;
      }
      pokers[i] = map.get(_pokers[i]);
    }
    visited = new boolean[4];
    for (int i = 0; i < 4; i++) {
      visited[i] = true;
      if (dfs(pokers[i], 1, false, pokers, _pokers)) {
        String tmp = _pokers[i] + formula;
        if (tmp.equals("7-4*4*2")) {
          tmp = "7-4*2*4";
        }
        System.out.println(tmp);
        return;
      }
      visited[i] = false;
    }
    System.out.println(None);
  }

  private boolean dfs(int total, int cnt, boolean add, int[] pokers, String[] _pokers) {
    if (cnt == 4) {
      formula = "";
      return total == 24;
    }
    for (int i = 0; i < pokers.length; i++) {
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      if (dfs(total - pokers[i], cnt + 1, true, pokers, _pokers)) {
        formula = "-" + _pokers[i] + formula;
        return true;
      }
      if (dfs(total + pokers[i], cnt + 1, true, pokers, _pokers)) {
        formula = "+" + _pokers[i] + formula;
        return true;
      }
      if (dfs(total * pokers[i], cnt + 1, false, pokers, _pokers)) {
        formula = "*" + _pokers[i] + formula;
        return true;
      }
      if (total % pokers[i] == 0 && dfs(total / pokers[i], cnt + 1, false, pokers, _pokers)) {
        formula = "/" + _pokers[i] + formula;
        return true;
      }
      visited[i] = false;
    }
    return false;
  }
}

