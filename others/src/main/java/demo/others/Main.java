package demo.others;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //用例数
        int n = sc.nextInt();
        //存储数据
        HashMap<Integer, Node> nodes;
        List<Integer> results = new ArrayList<>(n);
        int x, y;
        for (int i = 0; i < n; i++) {
            //行数
            int size = sc.nextInt();
            nodes = new HashMap<>(size);
            for (int j = 0; j < size; j++) {
                x = sc.nextInt();
                y = sc.nextInt();
                Node temp1;
                Node temp2;
                if (nodes.get(x) == null) {
                    temp1 = new Node(x);
                } else {
                    temp1 = nodes.get(x);
                }
                if (nodes.get(y) == null) {
                    temp2 = new Node(y);
                } else {
                    temp2 = nodes.get(y);
                }
                temp1.link(temp2);
                nodes.put(x, temp1);
                nodes.put(y, temp2);
            }
            Integer tmpResult = getResult(nodes);
            System.out.println(tmpResult);
            results.add(getResult(nodes));
        }
        //输出结果
        for (Integer result : results) {
            System.out.println(result);
        }
    }

    /**
     * 计算数据
     */
    private static Integer getResult(HashMap<Integer, Node> nodes) {
        List<Integer> allMax = new ArrayList<>();
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            if (entry.getValue().isStart()) {
                allMax.add(entry.getValue().computeMax(entry.getValue(),true));
            }
        }
        // 链表中求最大值
        return getListMax(allMax);
    }


    /**
     * 计算链表中最大值
     */
    private static Integer getListMax(List<Integer> allMax) {
        Integer max = 0;
        for (Integer a : allMax) {
            if (a > max) {
                max = a;
            }
        }
        return max;
    }

    /**
     * 定义节点
     */
    static class Node {
        /**
         * 当前id
         */
        int name;

        /**
         * 到这儿的最大值
         */
        int max = 0;

        /**
         * 其它节点
         */
        List<Node> others = new ArrayList<>();

        Node(Integer name) {
            this.name = name;
        }

        /**
         * 连接两个节点
         */
        void link(Node node) {
            this.others.add(node);
            node.others.add(node);
        }

        /**
         * 是否是起始节点
         */
        boolean isStart(Node nextNode) {
            return others.size() == 1 && nextNode == null;
        }

        boolean isStart() {
            return others.size() == 1;
        }

        /**
         * 计算当前节点的最远值
         */
        int computeMax(Node fromNode, boolean firstStart) {
            int result;
            if (this.max != 0) {
                result = this.max;
            } else if (isStart() && !firstStart) {
                result = this.max = 1;
            } else {
                List<Integer> tmpMax = new ArrayList<>();
                for (Node node : others) {
                    if (this != fromNode) {
                        tmpMax.add(node.computeMax(this,false));
                    }
                }
                result = this.max = getListMax(tmpMax);
            }
            return result;
        }

    }
}
