package demo.leetcode.easy.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import demo.leetcode.easy.array.ContainsDuplicate;
import demo.leetcode.easy.strings.LongestCommonPrefix;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 测试类
 *
 * @author leishiguang
 * @since v1.0
 */
public class IsPowerOfThreeTest {

  private List<Integer> cases = new ArrayList<>();
  private List<Boolean> results = new ArrayList<>();

  @BeforeEach
  void setUp() {
    cases.add(30);
    results.add(false);
    cases.add(19684);
    results.add(false);
    cases.add(1);
    results.add(true);
    cases.add(27);
    results.add(true);
    cases.add(0);
    results.add(false);
    cases.add(9);
    results.add(true);
    cases.add(45);
    results.add(false);
  }

  @Test
  void go() {
    IsPowerOfThree isPowerOfThree = new IsPowerOfThree();
    for (int i = 0; i < cases.size(); i++) {
      assertEquals(results.get(i),isPowerOfThree.isPowerOfThree(cases.get(i)));
      assertEquals(results.get(i),isPowerOfThree.isPowerOfThree2(cases.get(i)));
      assertEquals(results.get(i),isPowerOfThree.isPowerOfThree3(cases.get(i)));
    }
  }
}
