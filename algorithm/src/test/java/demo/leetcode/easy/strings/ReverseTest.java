package demo.leetcode.easy.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试
 *
 * @author leishiguang
 * @since v1.0
 */
class ReverseTest {

    @Test
    void go(){
        Reverse reverse = new Reverse();
        assertEquals(321,reverse.reverse(123));
    }
}