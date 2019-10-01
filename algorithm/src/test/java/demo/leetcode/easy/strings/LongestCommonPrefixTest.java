package demo.leetcode.easy.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试类
 *
 * @author leishiguang
 * @since v1.0
 */
class LongestCommonPrefixTest {

    @Test
    void go() {
        LongestCommonPrefix obj = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow","flight"};
        assertEquals(obj.longestCommonPrefix(strs),"fl");
    }
}