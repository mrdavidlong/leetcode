import common.ListNode;

/**
 * Created by davidlong on 6/30/18.
 */
public class Q0025_ReverseNodesInKGroup {

    public ListNode reverseKGroupByDavid(ListNode head, int k) {
        // If the length is smaller than k, then just return the linked list
        ListNode node = head;
        int count = 0;
        while (node != null && count < k) {
            node = node.next;
            count++;
        }
        if (count < k) return head;

        // reverse the k group
        ListNode prevNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;
        count = 0;
        while (curNode != null && count < k) {
            nextNode = curNode.next;
            curNode.next = prevNode;

            prevNode = curNode;
            curNode = nextNode;
            count++;
        }

        ListNode groupTail = head;
        head = prevNode;

        // reverse the next k group
        groupTail.next = reverseKGroupByDavid(curNode, k);

        return head;
    }

    // https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11423/Short-but-recursive-Java-code-with-comments
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        Q0025_ReverseNodesInKGroup q = new Q0025_ReverseNodesInKGroup();
        //ListNode resultNode = q.reverseKGroup(n1, 3);
        ListNode resultNode = q.reverseKGroupByDavid(n1, 3);
        resultNode.print();
    }
}
