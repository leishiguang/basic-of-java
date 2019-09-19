package demo.leetcode.easy.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 旋转数组的测试
 *
 * @author leishiguang
 * @since v1.0
 */
class RotateTest {

    private List<int[]> cases = new ArrayList<>();
    private List<Integer> caseNums = new ArrayList<>();
    private List<int[]> results = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cases.add(new int[]{1,2,3,4,5,6,7});
        caseNums.add(3);
        results.add(new int[]{5,6,7,1,2,3,4});
        cases.add(new int[]{-1,-100,3,99});
        caseNums.add(2);
        results.add(new int[]{3,99,-1,-100});
    }

    @Test
    void go(){
        Rotate rotate = new Rotate();
        for (int i = 0; i < cases.size(); i++) {
            rotate.rotate(cases.get(i),caseNums.get(i));
            assertArrayEquals(results.get(i), cases.get(i));
        }
    }

    @Test
    void go2(){
        Rotate rotate = new Rotate();
        for (int i = 0; i < cases.size(); i++) {
            rotate.rotate2(cases.get(i),caseNums.get(i));
            assertArrayEquals(results.get(i), cases.get(i));
        }
    }

    @Test
    void go3(){
        Rotate rotate = new Rotate();
        for (int i = 0; i < cases.size(); i++) {
            rotate.rotate3(cases.get(i),caseNums.get(i));
            assertArrayEquals(results.get(i), cases.get(i));
        }
    }

    @Test
    void go4(){
        Rotate rotate = new Rotate();
        for (int i = 0; i < cases.size(); i++) {
            rotate.rotate4(cases.get(i),caseNums.get(i));
            assertArrayEquals(results.get(i), cases.get(i));
        }
    }

    @Test
    void go5(){
        Rotate rotate = new Rotate();
        for (int i = 0; i < cases.size(); i++) {
            rotate.rotate5(cases.get(i),caseNums.get(i));
            assertArrayEquals(results.get(i), cases.get(i));
        }
    }
}