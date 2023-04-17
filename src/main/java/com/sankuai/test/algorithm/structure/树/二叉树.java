package com.sankuai.test.algorithm.structure.树;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/24 21:27
 */
public class 二叉树 {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 按层遍历二叉树
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     * LeetCode 102
     */
    class Problem1 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> result = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            ArrayList<Integer> currentLevel;
            while (!queue.isEmpty()) {
                int size = queue.size();
                currentLevel = new ArrayList<>();
                for (int idx = 0; idx < size; idx++) {
                    TreeNode curNode = queue.poll();
                    currentLevel.add(curNode.val);
                    if (curNode.left != null) {
                        queue.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.add(curNode.right);
                    }
                }
                result.add(currentLevel);
            }
            return result;
        }
    }

    /**
     * 判断是否完全二叉树
     * 按层遍历二叉树
     * 1）如果任何节点， leftChild == null ， rightChile != null 则不是完全二叉树。
     * 2）如果遇到一个节点，left == null && right == null，之后所有的节点必须都是叶子节点，否则就不是完全二叉树
     * LeeCode 958
     */
    class Problem2 {
        public boolean isCompleteTree(TreeNode root) {
            if (root == null) {
                return true;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            TreeNode currentNode;
            boolean mustBeLeave = false;
            while (!queue.isEmpty()) {
                currentNode = queue.poll();
                if (currentNode.left == null && currentNode.right != null) {
                    return false;
                }
                if ((currentNode.right != null || currentNode.left != null) && mustBeLeave) {
                    return false;
                }
                if (currentNode.right == null) {
                    mustBeLeave = true;
                }
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            return true;
        }
    }

    /**
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * <p>
     * <p>
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     * <p>
     * 根据二叉树前序遍历，中顺遍历还原二叉树
     * LeeCode 105
     */
    class Problem3 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
                return null;
            }
            HashMap<Integer, Integer> inIndexMap = new HashMap<>();
            for (int idx = 0; idx < inorder.length; idx++) {
                inIndexMap.put(inorder[idx], idx);
            }
            return buildSubTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
        }

        /**
         * 根据前序遍历，后续遍历数组，按照起始点，结束点构建对应子树。
         *
         * @param preorder   前序遍历数组
         * @param pl         当前子树前序遍历起始点
         * @param pr         当前子树前序遍历结束点
         * @param inorder    中序遍历数组
         * @param il         当前子树中序遍历起始点
         * @param ir         当前子树中序遍历结束点
         * @param inIndexMap 中序遍历数组值对应下标
         *                   <p>
         *                   preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         *                   0 1 2  3  4                         0 1 2  3  4
         * @return
         */
        public TreeNode buildSubTree(int[] preorder, int pl, int pr, int[] inorder, int il, int ir,
                                     HashMap<Integer, Integer> inIndexMap) {
            TreeNode root = new TreeNode(preorder[pl]);
            int inOrderIndex = inIndexMap.get(root.val);
            int leftSize = inOrderIndex - il;
            int rightSize = ir - inOrderIndex;
            TreeNode leftTree = null;
            if (leftSize > 0) {
                leftTree = buildSubTree(preorder, pl + 1, pl + leftSize, inorder, il, inOrderIndex - 1, inIndexMap);
            }
            TreeNode rightTree = null;
            if (rightSize > 0) {
                rightTree = buildSubTree(preorder, pr - rightSize + 1, pr, inorder, inOrderIndex + 1, ir, inIndexMap);
            }
            root.left = leftTree;
            root.right = rightTree;
            return root;
        }
    }

    /**
     * LeetCode 98
     * 判断二叉搜索树
     * 二叉树遍历满足以下条件：
     * 1）左子树是二叉搜索树
     * 2）右子树是二叉搜索树
     * 3）左子树的最大值，小于根节点。
     * 4）右子树的最小值，小于根节点。
     */
    class Problem4 {
        class TreeInfo {
            public boolean isBST;
            public int maxVal;
            public int minVal;

            TreeInfo() {

            }

            TreeInfo(boolean isBST, int maxVal, int minVal) {
                this.isBST = isBST;
                this.maxVal = maxVal;
                this.minVal = minVal;
            }
        }


        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return false;
            }
            return getTreeInfo(root).isBST;
        }

        public TreeInfo getTreeInfo(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeInfo leftInfo = getTreeInfo(root.left);
            TreeInfo rightInfo = getTreeInfo(root.right);
            int maxVal = root.val;
            int minVal = root.val;
            boolean isBST = true;
            if (leftInfo != null) {
                if (root.val <= leftInfo.maxVal) {
                    isBST = false;
                }
                isBST = isBST && leftInfo.isBST;
                maxVal = Math.max(maxVal, leftInfo.maxVal);
                minVal = Math.min(minVal, leftInfo.minVal);
                if (maxVal != root.val) {
                    isBST = false;
                }
            }
            if (rightInfo != null) {
                maxVal = Math.max(maxVal, rightInfo.maxVal);
                minVal = Math.min(minVal, rightInfo.minVal);
                if (root.val >= rightInfo.minVal) {
                    isBST = false;
                }
                isBST = isBST && rightInfo.isBST;
            }
            return new TreeInfo(isBST, maxVal, minVal);
        }

    }

    /**
     * 判断是否平衡二叉树
     * 二叉树遍历，需要满足以下条件：
     * 1）左子树是平衡二叉树。
     * 2）右子树是平衡二叉树。
     * 3）左右子树高度差小于等于1
     */
    class Problem5 {
        class AVLTreeInfo {
            public boolean isAvl;
            public int height;

            AVLTreeInfo() {

            }

            AVLTreeInfo(boolean isAvl, int height) {
                this.isAvl = isAvl;
                this.height = height;
            }
        }

        public boolean isBalanced(TreeNode root) {
            return getAvlTreeInfo(root).isAvl;
        }

        public AVLTreeInfo getAvlTreeInfo(TreeNode root) {
            if (root == null) {
                return new AVLTreeInfo(true, 0);
            }
            AVLTreeInfo left = getAvlTreeInfo(root.left);
            AVLTreeInfo right = getAvlTreeInfo(root.right);
            boolean isAvl = left.isAvl && right.isAvl;
            int height = Math.max(left.height, right.height) + 1;
            if (Math.abs(right.height - left.height) > 1) {
                isAvl = false;
            }
            return new AVLTreeInfo(isAvl, height);
        }
    }

    /**
     * LeetCode 222
     * 统计完全二叉树节点。
     */
    class Problem6 {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return countNodes(root, 1, calTreeHeight(root));
        }

        /**
         * @param root         当前树根节点
         * @param currentLevel 当前根节点层级
         * @param height       树的总高度
         * @return
         */
        public int countNodes(TreeNode root, int currentLevel, int height) {
            if (root == null) {
                return 0;
            }
            int rightHeight = calTreeHeight(root.right);
            if (rightHeight + currentLevel == height) {//到底了，那么左子树为完全二叉树，节点个数为 1<<(h-currentLevel)-1+1
                return (1 << (height - currentLevel) - 1 + 1) + countNodes(root.right, currentLevel + 1, height);
            } else {
                return (1 << (height - currentLevel - 1) - 1 + 1) + countNodes(root.left, currentLevel + 1, height);
            }
        }

        /**
         * @param root
         * @return
         */
        public int calTreeHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int height = 1;
            TreeNode current = root;
            while (current != null) {
                current = current.left;
                height++;
            }
            return height - 1;
        }
    }


    /**
     * LeetCode 543
     * 统计二叉树节点两个距离最远的节点其距离是多少
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * <p>
     * 当一颗树，根节点为X时，最远距离计算分以下两种情况：
     * 1）最远距离计算包含X
     * 最远距离 = 左子树的高度 + 右子树的高度
     * 2）最远距离计算不包含X
     * max（左子树的最大距离，右子树的最大距离）
     * 由上可得，我们遍历二叉树时，需要知道两个信息：
     * 1）当前树的高度，以及最大距离。
     */
    class Problem7 {
        class TreeInfo {
            int height;
            int distance;

            TreeInfo(int h, int d) {
                height = h;
                distance = d;
            }
        }

        public int diameterOfBinaryTree(TreeNode root) {
            return getTreeInfo(root).distance;
        }

        public TreeInfo getTreeInfo(TreeNode root) {
            if (root == null) {
                return new TreeInfo(0, 0);
            }
            TreeInfo left = getTreeInfo(root.left);
            TreeInfo right = getTreeInfo(root.right);
            int height = Math.max(left.height, right.height) + 1;
            int distance = Math.max(left.distance, right.distance);
            distance = Math.max(left.height + right.height, distance);
            return new TreeInfo(height, distance);
        }
    }

    /**
     * LeetCode 199
     */
    static class Problem8 {
        static class TreeInfo {
            int height;
            List<Integer> rightSightList;

            public TreeInfo(int h, List<Integer> valList) {
                this.height = h;
                this.rightSightList = valList;
            }
        }

        public List<Integer> rightSideView(TreeNode root) {
            TreeInfo treeInfo = solution(root);
            return treeInfo.rightSightList;
        }

        private TreeInfo solution(TreeNode current) {
            if (current == null) {
                return new TreeInfo(0, new ArrayList<>());
            }
            List<Integer> rightSightList = new ArrayList<>();
            rightSightList.add(current.val);
            TreeInfo right = solution(current.right);
            TreeInfo left = solution(current.left);
            rightSightList.addAll(right.rightSightList);
            if (left.height > right.height) {
                for (int idx = right.rightSightList.size(); idx < left.rightSightList.size(); idx++) {
                    rightSightList.add(left.rightSightList.get(idx));
                }
            }
            return new TreeInfo(Math.max(right.height, left.height) + 1, rightSightList);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2, null, new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), null)));
        System.out.println(JSONObject.toJSONString(new 二叉树.Problem8().rightSideView(root)));

//
//        TreeNode tree = new 二叉树().new Problem3().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
//        System.out.println(JSONObject.toJSONString(new 二叉树().new Problem1().levelOrder(tree)));
    }
}
