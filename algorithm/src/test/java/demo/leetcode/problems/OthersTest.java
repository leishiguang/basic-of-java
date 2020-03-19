package demo.leetcode.problems;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 其它的一些算法，测试类
 *
 * @author leishiguang
 * @since v1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OthersTest {

  private List<int[]> cases;
  private List<Integer> result;

  @BeforeAll
  void beforeAll() {

  }

  void before1() {
    cases = new ArrayList<>();
    result = new ArrayList<>();
    cases.add(new int[]{1, -6, 2, 3});
    result.add(5);
    cases.add(new int[]{-1, -2, -3, -4});
    result.add(-1);
    cases.add(new int[]{1, 1, 6});
    result.add(8);
    cases.add(new int[]{1, -6, 2, 3, -6, 9});
    result.add(9);
  }

  @Test
  void go() {
    before1();
    Others others = new Others();
    for (int i = 0; i < cases.size(); i++) {
      assertEquals((int) result.get(i), others.maxSubArrayWithDp(cases.get(i)));
      assertEquals((int) result.get(i), others.maxSubArray(cases.get(i)));
    }
  }
}