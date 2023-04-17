package com.sankuai.test.algorithm.并查集;

import java.io.*;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/26 21:38
 */
public class UnionFind {
    int[] father;
    int[] num;
    int[] help;
    int setNum;

    public UnionFind(int size) {
        this.num = new int[size];
        this.father = new int[size];
        this.help = new int[size];
        for (int idx = 0; idx <= size; idx++) {
            father[idx] = idx;
            num[idx] = 1;
        }
        setNum = size;
    }

    public boolean isSameSet(int a, int b) {
        return findFather(a) == findFather(b);
    }

    private int findFather(int param) {
        int size = 0;
        while (father[param] != param) {
            help[size++] = param;
            param = father[param];
        }
        while (size > 0) {
            father[help[--size]] = param;
        }
        return param;
    }

    public void union(int a, int b) {
        int fatherA = findFather(a);
        int fatherB = findFather(b);
        if (fatherA != fatherB) {
            if (num[fatherA] >= num[fatherB]) {
                father[fatherB] = fatherA;
                num[fatherA] += num[fatherB];
            } else {
                father[fatherA] = fatherB;
                num[fatherB] += num[fatherA];
            }
            setNum--;
        }

    }

    /**
     * 4 5
     * 1 1 2
     * 2 2 3
     * 2 1 3
     * 1 1 1
     * 1 2 3
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int size = (int) in.nval;
            in.nextToken();
            int optNum = (int) in.nval;
            UnionFind unionFind = new UnionFind(size);
            for (int i = 0; i < optNum; i++) {
                in.nextToken();
                int opt = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (opt == 1) {
                    String result = unionFind.isSameSet(x, y) ? "Yes" : "No";
                    out.println(result);
                    out.flush();
                } else {
                    unionFind.union(x, y);
                }
            }
        }
    }
}
