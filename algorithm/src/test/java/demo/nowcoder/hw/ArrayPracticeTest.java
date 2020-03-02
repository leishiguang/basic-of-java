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

  private List<List<List<Integer>>> cases;
  private List<Integer> result;

  @BeforeEach
  void prepare() {
    cases = new ArrayList<>();
    result = new ArrayList<>();

    List<List<Integer>> case1 = new ArrayList<>();
    Integer[] sum = new Integer[]{1, 2, 2, 1};
    Integer[] sum1 = new Integer[]{3, 1, 2};
    Integer[] sum2 = new Integer[]{1, 3, 2};
    Integer[] sum3 = new Integer[]{2, 4};
    Integer[] sum4 = new Integer[]{3, 1, 2};
    Integer[] sum5 = new Integer[]{1, 3, 1, 1};
    case1.add(Arrays.asList(sum));
    case1.add(Arrays.asList(sum1));
    case1.add(Arrays.asList(sum2));
    case1.add(Arrays.asList(sum3));
    case1.add(Arrays.asList(sum4));
    case1.add(Arrays.asList(sum5));
    int result1 = 2;
    cases.add(case1);
    result.add(result1);

    List<List<Integer>> case2 = new ArrayList<>();
    sum = new Integer[]{1, 1};
    sum1 = new Integer[]{2};
    sum2 = new Integer[]{1, 1};
    case2.add(Arrays.asList(sum));
    case2.add(Arrays.asList(sum1));
    case2.add(Arrays.asList(sum2));
    int result2 = 1;
    cases.add(case2);
    result.add(result2);

    List<List<Integer>> case3 = new ArrayList<>();
    sum = new Integer[]{2};
    sum1 = new Integer[]{2};
    sum2 = new Integer[]{2};
    case3.add(Arrays.asList(sum));
    case3.add(Arrays.asList(sum1));
    case3.add(Arrays.asList(sum2));
    int result3 = 3;
    cases.add(case3);
    result.add(result3);
  }

  @Test
  public void go() {
    ArrayPractice arrayPractice = new ArrayPractice();
    for (int i = 0; i < cases.size(); i++) {
      assertEquals((int) result.get(i), arrayPractice.leastBricks(cases.get(i)));
    }
  }
}