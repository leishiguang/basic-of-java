package demo.leetcode.easy.linkedlist;

/**
 * 回文链表
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/45/
 * <p>
 * 输入: 1->2
 * 输出: false
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 *
 * @author leishiguang
 * @since v1.0
 */
public class IsPalindrome {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        boolean result = true;
        //取得中间节点（靠左）
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            if (fast == null) {
                break;
            }
            slow = slow.next;
        }
        //翻转中间节点之后的节点
        ListNode middleHeader = slow.next;
        middleHeader = reverse(middleHeader);
        //翻转后的链表（一半长度），与原链表进行对比
        ListNode middleCurrent = middleHeader;
        ListNode startCurrent = head;
        while (middleCurrent != null){
            if(middleCurrent.val != startCurrent.val){
                result = false;
                break;
            }
            middleCurrent = middleCurrent.next;
            startCurrent = startCurrent.next;
        }
        //将原始链表变换回来
        slow.next = reverse(middleHeader);
        return result;
    }

    /**
     * 翻转链表，返回新的链表头
     */
    private ListNode reverse(ListNode head){
        if(head == null){
            return null;
        }
        ListNode currentNode = head;
        ListNode nextNode = head.next;
        head.next = null;
        ListNode tmpNode;
        while (nextNode != null) {
            tmpNode = nextNode.next;
            //交换
            nextNode.next = currentNode;
            //下一轮，指针往后移动
            currentNode = nextNode;
            nextNode = tmpNode;
        }
        return currentNode;
    }
}
