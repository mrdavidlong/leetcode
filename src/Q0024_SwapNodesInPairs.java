import common.ListNode;
/**
 * Created by davidlong on 6/30/18.
 */
public class Q0024_SwapNodesInPairs {

    public ListNode swapPairsByDavid(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode n1 = head;
        ListNode n2 = head.next;
        while (n1 != null && n2 != null) {
            int temp = n1.val;
            n1.val = n2.val;
            n2.val = temp;

            if (n2.next == null) break;
            if (n2.next != null && n2.next.next == null) throw new IllegalArgumentException("List cannot be have odd number of items");
            n1 = n1.next.next;
            n2 = n2.next.next;
        }
        return head;
    }

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
        //ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        //n3.next = n4;

        Q0024_SwapNodesInPairs q = new Q0024_SwapNodesInPairs();
        ListNode resultNode = q.swapPairsIteration(n1);
        resultNode.print();
    }
}
