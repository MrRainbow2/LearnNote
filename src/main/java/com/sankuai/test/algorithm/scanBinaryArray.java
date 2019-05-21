package com.sankuai.test.algorithm;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 */
public class scanBinaryArray {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        final boolean b = new scanBinaryArray().searchMatrix(matrix, 5);
        System.out.println(b);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int rowIndex = 0;
        int columnIndex = matrix[0].length - 1;
        while (rowIndex < matrix.length && columnIndex >= 0) {
            if (target == matrix[rowIndex][columnIndex]) {
                return true;
            } else if (target < matrix[rowIndex][columnIndex]) {
                columnIndex--;
            } else {
                rowIndex++;
            }
        }

        return false;

    }
}
