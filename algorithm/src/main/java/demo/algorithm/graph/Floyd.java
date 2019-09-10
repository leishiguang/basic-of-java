package demo.algorithm.graph;

/**
 * Floyd算法是一个经典的动态规划算法。
 * 是解决任意两点间的最短路径(称为多源最短路径问题)的一种算法，可以正确处理有向图或负权的最短路径问题。
 * 动态规划算法是通过拆分问题规模，并定义问题状态与状态的关系，使得问题能够以递推（分治）的方式去解决，最终合并各个拆分的小问题的解为整个问题的解。
 * <p>
 * 参考：
 * 1. https://www.jianshu.com/p/92e46d990d17
 * 2. https://blog.csdn.net/zxcvbnmlxy/article/details/56543163
 *
 * 在本算法中，我们将不使用Guava-Graph结构，而采用邻接矩阵来作为本例的数据结构
 *
 * @author leishiguang
 * @since v1.0
 */
public class Floyd {
    private static final String TAG = "Floyd";

    public static void main(String[] args) {
        int vexCount = 4;
        int[][] arcs = new int[vexCount + 1][vexCount + 1];
        int[][] path = new int[vexCount + 1][vexCount + 1];

        /*
         * 初始化图的邻接矩阵
         */
        for (int i = 1; i <= vexCount; i++) {
            for (int j = 1; j <= vexCount; j++) {
                if (i != j) {
                    arcs[i][j] = Integer.MAX_VALUE;
                } else {
                    arcs[i][j] = 0;
                }
                path[i][j] = j;
            }
        }

        /*
         * 输入图的边集
         */
        arcs[1][2] = 2;
        arcs[1][3] = 6;
        arcs[1][4] = 4;
        arcs[2][3] = 3;
        arcs[3][1] = 7;
        arcs[3][4] = 1;
        arcs[4][1] = 5;
        arcs[4][3] = 12;
        print(arcs, vexCount, 0);

        /*
         * floyd核心算法：
         * if arcs[i][k] + arcs[k][j] < arcs[i][j] then
         *      arcs[i][j] = arcs[i][k] + arcs[k][j]
         */
        for (int k = 1; k <= vexCount; k++) {
            for (int i = 1; i <= vexCount; i++) {
                for (int j = 1; j <= vexCount; j++) {
                    if (arcs[i][k] < Integer.MAX_VALUE && arcs[k][j] < Integer.MAX_VALUE) {
                        final int d = arcs[i][k] + arcs[k][j];
                        //经过k点时i到j的距离比不经过k点的距离更短
                        if (d < arcs[i][j]) {
                            //更新i到j的最短距离
                            arcs[i][j] = d;
                            //更新i到j经过的最后一个点为k点
                            path[i][j] = path[i][k];
                        }
                    }
                }
            }
            print(arcs, vexCount, k);
        }

        printPath(arcs, path, vexCount);
    }

    private static void print(int[][] arcs, int vexCount, int index) {
        System.out.println(TAG + ",print array step of " + index + ":");
        for (int i = 1; i <= vexCount; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 1; j <= vexCount; j++) {
                if (arcs[i][j] < Integer.MAX_VALUE) {
                    builder.append(String.format("%5d", arcs[i][j])).append(" ");
                } else {
                    builder.append(String.format("%5s", "∞")).append(" ");
                }
            }
            builder.append("\n");
            System.out.println(TAG + "," + builder.toString());
        }
    }

    private static void printPath(int[][] arcs, int[][] path, int vexCount) {
        System.out.println(TAG + ",print path:");
        int temp;
        for (int i = 1; i <= vexCount; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 1; j <= vexCount; j++) {
                builder.append(i).append("->").append(j)
                        .append(", weight: ").append(arcs[i][j]).append(":").append(i);
                temp = path[i][j];
                while (temp != j) {
                    builder.append("->").append(temp);
                    temp = path[temp][j];
                }
                builder.append("->").append(j).append("\n");
            }
            System.out.println(TAG + "," + builder.toString());
        }
    }
}
