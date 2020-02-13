package demo.nowcoder.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 数组的测试
 *
 * @author leishiguang
 * @since v1.0
 */
class ArrayPracticeTest {

  private List<List<Integer>> cases;
  private int result;

  @BeforeEach
  void prepare() {
    cases = new ArrayList<>();
    Integer[] sum = new Integer[]{1, 2, 2, 1};
    Integer[] sum1 = new Integer[]{3, 1, 2};
    Integer[] sum2 = new Integer[]{1, 3, 2};
    Integer[] sum3 = new Integer[]{2, 4};
    Integer[] sum4 = new Integer[]{3, 1, 2};
    Integer[] sum5 = new Integer[]{1, 3, 1, 1};
    cases.add(Arrays.asList(sum));
    cases.add(Arrays.asList(sum1));
    cases.add(Arrays.asList(sum2));
    cases.add(Arrays.asList(sum3));
    cases.add(Arrays.asList(sum4));
    cases.add(Arrays.asList(sum5));
    result = 2;
  }

  @Test
  public void go() {
    ArrayPractice arrayPractice = new ArrayPractice();
    assertEquals(result, arrayPractice.leastBricks(cases));
  }
}