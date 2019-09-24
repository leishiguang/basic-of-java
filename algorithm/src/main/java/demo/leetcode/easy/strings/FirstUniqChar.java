package demo.leetcode.easy.strings;

/**
 * 字符串中的第一个唯一字符
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/34/
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * @author leishiguang
 * @since v1.0
 */
public class FirstUniqChar {

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        //0：出现次数，1：出现的索引
        int[][] charTimesAndIndex = new int[2][26];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int p = c - 97;
            if (charTimesAndIndex[0][p] == 0) {
                charTimesAndIndex[1][p] = i;
            }
            charTimesAndIndex[0][p] += 1;
        }
        int min = -1;
        for (int j = 25; j >= 0; j--) {
            if(charTimesAndIndex[0][j] == 1){
                if(min == -1){
                    min = charTimesAndIndex[1][j];
                }else{
                    min = Math.min(min,charTimesAndIndex[1][j]);
                }
            }
        }
        return min;
    }

}
