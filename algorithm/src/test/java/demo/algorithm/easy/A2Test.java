package demo.algorithm.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 算法测试类
 *
 * @author leishiguang
 * @since v1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class A2Test {

    private List<Integer[]> cases = new ArrayList<>();
    private List<Integer[]> results = new ArrayList<>();

    @BeforeAll
    void beforeAll() {
        cases.add(new Integer[]{1, 2, 3, 4, 3});
        results.add(new Integer[]{4});
        cases.add(new Integer[]{1, 1, 1, 2, 3, 4, 5, 1});
        results.add(new Integer[]{5});
        cases.add(new Integer[]{5, 4, 1});
        results.add(new Integer[]{5});
        cases.add(new Integer[]{4, 4, 5});
        results.add(new Integer[]{5});
    }

    @Test
    void go() {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < cases.size(); i++) {
            Integer result = A2.go(Arrays.asList(cases.get(i)));
            assertTrue(Arrays.asList(results.get(i)).contains(result),
                    "result=" + result + ",total=" + Arrays.toString(results.get(i)));
        }
        System.out.println("测试通过，花费时间：" + (System.currentTimeMillis() - starTime) + "ms");
    }
}