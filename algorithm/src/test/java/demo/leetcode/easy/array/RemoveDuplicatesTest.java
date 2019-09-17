package demo.leetcode.easy.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试类
 *
 * @author leishiguang
 * @since v1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RemoveDuplicatesTest {

    private List<int[]> cases = new ArrayList<>();
    private List<Integer> results = new ArrayList<>();
    private List<int[]> resultCases = new ArrayList<>();

    @BeforeEach
    void before() {
        cases.add(new int[]{1,2});
        results.add(2);
        resultCases.add(new int[]{1,2});
        cases.add(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        results.add(5);
        resultCases.add(new int[]{0, 1, 2, 3, 4});
        cases.add(new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4});
        results.add(5);
        resultCases.add(new int[]{0, 1, 2, 3, 4});
        cases.add(new int[]{});
        results.add(0);
        resultCases.add(new int[]{});
    }

    @Test
    void test() {
        RemoveDuplicates duplicates = new RemoveDuplicates();
        for (int i = 0; i < cases.size(); i++) {
            int result = duplicates.removeDuplicates(cases.get(i));
            assertEquals((int) results.get(i), result);
            int[] resultCase = new int[result];
            System.arraycopy(resultCases.get(i), 0, resultCase, 0, result);
            assertArrayEquals(resultCases.get(i), resultCase);
        }
    }

    @Test
    void test2(){
        RemoveDuplicates duplicates = new RemoveDuplicates();
        for (int i = 0; i < cases.size(); i++) {
            int result = duplicates.removeDuplicates2(cases.get(i));
            assertEquals((int) results.get(i), result);
            int[] resultCase = new int[result];
            System.arraycopy(resultCases.get(i), 0, resultCase, 0, result);
            assertArrayEquals(resultCases.get(i), resultCase);
        }
    }

}