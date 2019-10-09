package demo.leetcode.easy.strings;

import demo.algorithm.classic.Kmp;

/**
 * 实现 strStr()
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/38/
 *
 * @author leishiguang
 * @since v1.0
 */
public class StrStr {

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * @see demo.algorithm.classic.Kmp
     */
    public int strStrWithKmp(String haystack, String needle) {
        Kmp kmp = new Kmp(needle);
        return kmp.search(haystack);
    }
}
