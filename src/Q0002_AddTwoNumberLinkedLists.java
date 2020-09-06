import common.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */
public class Q0002_AddTwoNumberLinkedLists {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);

            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    // From CtCI
    public static ListNode addLists(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int value = carry;
        if (l1 != null) {
            value += l1.val;
        }
        if (l2 != null) {
            value += l2.val;
        }

        ListNode result = new ListNode(value % 10);
        int nextCarry = value /10;

        if (l1 != null || l2 != null) {
            ListNode more = addLists(l1 == null ? null : l1.next,
                                     l2 == null ? null : l2.next,
                                     nextCarry);
            result.next = more;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        System.out.print("l1: ");
        l1.print();

        ListNode l2 = new ListNode(8);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(9);
        System.out.print("l2: ");
        l2.print();
        System.out.println();


        // 347 + 958 =  1305

        ListNode result = addTwoNumbers(l1, l2);
        System.out.print("result: ");
        result.print();
        System.out.println();

        ListNode result2 = addLists(l1, l2, 0);
        System.out.print("result2: ");
        result2.print();
        System.out.println();

        ListNode result3 = addLists(new ListNode(8), new ListNode(7), 0);
        System.out.print("result3: ");
        result3.print();
        System.out.println();
    }
}
