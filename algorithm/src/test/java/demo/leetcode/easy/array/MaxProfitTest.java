package demo.leetcode.easy.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试
 *
 * @author leishiguang
 * @since v1.0
 */
class MaxProfitTest {

    private List<int[]> cases = new ArrayList<>();
    private List<Integer> results = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cases.add(new int[]{1,3,3});
        results.add(2);
        cases.add(new int[]{7, 1, 5, 3, 6, 4});
        results.add(7);
        cases.add(new int[]{1,2,3,4,5});
        results.add(4);
        cases.add(new int[]{7,6,4,3,1});
        results.add(0);
        cases.add(new int[]{1,2});
        results.add(1);
        cases.add(new int[]{1});
        results.add(0);
        cases.add(new int[]{1,1,1,3});
        results.add(2);
        cases.add(new int[]{1,9,6,9,1,7,1,1,5,9,9,9});
        results.add(25);
    }

    @Test
    void go() {
        MaxProfit maxProfit = new MaxProfit();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals((int) results.get(i),maxProfit.maxProfit(cases.get(i)));
        }
    }

    @Test
    void go2(){
        MaxProfit maxProfit = new MaxProfit();
        for (int i = 0; i < cases.size(); i++) {
            assertEquals((int) results.get(i),maxProfit.maxProfit2(cases.get(i)));
        }
    }

}