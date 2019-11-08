package demo.leetcode.easy.trees;


/**
 * 将有序数组转换为二叉搜索树，https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/51/
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * @author leishiguang
 * @since v1.0
 */
public class SortedArrayToBst {

  public static void main(String[] args) {
    SortedArrayToBst bst = new SortedArrayToBst();
    int[] nums = new int[]{-10, -3, 0, 1, 2, 3, 5, 9};
    TreeNode treeNode = bst.sortedArrayToBst(nums);
  }

  public TreeNode sortedArrayToBst(int[] nums) {
    if (nums.length == 0) {
      return null;
    }
    return sortedArrayToBst(nums, 0, nums.length - 1);
  }

  public TreeNode sortedArrayToBst(int[] nums, int start, int end) {
    if (end - start == 0) {
      return new TreeNode(nums[start]);
    }
    if (end < start) {
      return null;
    }
    int middle = start + ((end - start) / 2);
    TreeNode treeNode = new TreeNode(nums[middle]);
    treeNode.left = sortedArrayToBst(nums, start, middle - 1);
    treeNode.right = sortedArrayToBst(nums, middle + 1, end);
    return treeNode;
  }
}
