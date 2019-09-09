package demo.algorithm.normal;

/**
 * FNV1_32_HASH hash 算法
 *
 * @author leishiguang
 * @since v1.0
 */
public class H3 {

    /**
     * 使用 FNV1_32_HASH 算法计算服务器的Hash值，类似于 hashCode
     */
    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }
}
