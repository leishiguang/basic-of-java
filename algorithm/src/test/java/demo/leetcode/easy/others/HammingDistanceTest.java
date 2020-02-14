package demo.leetcode.easy.others;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试
 *
 * @author leishiguang
 * @since v1.0
 */
class HammingDistanceTest {

  private List<Integer[]> cases;
  private List<Integer> result;

  @BeforeEach
  void setUp() {
    cases = new ArrayList<>();
    result = new ArrayList<>();
    cases.add(new Integer[]{4,2});
    result.add(2);
    cases.add(new Integer[]{0,1});
    result.add(1);
    cases.add(new Integer[]{1, 4});
    result.add(2);

  }

  @Test
  void go() {
 HammingDistance t = new HammingDistance();
    for (int i = 0; i < cases.size(); i++) {
      Integer[] param = cases.get(i);
      assertEquals((int) result.get(i), t.hammingDistance(param[0], param[1]));

    }
  }
}