package demo.leetcode.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 第20题：有效的括号,Valid Parentheses
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * https://leetcode-cn.com/problems/valid-parentheses/solution/
 *
 * @author leishiguang
 * @since v1.0
 */
public class L20 {
    public boolean isValid(String s) {
        int length = s.length();
        if(length == 0){
            return true;
        }
        Map<Character,Character> map = new HashMap<>(6);
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else if(c == ')' || c == ']' || c == '}'){
                if(stack.isEmpty() || !stack.pop().equals(map.get(c))){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
