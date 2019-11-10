package demo.leetcode.easy.math;

/**
 * 计数质数，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/61/
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 10 输出: 4 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * @author leishiguang
 * @since v1.0
 */
public class CountPrimes {

  public static void main(String[] args) {
    CountPrimes countPrimes = new CountPrimes();
    System.out.println(countPrimes.countPrimes(3));
  }

  public int countPrimes(int n) {
    boolean[] notPrime = new boolean[n];
    int count = 0;
    for (int i = 2; i < n; i++) {
      //判断是否在notPrime表中，再判断是否是素质
      if(!notPrime[i] && isPrime(i)){
        //把i倍位置，都置为true
        setNotPrime(notPrime,i);
        count ++;
      }
    }
    return count;
  }

  public boolean isPrime(int m) {
    for (int i = 2; i <= Math.sqrt(m); ++i) {
      if (m % i == 0) {
        return false;
      }
    }
    return true;
  }

  public void setNotPrime(boolean[] notPrime, int x){
    int index = x;
    while (index < notPrime.length){
      notPrime[x] = true;
      index += x;
    }
  }

}
