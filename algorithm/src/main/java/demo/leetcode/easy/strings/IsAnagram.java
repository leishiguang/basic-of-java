package demo.leetcode.easy.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 有效的字母异位词
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/35/
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * @author leishiguang
 * @since v1.0
 */
public class IsAnagram {

    /**
     * 用 hashMap 去计算
     */
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>(6);
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        for (char c1 : s1) {
            if (map.containsKey(c1)) {
                map.put(c1, map.get(c1) + 1);
            } else {
                map.put(c1, 1);
            }
        }
        for (char c2 : s2) {
            if (map.containsKey(c2)) {
                map.put(c2, map.get(c2) - 1);
            } else {
                return false;
            }
        }

        for(Integer value: map.values()){
            if(value != null && value >= 1){
                return false;
            }
        }
        return true;
    }

    /**
     * 排序后再计算
     */
    public boolean isAnagram2(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        return Arrays.equals(s1,s2);
    }
}
