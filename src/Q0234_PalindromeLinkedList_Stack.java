import java.util.Stack;
import common.ListNode;


/**
 * https://leetcode.com/problems/palindrome-linked-list/description/
 */
public class Q0234_PalindromeLinkedList_Stack {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode slow = head;
        ListNode fast = head;
        Stack<Integer> stack = new Stack<>();
        stack.push(slow.val);
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            stack.push(slow.val);
        }

        // odd number of nodes, pop the middle value
        if (fast.next == null) {
            stack.pop();
        }
        // slow now points to one beyond the middle node.
        slow = slow.next;

        while (slow != null) {
            if (slow.val != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Q0234_PalindromeLinkedList_Stack sol = new Q0234_PalindromeLinkedList_Stack();

        ListNode l1a = new ListNode(1);
        ListNode l1b = new ListNode(2);
        ListNode l1c = new ListNode(3);
        ListNode l1d = new ListNode(2);
        ListNode l1e = new ListNode(1);

        l1a.next = l1b;
        l1b.next = l1c;
        l1c.next = l1d;
        l1d.next = l1e;

        boolean isPalindrome1 = sol.isPalindrome(l1a);

        ListNode l2a = new ListNode(1);
        ListNode l2b = new ListNode(2);
        ListNode l2c = new ListNode(2);
        ListNode l2d = new ListNode(1);

        l2a.next = l2b;
        l2b.next = l2c;
        l2c.next = l2d;

        boolean isPalindrome2 = sol.isPalindrome(l2a);


    }
}
