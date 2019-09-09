package demo.algorithm.easy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 算法测试类
 *
 * @author leishiguang
 * @since v1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class A1Test {

    private List<Integer[]> cases;
    private List<Integer> keys;
    private List<Integer[]> results;

    @BeforeAll
    void beforeAll() {
        cases = new ArrayList<>();
        results = new ArrayList<>();
        keys = new ArrayList<>();
        cases.add(new Integer[]{1, 1, 2, 2, 2, 2, 2, 4, 5});
        keys.add(1);
        results.add(new Integer[]{0, 1});
        cases.add(new Integer[]{1, 1, 2, 2, 2, 2, 2, 4, 5});
        keys.add(2);
        results.add(new Integer[]{2, 6});
    }

    @Test
    void go() {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < cases.size(); i++) {
            Integer[] result = A1.go(cases.get(i), keys.get(i));
            assertEquals(results.get(i)[0], result[0]);
            assertEquals(results.get(i)[1], result[1]);
        }
        System.out.println("测试通过，花费时间：" + (System.currentTimeMillis() - starTime) + "ms");
    }

}