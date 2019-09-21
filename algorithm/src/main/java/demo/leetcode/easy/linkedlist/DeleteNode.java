package demo.leetcode.easy.linkedlist;

/**
 * 删除链表中的节点
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/41/
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * @author leishiguang
 * @since v1.0
 */
public class DeleteNode {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 这个考察的是，要把当前节点的值替换成下一个节点的啊...
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
