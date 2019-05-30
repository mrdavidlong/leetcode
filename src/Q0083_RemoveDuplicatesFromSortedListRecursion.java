import common.ListNode;
/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#链表
 */
public class Q0083_RemoveDuplicatesFromSortedListRecursion {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public static void main(String[] args) {
        Q0083_RemoveDuplicatesFromSortedListRecursion sol = new Q0083_RemoveDuplicatesFromSortedListRecursion();

        ListNode l1 = new ListNode(1);
        ListNode l1b = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3a = new ListNode(3);
        ListNode l3b = new ListNode(3);

        l1.next = l1b;
        l1b.next = l2;
        l2.next = l3a;
        l3a.next = l3b;

        ListNode resultNode = sol.deleteDuplicates(l1);
    }
}
