import common.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * Reverse a linked list from position m to n. Do it in one-pass.

 Note: 1 ≤ m ≤ n ≤ length of list.

 Example:

 Input: 1->2->3->4->5->NULL, m = 2, n = 4
 Output: 1->4->3->2->5->NULL
 */
public class Q0092_ReverseLinkedListII {
    // https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        for (int i = 0; i < n - m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // m = 2; n = 4;
        // before reversing: dummy-> 1 -> 2 -> 3 -> 4 -> 5; pre = 1, start = 2, then = 3
        // first reversing:  dummy-> 1 -> 3 -> 2 -> 4 -> 5; pre = 1, start = 2, then = 4
        // second reversing: dummy-> 1 -> 4 -> 3 -> 2 -> 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;
    }

    public static void main(String[] args) {
        Q0092_ReverseLinkedListII sol = new Q0092_ReverseLinkedListII();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        l1.print();
        System.out.println();

        ListNode newList = sol.reverseBetween(l1, 2, 4);
        newList.print();
    }
}
