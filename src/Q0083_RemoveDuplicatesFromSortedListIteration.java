import common.ListNode;
/**
 * Created by davidlong on 9/17/18.
 */
public class Q0083_RemoveDuplicatesFromSortedListIteration {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Q0083_RemoveDuplicatesFromSortedListIteration sol = new Q0083_RemoveDuplicatesFromSortedListIteration();

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
