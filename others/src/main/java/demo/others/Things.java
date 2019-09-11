package demo.others;

import java.util.Scanner;

/**
 * 按秩序键盘输入
 *
 * 依次输入：
 *   3
 *   1 2 3
 *   2 1 3
 *   3 2 1
 * 输出：
 *   18
 *
 * @author leishiguang
 * @since v1.0
 */
public class Things {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                x = sc.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }
}
