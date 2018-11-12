import java.util.Stack;
import common.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 *
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:

 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7
 *
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#链表
 */
public class Q0445_AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = buildStack(l1);
        Stack<Integer> l2Stack = buildStack(l2);
        //ListNode head = new ListNode(-1);
        ListNode head = null;
        int carry = 0;
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry != 0) {
            int x = l1Stack.isEmpty() ? 0 : l1Stack.pop();
            int y = l2Stack.isEmpty() ? 0 : l2Stack.pop();
            int sum = x + y + carry;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }

    private Stack<Integer> buildStack(ListNode l) {
        Stack<Integer> stack = new Stack<>();
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }

    public static void main(String[] args) {
        Q0445_AddTwoNumbersII sol = new Q0445_AddTwoNumbersII();

        ListNode l1a = new ListNode(7);
        ListNode l1b = new ListNode(2);
        ListNode l1c = new ListNode(4);
        ListNode l1d = new ListNode(3);
        l1a.next = l1b;
        l1b.next = l1c;
        l1c.next = l1d;

        ListNode l2a = new ListNode(5);
        ListNode l2b = new ListNode(6);
        ListNode l2c = new ListNode(4);
        l2a.next = l2b;
        l2b.next = l2c;

        ListNode resultList = sol.addTwoNumbers(l1a, l2a);

    }
}
