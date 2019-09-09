package demo.algorithm.normal;

import java.util.*;

/**
 * 带虚拟节点的分布式一致性 Hash 算法
 * 通过采取虚拟节点的方法，一个真实结点不再固定在Hash换上的某个点，而是大量地分布在整个Hash环上，这样：
 * 1、即使上线、下线服务器，也不会造成整体的负载不均衡。
 * 2、hash 环上面的服务分布更加平均，每台服务器负载相对均衡。
 *
 * @author leishiguang
 * @since v1.0
 */
public class H2 {
    /**
     * 服务列表，将添加进入 Hash 环
     */
    private static String[] servers = {"192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4",
            "192.168.0.5", "192.168.0.6", "192.168.0.7", "192.168.0.8", "192.168.0.9"};

    /**
     * 真实结点列表
     */
    private static List<String> realNodes = new LinkedList<>();

    /**
     * 虚拟节点的 hash 值 -> 虚拟节点名称
     */
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    /**
     * 虚拟节点数目，为了演示需要，这儿写死为 5
     */
    private static final int VIRTUAL_NODES = 5;

    /*
     初始化
     */
    static {
        realNodes.addAll(Arrays.asList(servers));
        for (String server : realNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = server + "&&VN" + i;
                int hash = getHash(virtualNodeName);
                sortedMap.put(hash, virtualNodeName);
                System.out.println("虚拟节点:" + virtualNodeName + "->" + hash);
            }
        }
        System.out.println("虚拟节点添加完毕");
    }

    /**
     * 使用 FNV1_32_HASH 算法计算服务器的Hash值，类似于 hashCode
     */
    private static int getHash(String str) {
        return H3.getHash(str);
    }

    /**
     * 得到应当路由到的节点
     */
    private static String getServer(String node){
        // 计算节点 hash
        int hash = getHash(node);
        // 得到大于该 hash 值的节点
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        // 取到 hash 中的 key
        Integer i = subMap.firstKey();
        // 返回对应的虚拟节点名称，这里字符串稍微截取一下
        String virtualNode = subMap.get(i);
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "127.0.0.1:2222", "127.0.0.1:3333", "127.0.0.1:4444"};
        for (String node : nodes) {
            System.out.println(node + "->" + getHash(node) + "->" + getServer(node));
        }
    }
}
