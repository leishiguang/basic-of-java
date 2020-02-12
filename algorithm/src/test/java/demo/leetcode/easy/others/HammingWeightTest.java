package demo.leetcode.easy.others;

import demo.leetcode.easy.array.ContainsDuplicate;
import demo.leetcode.problems.L1;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 测试
 *
 * @author leishiguang
 * @since v1.0
 */
class HammingWeightTest {

  private List<Integer> cases = new ArrayList<>();
  private List<Integer> results = new ArrayList<>();

  @BeforeEach
  void setUp() {
    cases.add(1);
    results.add(1);
    cases.add(2);
    results.add(1);
    cases.add(-1);
    results.add(32);
    cases.add(-100);
    results.add(28);
  }

  @Test
  void go() {

    HammingWeight hammingWeight = new HammingWeight();

    for (int i = 0; i < cases.size(); i++) {
      //System.out.println(hammingWeight.hammingWeight(cases.get(i)));
      assertEquals((int) results.get(i), hammingWeight.hammingWeight(cases.get(i)));
      assertEquals((int) results.get(i), hammingWeight.hammingWeight2(cases.get(i)));
    }
  }
}