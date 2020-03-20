package demo.leetcode.medium.strings;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * 测试三数之和
 *
 * @author leishiguang
 * @since v1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ThreeSumTest {

  private List<int[]> cases;

  @BeforeAll
  void beforeAll() {
    cases = new ArrayList<>();
    cases.add(new int[]{-2, -1, -1, 0, 2});
    cases.add(new int[]{-1, 0, 1, -1, 1});
    cases.add(new int[]{-1, 0, 1, 2, -1, 1, 0, 0, -4});
    cases.add(new int[]{-6, -5, -4, 2, 1, 3, 4, 0, 1, 2, -1, 1, 0, 0, -4});
    cases.add(new int[]{0, 0, 0, 0});
    cases.add(new int[]{0, 0, 0});
    cases.add(new int[]{1, 2, 3});
    cases.add(new int[]{1, 2, -3});
  }


  @Test
  void go() {
    ThreeSum threeSum = new ThreeSum();
    for (int[] c : cases) {
      threeSum.threeSum(c);
      threeSum.threeSum2(c);
    }
  }
}