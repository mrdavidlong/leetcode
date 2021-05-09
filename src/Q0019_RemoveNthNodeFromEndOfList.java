import common.ListNode;
/**
 * Created by davidlong on 6/30/18.
 * Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.

 */
public class Q0019_RemoveNthNodeFromEndOfList {
public ListNode removeNthFromEndByDavid(ListNode head, int n) {
    if (head == null) throw new IllegalArgumentException("linked list cannot be null");

    ListNode fast = head;
    for (int i = 1; i < n; i++) {
        if (fast.next != null) fast = fast.next;
        else throw new IllegalArgumentException("n cannot be less than the length of the linked list");
    }

    // delete the head
    if (fast.next == null) {
        return head.next;
    }

    ListNode slow = head;
    ListNode prev = null;
    while (fast.next != null) {
        prev = slow;
        fast = fast.next;
        slow = slow.next;
    }

    prev.next = slow.next;
    return head;
}

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#链表
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) return head.next;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private static void printNode(ListNode head) {
        if (head != null) {
            System.out.print(head.val + " ");
            printNode(head.next);
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Q0019_RemoveNthNodeFromEndOfList q = new Q0019_RemoveNthNodeFromEndOfList();
        ListNode resultNode = q.removeNthFromEnd(n1, 7);
        printNode(resultNode);

    }

}
