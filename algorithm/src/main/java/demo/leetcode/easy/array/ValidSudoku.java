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
    public boolean isValidSudoku(char[][] board) {
        int i, j, k, m;
        Set<Character> set1, set2, set3;
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
}
