package demo.algorithm.leetcode;

/**
 * 28. Implement strStr()
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * @author leishiguang
 * @since v1.0
 */
public class L28 {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int hayLength = haystack.length();
        int needLength = needle.length();
        if (needLength > hayLength) {
            return -1;
        }
        int star = 0;
        int end = needLength - 1;
        while (end < hayLength) {
            if (haystack.substring(star, end + 1).equals(needle)) {
                return star;
            }
            star++;
            end++;
        }
        return -1;
    }
}
