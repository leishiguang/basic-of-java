package demo.leetcode.easy.array;

/**
 * 旋转图像
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/31/
 * <p>
 * 给定一个 n × n 的二维矩阵表示一个图像，将图像顺时针旋转 90 度。
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * @author leishiguang
 * @since v1.0
 */
public class Rotate2 {

    /**
     * 使用两个指针进行翻转
     */
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int n = matrix[0].length - 1;
        int times = n / 2;
        for (int i = 0; i <= times; i++) {
            for (int j = i; j < n - i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j][i];
                matrix[n - j][i] = matrix[n - i][n - j];
                matrix[n - i][n - j] = matrix[j][n - i];
                matrix[j][n - i] = tmp;
            }
        }
    }

}
