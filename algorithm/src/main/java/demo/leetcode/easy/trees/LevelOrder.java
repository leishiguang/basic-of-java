package demo.leetcode.easy.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的层次遍历
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/50/
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author leishiguang
 * @since v1.0
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            ArrayList<TreeNode> next = new ArrayList<>();
            next.add(root);
            addNow(result,next);
        }
        return result;
    }

    private void addNow(List<List<Integer>> result, ArrayList<TreeNode> nodes) {
        List<Integer> now = new ArrayList<>();
        ArrayList<TreeNode> next = new ArrayList<>();
        for (TreeNode node : nodes) {
            now.add(node.val);
            if (node.left != null) {
                next.add(node.left);
            }
            if (node.right != null) {
                next.add(node.right);
            }
        }
        result.add(now);
        if (next.size() != 0) {
            addNow(result, next);
        }
    }
}
