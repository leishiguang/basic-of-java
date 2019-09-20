package demo.leetcode.easy.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试只出现一次的数字
 *
 * @author leishiguang
 * @since v1.0
 */
class SingleNumberTest {

    private List<int[]> cases = new ArrayList<>();
    private List<Integer> results = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cases.add(new int[]{2, 2, 1});
        results.add(1);
        cases.add(new int[]{4, 1, 2, 1, 2});
        results.add(4);
    }

    @Test
    void go() {
        SingleNumber singleNumber = new SingleNumber();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals((int)results.get(i), singleNumber.singleNumber(cases.get(i)));
        }
    }

    @Test
    void go2() {
        SingleNumber singleNumber = new SingleNumber();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals((int)results.get(i), singleNumber.singleNumber2(cases.get(i)));
        }
    }

    @Test
    void go3() {
        SingleNumber singleNumber = new SingleNumber();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals((int)results.get(i), singleNumber.singleNumber3(cases.get(i)));
        }
    }
}