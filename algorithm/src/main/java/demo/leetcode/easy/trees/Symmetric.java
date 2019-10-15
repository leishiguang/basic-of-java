package demo.leetcode.easy.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 对称二叉树
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/49/
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 * @author leishiguang
 * @since v1.0
 */
public class Symmetric {

    //深度遍历
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        if (root.left != null) {
            treeNodes.add(root.left);
        }
        if (root.right != null) {
            treeNodes.add(root.right);
        }
        return isSymmetric(treeNodes);
    }

    private boolean isSymmetric(List<TreeNode> treeNodes) {
        if (treeNodes.size() % 2 == 1) {
            return false;
        }
        if (treeNodes.size() == 0) {
            return true;
        }
        int middle = treeNodes.size() / 2;
        //middle += treeNodes.size() % 2;
        List<TreeNode> rightTreeNodes = new ArrayList<>();
        List<TreeNode> leftTreeNodes = new ArrayList<>();
        //判断当前层的节点是否对称
        int size = treeNodes.size();
        for (int i = 0; i < middle; i++) {
            //判断当前层是否对称
            TreeNode left = treeNodes.get(i);
            TreeNode right = treeNodes.get(size - i - 1);
            if (left.left == null && right.right != null) {
                return false;
            }
            if (left.left != null && right.right == null) {
                return false;
            }
            if (left.right == null && right.left != null) {
                return false;
            }
            if (left.right != null && right.left == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            if (left.left != null && right.right != null) {
                leftTreeNodes.add(left.left);
                rightTreeNodes.add(0, right.right);
            }
            if (left.right != null && right.left != null) {
                leftTreeNodes.add(left.right);
                rightTreeNodes.add(0, right.left);
            }
        }
        //左右节点合并在一起
        leftTreeNodes.addAll(rightTreeNodes);
        //校验下一层
        return isSymmetric(leftTreeNodes);
    }


    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return v(root.left, root.right);
    }

    public boolean v(TreeNode left, TreeNode right) {
        if (left != null && right != null) {
            if (left.val == right.val) {
                return v(left.right, right.left) && v(right.right, left.left);
            } else {
                return false;
            }
        } else {
            return left == right;
        }
    }

    public boolean isSymmetric3(TreeNode root) {
        return isMorror(root, root);
    }

    public boolean isMorror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val){
            return false;
        }
        return isMorror(root1.right, root2.left) && isMorror(root1.left, root2.right);
    }
}
