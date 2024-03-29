import common.ListNode;

/**
 * https://leetcode.com/problems/partition-list/description/
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 Example:

 Input: head = 1->4->3->2->5->2, x = 3
 Output: 1->2->2->4->3->5
 */
public class Q0086_PartitionList {
// https://leetcode.com/problems/partition-list/discuss/29183/Concise-java-code-with-explanation-one-pass
//    public ListNode partition(ListNode head, int x) {
//        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
//        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
//        while (head!=null){
//            if (head.val < x) {
//                curr1.next = head;
//                curr1 = head;
//            } else {
//                curr2.next = head;
//                curr2 = head;
//            }
//            head = head.next;
//        }
//        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
//        curr1.next = dummy2.next;
//        return dummy1.next;
//    }

    // from CtCI
    public ListNode partition(ListNode head, int x) {
        ListNode beforeStart = null, beforeEnd = null;
        ListNode afterStart = null, afterEnd = null;
        while (head!=null) {
            if (head.val < x) {
                if (beforeStart == null) {
                    beforeStart = head;
                    beforeEnd = head;
                } else {
                    beforeEnd.next = head;
                }
                beforeEnd = head;
            } else {
                if (afterStart == null) {
                    afterStart = head;
                    afterEnd = head;
                } else {
                    afterEnd.next = head;
                }
                afterEnd = head;
            }
            head = head.next;
        }
        if (afterEnd != null) afterEnd.next = null; // cut the end
        if (beforeEnd != null) beforeEnd.next = afterStart;
        return (beforeStart != null) ? beforeStart : afterStart;
    }

    public static void main(String[] args) {
        Q0086_PartitionList sol = new Q0086_PartitionList();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(2);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        ListNode newList = sol.partition(l1, 3);
    }
}
