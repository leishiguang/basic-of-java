package demo.leetcode.easy.others;

import java.util.Stack;

/**
 * 有效的括号，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/68/
 *
 * @author leishiguang
 * @since v1.0
 */
public class ValidParentheses {

  /**
   * 使用栈实现
   */
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (Character c : s.toCharArray()) {
      switch (c) {
        case '{':
        case '(':
        case '[':
          stack.push(c);
          break;
        case '}':
          if (stack.empty()) {
            return false;
          }
          if ('{' != stack.pop()) {
            return false;
          }
          break;
        case ']':
          if (stack.empty()) {
            return false;
          }
          if ('[' != stack.pop()) {
            return false;
          }
          break;
        case ')':
          if (stack.empty()) {
            return false;
          }
          if ('(' != stack.pop()) {
            return false;
          }
          break;
        default:
      }
    }
    return stack.empty();
  }

  /**
   * 递归+字符串替换
   */
  public boolean isValid2(String s) {
    if (s.contains("()") || s.contains("[]") || s.contains("{}")) {
      return isValid2(s.replace("()", "").replace("[]", "").replace("{}", ""));
    } else {
      return "".equals(s);
    }
  }
}
