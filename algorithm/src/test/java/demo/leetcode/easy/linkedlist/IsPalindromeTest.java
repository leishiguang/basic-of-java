package demo.leetcode.easy.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试
 *
 * @author leishiguang
 * @since v1.0
 */
class IsPalindromeTest {

    private List<IsPalindrome.ListNode> cases = new ArrayList<>();
    private List<Boolean> results = new ArrayList<>();

    @BeforeEach
    void setUp() {

        IsPalindrome.ListNode listNodeD1 = new IsPalindrome.ListNode(1);
        cases.add(listNodeD1);
        results.add(true);

        IsPalindrome.ListNode listNodeA1 = new IsPalindrome.ListNode(1);
        IsPalindrome.ListNode listNodeA2 = new IsPalindrome.ListNode(2);
        IsPalindrome.ListNode listNodeA3 = new IsPalindrome.ListNode(3);
        IsPalindrome.ListNode listNodeA4 = new IsPalindrome.ListNode(2);
        IsPalindrome.ListNode listNodeA5 = new IsPalindrome.ListNode(1);
        listNodeA1.next = listNodeA2;
        listNodeA2.next = listNodeA3;
        listNodeA3.next = listNodeA4;
        listNodeA4.next = listNodeA5;
        cases.add(listNodeA1);
        results.add(true);

        IsPalindrome.ListNode listNodeB1 = new IsPalindrome.ListNode(1);
        IsPalindrome.ListNode listNodeB2 = new IsPalindrome.ListNode(2);
        IsPalindrome.ListNode listNodeB3 = new IsPalindrome.ListNode(3);
        IsPalindrome.ListNode listNodeB4 = new IsPalindrome.ListNode(3);
        IsPalindrome.ListNode listNodeB5 = new IsPalindrome.ListNode(2);
        IsPalindrome.ListNode listNodeB6 = new IsPalindrome.ListNode(1);
        listNodeB1.next = listNodeB2;
        listNodeB2.next = listNodeB3;
        listNodeB3.next = listNodeB4;
        listNodeB4.next = listNodeB5;
        listNodeB5.next = listNodeB6;
        cases.add(listNodeB1);
        results.add(true);

        IsPalindrome.ListNode listNodeC1 = new IsPalindrome.ListNode(1);
        listNodeC1.next = new IsPalindrome.ListNode(2);
        cases.add(listNodeC1);
        results.add(false);
    }

    @Test
    void test(){
        IsPalindrome isPalindrome = new IsPalindrome();
        for(int i = 0; i < cases.size(); i++){
            assertEquals(results.get(i),isPalindrome.isPalindrome(cases.get(i)));
        }
    }
}