package com.jiuxian;

import java.util.*;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-07-23 09:19:49
 * *
 * @description: 一致性Hash
 **/
public class CoherenceHash {
    private static String[] servers = { "192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111" };

    private static List<String> realNodes = new LinkedList<>();

    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private final static int VIRTUAL_NODES = 5;

    private final static String SYMBOL = "&&VN";


    static {
        realNodes.addAll(Arrays.asList(servers));
        realNodes.forEach(CoherenceHash::addServer);
    }

    public static void addServer(String server) {
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            String virtualNodeName = server + SYMBOL + i;
            virtualNodes.put(getHash(virtualNodeName), virtualNodeName);
        }
    }

    public static void removeServer(String server) {
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            String virtualNodeName = server + SYMBOL + i;
            virtualNodes.remove(getHash(virtualNodeName));
        }
    }

    public static String getServer(String key) {
        int hash = getHash(key);
        SortedMap<Integer, String> map = virtualNodes.tailMap(hash);

        String virtualNode;
        if (map.isEmpty()) {
            virtualNode = virtualNodes.get(virtualNodes.firstKey());
        } else {
            virtualNode = map.get(map.firstKey());
        }
        return virtualNode == null ? null : virtualNode.substring(0, virtualNode.indexOf(SYMBOL));
    }

    //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        return hash < 0 ? Math.abs(hash) : hash;
    }

    public static void main(String[] args) {
        String[] keys = { "太阳", "月亮", "星星" };
        for (String key : keys)
            System.out.println("[" + key + "]的hash值为" + getHash(key)
                    + ", 被路由到结点[" + CoherenceHash.getServer(key) + "]");
    }

}
