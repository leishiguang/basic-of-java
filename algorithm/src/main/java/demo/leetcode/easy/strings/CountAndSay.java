package demo.leetcode.easy.strings;

/**
 * 报数
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/39/
 *
 * 当 n == 1 的时候，返回 1；
 * 当 n != 1 的时候，递归获取到 n-1 的字符串，后续处理思路为：
 *
 *     1. 记录当前 char；
 *     2. 记录当前 char 的出现次数；
 *     3. 循环所有的 char，如果下个char的值发生了变化，执行 append，更新当前 char 以及 char 出现次数；
 *     4. 在最末尾执行一次 append；
 *     5. 返回本次处理之后的字符串，完成本地递归；
 *
 *
 * @author leishiguang
 * @since v1.0
 */
public class CountAndSay {


    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else {
            String str = countAndSay(n - 1);
            StringBuilder result = new StringBuilder();
            int times = 1;
            char lastChar = str.charAt(0);
            for(int i = 1 ;i < str.length(); i ++){
                if(str.charAt(i) == lastChar){
                    times ++;
                }else{
                    result.append(times).append(lastChar);
                    times = 1;
                    lastChar = str.charAt(i);
                }
            }
            result.append(times).append(lastChar);
            return result.toString();
        }
    }

}
