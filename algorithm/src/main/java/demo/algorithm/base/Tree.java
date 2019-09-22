package demo.algorithm.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 树的遍历
 *
 * @author leishiguang
 * @since v1.0
 */
public class Tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 中序遍历（递归） left->root->right
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            //先遍历左节点
            if (root.left != null) {
                helper(root.left, res);
            }
            //遍历root节点
            res.add(root.val);
            //遍历右节点
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }

    /**
     * 中序遍历，使用了栈
     */
    public List<Integer> inorderTraversalWithStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }


    /**
     * 前序遍历，借用堆栈实现
     * 从根节点开始，每次迭代弹出当前栈顶元素，并将其孩子节点压入栈中，先压右孩子再压左孩子。
     * 在这个算法中，输出到最终结果的顺序按照 Top->Bottom 和 Left->Right，符合前序遍历的顺序。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        // 根节点入栈
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 出栈，取出一个
            output.add(node.val);
            // 右节点入栈（后出）
            if (node.right != null) {
                stack.add(node.right);
            }
            // 左节点入栈（先出）
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }

    /**
     * 后序遍历
     * 从根节点开始依次迭代，弹出栈顶元素输出到输出列表中，然后依次压入它的所有孩子节点，按照从上到下、从左至右的顺序依次压入栈中。
     * 因为深度优先搜索后序遍历的顺序是从下到上、从左至右，所以需要将输出列表逆序输出。
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }


}
