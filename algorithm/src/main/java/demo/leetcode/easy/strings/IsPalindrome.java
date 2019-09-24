package demo.leetcode.easy.strings;

/**
 * 验证回文字符串
 *
 * @author leishiguang
 * @since v1.0
 */
public class IsPalindrome {

    /**
     * 首尾往中间走，依次判断是否相等
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        char c1;
        char c2;
        while (i < j) {
            while (i < j && !matchAll(chars[i])) {
                i++;
            }
            while (i < j && !matchAll(chars[j])) {
                j--;
            }
            if(i >= j){
                return true;
            }
            c1 = chars[i];
            c2 = chars[j];
            if (!equal(c1, c2)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean matchAll(char c) {
        return matchBig(c) || matchDig(c) || matchSmall(c);
    }

    public boolean matchDig(char c) {
        return 48 <= c && c <= 57;
    }

    public boolean matchBig(char c) {
        return 65 <= c && c <= 90;
    }

    public boolean matchSmall(char c) {
        return 97 <= c && c <= 122;
    }

    public boolean equal(char c1, char c2) {
        if (c1 == c2) {
            return true;
        }
        int i1 = c1;
        int i2 = c2;
        if (matchBig(c1)) {
            i1 += 32;
        }
        if (matchBig(c2)) {
            i2 += 32;
        }
        return i1 == i2;
    }
}
