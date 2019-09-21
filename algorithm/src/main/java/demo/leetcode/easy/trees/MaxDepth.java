package demo.leetcode.easy.trees;

/**
 * 二叉树的最大深度
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/47/
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * 返回它的最大深度 3
 *
 * @author leishiguang
 * @since v1.0
 */
public class MaxDepth {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return doMaxDepth(root);
    }

    public int doMaxDepth(TreeNode root){
        int leftMax = 1;
        int rightMax = 1;
        if(root.left == null && root.right == null){
            return 1;
        }
        if(root.left != null){
            leftMax = doMaxDepth(root.left) + 1;
        }
        if(root.right != null){
            rightMax = doMaxDepth(root.right) + 1;
        }
        return Math.max(leftMax, rightMax);
    }
}
