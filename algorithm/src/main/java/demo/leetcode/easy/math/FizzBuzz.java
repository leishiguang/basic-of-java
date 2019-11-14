package demo.leetcode.easy.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Fizz Buzz，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/60/
 *
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 *
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 *
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 *
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * @author leishiguang
 * @since v1.0
 */
public class FizzBuzz {

  public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();
    String isThree = "Fizz";
    String isFive = "Buzz";
    String isThreeAndFive = "FizzBuzz";
    boolean[] three = new boolean[n + 1];
    boolean[] five = new boolean[n + 1];
    int x = 3;
    while (x < n + 1){
      three[x] = true;
      x += 3;
    }
    x = 5;
    while (x < n + 1){
      five[x] = true;
      x += 5;
    }
    x = 1;
    while (x < n + 1){
      if(five[x]){
       if(three[x]){
         result.add(isThreeAndFive);
       }else{
         result.add(isFive);
       }
      }else if(three[x]){
        result.add(isThree);
      }else{
        result.add(String.valueOf(x));
      }
      x ++;
    }
    return result;
  }

  public static void main(String[] args) {
    FizzBuzz fizzBuzz = new FizzBuzz();
    List<String> result = fizzBuzz.fizzBuzz(100);
  }
}
