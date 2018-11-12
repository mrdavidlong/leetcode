import common.ListNode;

import java.util.List;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

 Example 1:

 Input: 1->2->3->3->4->4->5
 Output: 1->2->5
 Example 2:

 Input: 1->1->1->2->3
 Output: 2->3
 */
public class Q0082_RemoveDuplicatesFromSortedListII {
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/discuss/28335/My-accepted-Java-code
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next!=null&&cur.val==cur.next.val) {
                cur=cur.next;
            }
            if (pre.next==cur) {
                pre=pre.next;
            } else {
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Q0082_RemoveDuplicatesFromSortedListII sol = new Q0082_RemoveDuplicatesFromSortedListII();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l3b = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l4b = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l3b;
        l3b.next = l4;
        l4.next = l4b;
        l4b.next = l5;

        ListNode newList = sol.deleteDuplicates(l1);

        ListNode m1 = new ListNode(1);
        ListNode m1b = new ListNode(1);
        ListNode m1c = new ListNode(1);
        ListNode m2 = new ListNode(2);
        ListNode m3 = new ListNode(3);

        m1.next = m1b;
        m1b.next = m1c;
        m1c.next = m2;
        m2.next = m3;

        ListNode newList2 = sol.deleteDuplicates(m1);
    }

}
