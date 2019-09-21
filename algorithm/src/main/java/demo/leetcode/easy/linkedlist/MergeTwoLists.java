package demo.leetcode.easy.linkedlist;

import java.util.List;

/**
 * 合并两个有序链表
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/44/
 * <p>
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author leishiguang
 * @since v1.0
 */
public class MergeTwoLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 递归解法
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHeader;
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            newHeader = l1;
            newHeader.next = mergeTwoLists(l1.next, l2);
        } else {
            newHeader = l2;
            newHeader.next = mergeTwoLists(l1, l2.next);
        }
        return newHeader;
    }
}
