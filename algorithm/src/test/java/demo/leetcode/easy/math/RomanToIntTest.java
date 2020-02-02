package demo.leetcode.easy.math;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试类
 *
 * @author leishiguang
 * @since v1.0
 */
class RomanToIntTest {

  private List<String> cases = new ArrayList<>();
  private List<Integer> results = new ArrayList<>();

  @BeforeEach
  void setUp() {
    cases.add("MCMXCIV");
    results.add(1994);
    cases.add("LVIII");
    results.add(58);
    cases.add("IX");
    results.add(9);
    cases.add("IV");
    results.add(4);
    cases.add("III");
    results.add(3);
    cases.add("XXVII");
    results.add(27);
    cases.add("XII");
    results.add(12);
  }

  @Test
  void go() {
    RomanToInt romanToInt = new RomanToInt();
    for (int i = 0; i < cases.size(); i++) {
      assertEquals(results.get(i),Integer.valueOf(romanToInt.romanToInt(cases.get(i))));
    }
  }
}