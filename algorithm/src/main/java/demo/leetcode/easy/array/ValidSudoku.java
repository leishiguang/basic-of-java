package demo.leetcode.easy.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 有效的数独
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/30/
 *
 * @author leishiguang
 * @since v1.0
 */
public class ValidSudoku {

    /**
     * 简单的暴力解法，共扫描 2 遍，用 set 做九宫格的校验
     * 第 1 遍，判断行、列是否匹配
     * 第 2 遍，判断九宫格是否匹配
     */
    public boolean isValidSudoku(char[][] board) {
        int i, j, k, m;
        Set<Character> set1, set2, set3;
        //行、列扫描
        for (i = 0; i < 9; i++) {
            set1 = new HashSet<>();
            set2 = new HashSet<>();
            for (j = 0; j < 9; j++) {
                if (!isValidSudoku(board[i][j], set1)) {
                    return false;
                }
                if (!isValidSudoku(board[j][i], set2)) {
                    return false;
                }
            }
        }
        //九宫格扫描
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                set3 = new HashSet<>();
                for (k = 0; k < 3; k++) {
                    for (m = 0; m < 3; m++) {
                        if (!isValidSudoku(board[i * 3 + k][j * 3 + m], set3)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 判断该字符是否在集合中出现过，是否合法
     */
    private boolean isValidSudoku(char c, Set<Character> set) {
        if (c == '.') {
            return true;
        }
        if (c <= '9' && c >= '1') {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        } else {
            return false;
        }
        return true;
    }

    /**
     * 1、只遍历一次如何储存数据；
     * 2、判断是在一个3*3的框中的方法。
     *
     * 1、使用了2进制的9个位数，如果是第一个数是1，那么统计标志就是0000000010(二进制 1左移1位)，如果第二个数是3那么统计标识变为0000001010(二进制 1左移3位再加上原来的)，每次判断有没有重复就右移相应位数之后整除2即可。
     * 2、同官方解法int boxNum = i / 3 * 3 + j / 3;如果是0,1,2行的话整除3就是0，然后再加上列数整除3，这样就把整个9*9分为了编号0-8的9个3*3的区域。
     *
     * 链接：https://leetcode-cn.com/problems/valid-sudoku/solution/36you-xiao-de-shu-du-ti-jie-java-3ms-by-zhaomin666/
     */
    public boolean isValidSudoku2(char[][] board) {
        int[] col = new int[9];
        int[] row = new int[9];
        int[] box = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                //处理行
                if ((col[i] >> num) % 2 == 1) {
                    return false;
                } else {
                    col[i] += 1 << num;
                }
                //处理列
                if ((row[j] >> num) % 2 == 1) {
                    return false;
                } else {
                    row[j] += 1 << num;
                }
                //处理九宫格
                int boxNum = i / 3 * 3 + j / 3;
                if ((box[boxNum] >> num) % 2 == 1) {
                    return false;
                } else {
                    box[boxNum] += 1 << num;
                }
            }
        }
        return true;
    }
}
