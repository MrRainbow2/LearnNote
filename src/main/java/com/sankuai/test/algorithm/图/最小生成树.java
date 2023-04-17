package com.sankuai.test.algorithm.图;

import com.sankuai.test.algorithm.并查集.Problem;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/4/4 11:01
 */
public class 最小生成树 {

    /**
     * 最小生成树
     */
    static class Problem1 {
        static class UnionFind {
            int[] father;
            int[] num;
            int[] help;

            public UnionFind(int n) {
                father = new int[n + 1];
                num = new int[n + 1];
                help = new int[n + 1];
                for (int i = 0; i <= n; i++) {
                    father[i] = i;
                    num[i] = 1;
                }
            }

            public boolean isSameSet(int param1, int param2) {
                return findFather(param1) == findFather(param2);
            }

            private int findFather(int param) {
                int size = 0;
                while (father[param] != param) {
                    help[size++] = param;
                    param = father[param];
                }
                while (size != 0) {
                    help[--size] = param;
                }
                return param;
            }

            public void union(int param1, int param2) {
                int father1 = findFather(param1);
                int father2 = findFather(param2);
                if (num[father1] >= num[father2]) {
                    father[father2] = father1;
                    num[father1] += num[father2];
                } else {
                    father[father1] = father2;
                    num[father2] += num[father1];
                }
            }
        }

        public int solution(int n, int[][] edge) {
            return 0;
        }
    }


    public static void main(String[] args) {
        Problem1.UnionFind uf = new Problem1.UnionFind(10);
        System.out.println(uf.isSameSet(1, 2));
        System.out.println(uf.isSameSet(3, 4));


        uf.union(1, 2);
        uf.union(3, 4);

        uf.union(1, 3);
        System.out.println(uf.isSameSet(2, 4));
    }


    /**
     * 村里面一共有 n 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。
     * <p>
     * 对于每个房子 i，我们有两种可选的供水方案：一种是直接在房子内建造水井，成本为 wells[i - 1] （注意 -1 ，因为 索引从0开始 ）；另一种是从另一口井铺设管道引水，数组 pipes 给出了在房子间铺设管道的成本，其中每个 pipes[j] = [house1j, house2j, costj] 代表用管道将 house1j 和 house2j连接在一起的成本。连接是双向的。
     * <p>
     * 请返回 为所有房子都供水的最低总成本 。
     */
    static class Problem2 {

    }
}
