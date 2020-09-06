import common.ListNode;

/**
 * https://leetcode.com/problems/rotate-list/description/
 *
 Given a linked list, rotate the list to the right by k places, where k is non-negative.

 Example 1:

 Input: 1->2->3->4->5->NULL, k = 2
 Output: 4->5->1->2->3->NULL
 Explanation:
 rotate 1 steps to the right: 5->1->2->3->4->NULL
 rotate 2 steps to the right: 4->5->1->2->3->NULL
 Example 2:

 Input: 0->1->2->NULL, k = 4
 Output: 2->0->1->NULL
 Explanation:
 rotate 1 steps to the right: 2->0->1->NULL
 rotate 2 steps to the right: 1->2->0->NULL
 rotate 3 steps to the right: 0->1->2->NULL
 rotate 4 steps to the right: 2->0->1->NULL
 */
public class Q0061_RotateList {
    /*
    https://leetcode.com/problems/rotate-list/discuss/22715/Share-my-java-solution-with-explanation
    Since n may be a large number compared to the length of list. So we need to know the length of linked list.After that, move the list after the (l-n%l )th node to the front to finish the rotation.

Ex: {1,2,3} k=2 Move the list after the 1st node to the front

Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.

So the code has three parts.

Get the length

Move to the (l-n%l)th node

3)Do the rotation
    */
//    public ListNode rotateRight(ListNode head, int n) {
//        if (head==null||head.next==null) return head;
//        ListNode dummy=new ListNode(0);
//        dummy.next=head;
//        ListNode fast=dummy,slow=dummy;
//
//        int i;
//        for (i=0;fast.next!=null;i++)//Get the total length
//            fast=fast.next;
//
//        for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
//            slow=slow.next;
//
//        // Do the rotation
//        fast.next=dummy.next; // connect the tail to the head
//        dummy.next=slow.next; // dummy.next points to the head of new list
//        slow.next=null; // cut off at the new tail
//
//        return dummy.next;
//    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;

        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        int newHeadIndex = len - k % len;
        if (newHeadIndex == 0) return head;

        // connect the tail the head
        tail.next = head;

        // get the new head
        ListNode newHead = head;
        ListNode newTail = head; // newTail is the previous node before newHead
        for (int i = 0; i < newHeadIndex; i++) {
            newHead = newHead.next;
            if (i > 0) newTail = newTail.next;
        }

        // cut off the tail
        newTail.next = null;

        return newHead;
    }


    public static void main(String[] args) {
        Q0061_RotateList sol = new Q0061_RotateList();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode newList = sol.rotateRight(l1, 1);
    }
}
