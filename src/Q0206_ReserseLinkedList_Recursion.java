import common.ListNode;
/**
 * Created by davidlong on 9/16/18.
 */
public class Q0206_ReserseLinkedList_Recursion {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Q0206_ReserseLinkedList_Recursion sol = new Q0206_ReserseLinkedList_Recursion();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        printList(l1);

        ListNode root = sol.reverseList(l1);

        printList(root);

    }

    public static void printList(ListNode l) {
        while (l != null) {
            System.out.print(l.val + " ");
            l = l.next;
        }
    }
}
