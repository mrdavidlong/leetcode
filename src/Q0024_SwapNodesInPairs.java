import common.ListNode;
/**
 * Created by davidlong on 6/30/18.
 */
public class Q0024_SwapNodesInPairs {

    public ListNode swapPairsRecursion(ListNode head) {
        if ((head == null) || (head.next == null)) return head;

        ListNode n = head.next;
        head.next = swapPairsRecursion(head.next.next);
        n.next = head;
        return n;
    }

    public ListNode swapPairsIteration(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) {
            ListNode l1 = pre.next, l2 = pre.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            pre.next = l2;

            pre = l1;
        }
        return node.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Q0024_SwapNodesInPairs q = new Q0024_SwapNodesInPairs();
        ListNode resultNode = q.swapPairsIteration(n1);
        resultNode.print();
        ListNode resultNodeRec = q.swapPairsRecursion(n1);
        resultNodeRec.print();
    }
}
