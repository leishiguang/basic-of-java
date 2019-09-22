package demo.algorithm.base;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 常用的搜索算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class Search {

    /**
     * 深度优先搜索
     */
    boolean dfs(int maze[][], int x, int y) {
        int[] B = new int[]{1, 2};
        int[] dx = new int[]{1, -1, 1, -1};
        int[] dy = new int[]{1, 1, -1, -1};
        Stack<Integer[]> stack = new Stack<>();
        // 将起始点压入栈，标记它访问过
        stack.push(new Integer[]{x, y});
        maze[x][y] = -1;

        while (!stack.isEmpty()) {
            // 取出当前点
            Integer[] pos = stack.pop();
            x = pos[0];
            y = pos[1];

            // 判断是否找到了目的地
            if (x == B[0] && y == B[1]) {
                return true;
            }

            // 在四个方向上尝试
            for (int d = 0; d < 4; d++) {
                int i = x + dx[d], j = y + dy[d];
                if (isSafe(maze, i, j)) {
                    stack.push(new Integer[]{i, j});
                    maze[i][j] = -1;
                }
            }
        }
        return false;
    }

    /**
     * 最短路径...
     */
    void solve(int maze[][]) {
        int[] A = new int[]{1, 2};
        int[] B = new int[]{5, 6};

        // 第一步. 除了A之外，将其他等于0的地方用MAX_VALUE替换
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 0 && !(i == A[0] && j == A[1])) {
                    maze[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 第二步. 进行优化的DFS操作
        dfs(maze, A[0], A[1]);

        // 第三步. 看看是否找到了目的地
        if (maze[B[0]][B[1]] < Integer.MAX_VALUE) {
            System.out.println("Shortest path count is: " + maze[B[0]][B[1]]);
        } else {
            System.out.println("Cannot find B!");
        }
    }

    private boolean isSafe(int[][] maze, int i, int j) {
        return false;
    }

    /**
     * 广度优先搜索
     */
    void bfs(int[][] maze, int x, int y) {

        int[] B = new int[]{1, 2};
        int[] dx = new int[]{1, -1, 1, -1};
        int[] dy = new int[]{1, 1, -1, -1};

        // 创建一个队列queue，将起始点A加入队列中
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x, y});

        // 只要队列不为空就一直循环下去
        while (!queue.isEmpty()) {
            // 从队列的头取出当前点
            Integer[] pos = queue.poll();
            x = pos[0];
            y = pos[1];

            // 从四个方向进行BFS
            for (int d = 0; d < 4; d++) {
                int i = x + dx[d], j = y + dy[d];

                if (isSafe(maze, i, j)) {
                    // 记录步数（标记访问过）
                    maze[i][j] = maze[x][y] + 1;
                    // 然后添加到队列中
                    queue.add(new Integer[]{i, j});
                    // 如果发现了目标就返回
                    if (i == B[0] && j == B[1]) {
                        return;
                    }
                }
            }
        }

    }

}
