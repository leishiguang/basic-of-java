package demo.others;

import java.util.Scanner;

/**
 * 按秩序读取键盘输入
 *
 * 依次输入：
 *   1 1
 * 输出：
 *   2
 *
 * @author leishiguang
 * @since v1.0
 */
public class Good {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }

}
