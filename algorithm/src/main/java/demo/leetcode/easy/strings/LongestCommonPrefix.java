package demo.leetcode.easy.strings;

/**
 * 最长公共前缀
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/40/
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author leishiguang
 * @since v1.0
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        int length = Integer.MAX_VALUE;
        for (String str : strs) {
            length = Math.min(length, str.length());
        }
        for (int i = 0; i < length; i++) {
            char tmp = ' ';
            for (String str : strs) {
                if( tmp == ' '){
                    tmp = str.charAt(i);
                }else{
                    if(tmp != str.charAt(i)){
                        tmp = ' ';
                        break;
                    }
                }
            }
            if( tmp == ' '){
                break;
            }
            result.append(tmp);
        }
        return result.toString();
    }
}
