package demo.leetcode.easy.linkedlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 删除链表的倒数第N个节点
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/42/
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * @author leishiguang
 * @since v1.0
 */
public class RemoveNthFromEnd {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = null;
        ListNode fast = head;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            fast = fast.next;
            if (slow == null) {
                slow = head;
            } else {
                slow = slow.next;
            }
        }
        if (slow == null) {
            return head.next;
        } else {
            slow.next = slow.next.next;
        }
        return head;
    }

}
