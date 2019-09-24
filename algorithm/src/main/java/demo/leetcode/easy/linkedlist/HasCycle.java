package demo.leetcode.easy.linkedlist;

/**
 * 环形链表
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/46/
 * 给定一个链表，判断链表中是否有环。
 *
 * @author leishiguang
 * @since v1.0
 */
public class HasCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 快慢指针看链表中是否有环，如果快指针追上了慢指针，就说明有环
     */
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null){
            if(slow == fast){
                return true;
            }
            fast = fast.next;
            if(fast == null){
                break;
            }else{
                fast = fast.next;
            }
            slow = slow.next;
        }
        return false;
    }
}
