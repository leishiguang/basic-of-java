package demo.algorithm.normal;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 不带虚拟节点的分布式一致性 Hash 算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class H1 {

    /**
     * 服务列表，将添加进入 Hash 环
     */
    private static String[] servers = {"192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4",
            "192.168.0.5", "192.168.0.6", "192.168.0.7", "192.168.0.8", "192.168.0.9"};

    /**
     * 服务器的 hash 值 -> 服务器名称
     */
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    /*
      程序初始化，将所有的服务器放入 sortedMap 中
     */
    static {
        for (String server : servers) {
            int hash = getHash(server);
            sortedMap.put(hash, server);
            System.out.println(server + "->" + hash);
        }
        System.out.println("服务器 hash 完毕");
    }

    /**
     * 使用 FNV1_32_HASH 算法计算服务器的Hash值，类似于 hashCode
     */
    private static int getHash(String str) {
        return H3.getHash(str);
    }

    /**
     * 得到应当路由的节点
     *
     * @param node 节点
     * @return 服务名称
     */
    private static String getServer(String node) {
        // 计算节点 hash
        int hash = getHash(node);
        // 得到大于该 hash 值的节点
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        // 取到 hash 中的 key
        Integer i = subMap.firstKey();
        // 返回服务名称
        return subMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "127.0.0.1:2222", "127.0.0.1:3333", "127.0.0.1:4444"};
        for (String node : nodes) {
            System.out.println(node + "->" + getHash(node) + "->" + getServer(node));
        }
    }
}
