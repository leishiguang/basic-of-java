package demo.leetcode.easy.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证二叉搜索树
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/48/
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * - 节点的左子树只包含小于当前节点的数
 * - 节点的右子树只包含大于当前节点的数
 * - 所有左子树和右子树自身必须也是二叉搜索树
 *
 * @author leishiguang
 * @since v1.0
 */
public class ValidBst {

    /**
     * 运用中序遍历，得到遍历之后的链表，再根据链表中的字符大小，判断是否符合二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root,res);
        for(int i = 0; i < res.size() - 1; i++){
            if(res.get(i) >= res.get(i+1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(TreeNode node,List<Integer> res){
        if(node.left != null){
            inorderTraversal(node.left,res);
        }
        res.add(node.val);
        if(node.right != null){
            inorderTraversal(node.right,res);
        }
    }


}
