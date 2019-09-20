package demo.leetcode.easy.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试数组中是否存在重复元素
 *
 * @author leishiguang
 * @since v1.0
 */
class ContainsDuplicateTest {

    private List<int[]> cases = new ArrayList<>();
    private List<Boolean> results = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cases.add(new int[]{1, 2, 3, 1});
        results.add(true);
        cases.add(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2});
        results.add(true);
        cases.add(new int[]{1, 2, 3, 4});
        results.add(false);
    }

    @Test
    void go() {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals(results.get(i),containsDuplicate.containsDuplicate(cases.get(i)));
        }
    }

    @Test
    void go2() {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals(results.get(i),containsDuplicate.containsDuplicate2(cases.get(i)));
        }
    }

    @Test
    void go3() {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals(results.get(i),containsDuplicate.containsDuplicate3(cases.get(i)));
        }
    }
}