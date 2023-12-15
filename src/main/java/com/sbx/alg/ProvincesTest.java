package com.sbx.alg;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 省份数量
 * * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * @author sbx
 * @since 2023/12/14
 */
public class ProvincesTest {

    /**
     * 深度优先遍历
     * @param isConnected 图的邻接矩阵
     * @return 省份数量
     */
    public int findCircleNumDfs(int[][] isConnected) {
        int vertexs = isConnected.length;
        boolean[] visited = new boolean[vertexs];
        int provinces = 0;
        for (int i = 0; i < vertexs; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, vertexs, i);
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * 深度递归
     * @param isConnected 图的邻接矩阵
     * @param visited 是否被访问数组
     * @param vertexs 顶点个数
     * @param i 代表矩阵中第i个数组
     */
    public void dfs(int[][] isConnected, boolean[] visited, int vertexs, int i) {
        for (int j = 0; j < vertexs; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, vertexs, j);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param isConnected 图的邻接矩阵
     * @return 省份数量
     */
    public int findCircleNumBfs(int[][] isConnected) {
        int vertexs = isConnected.length;
        boolean[] visited = new boolean[vertexs];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertexs; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    Integer j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < vertexs; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * 并查集
     * @param isConnected 图的邻接矩阵
     */
    public int findCircleNumUnionFind(int[][] isConnected) {
        int vertexs = isConnected.length;
        // 初始化
        int[] parent = new int[vertexs];
        for (int i = 0; i < vertexs; i++) {
            parent[i] = i;
        }
        // 根据矩阵构建并查集
        for (int i = 0; i < vertexs; i++) {
            for (int j = 0; j < vertexs; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        // 遍历并查集，有几个根节点就有几个省份
        int provinces = 0;
        for (int i = 0; i < vertexs; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * 合并
     * @param parent 并查集
     * @param i 节点i
     * @param j 节点j
     */
    public void union(int[] parent, int i, int j) {
        int p1 = find(parent, i), p2 = find(parent, j);
        if (p1 == p2) {
            return;
        }
        parent[p1] = p2;
    }

    /**
     * 查找元素所属集合，即根节点
     * @param parent 并查集
     * @param x 查找元素x
     * @return
     */
    public int find(int[] parent, int x) {
        return parent[x] == x ? x : (parent[x] = find(parent, parent[x]));
    }



    @Test
    public void testDfs() {
        long begin = System.currentTimeMillis();
        int[][] isConnected = {{1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},{0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}};
        int circleNum = findCircleNumDfs(isConnected);
        long end = System.currentTimeMillis();
        System.out.println(circleNum);
        System.out.println("DFS耗时:" + (end - begin));
    }

    @Test
    public void testBfs() {
        long begin = System.currentTimeMillis();
        int[][] isConnected = {{1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},{0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}};
        int circleNum = findCircleNumBfs(isConnected);
        long end = System.currentTimeMillis();
        System.out.println(circleNum);
        System.out.println("BFS耗时:" + (end - begin));
    }

    @Test
    public void testUnionFind() {
        long begin = System.currentTimeMillis();
        int[][] isConnected = {{1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},{0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}};
        int circleNum = findCircleNumUnionFind(isConnected);
        long end = System.currentTimeMillis();
        System.out.println(circleNum);
        System.out.println("UnionFind耗时:" + (end - begin));
    }

    @Test
    public void test() {
        testDfs();
        testBfs();
        testUnionFind();
    }

}
