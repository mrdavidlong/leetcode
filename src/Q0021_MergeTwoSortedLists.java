import common.ListNode;
/**
 * Created by davidlong on 6/30/18.
 *
 * Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 */
public class Q0021_MergeTwoSortedLists {
//    public ListNode mergeTwoListsByDavid(ListNode l1, ListNode l2) {
//        ListNode mergedListHead = null;
//        ListNode mergedListTail = null;
//
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                ListNode node = new ListNode(l1.val);
//
//                if (mergedListHead == null) {
//                    mergedListHead = node;
//                    mergedListTail = node;
//                } else {
//                    mergedListTail.next = node;
//                    mergedListTail = node;
//                }
//                l1 = l1.next;
//            } else {
//                ListNode node = new ListNode(l2.val);
//
//                if (mergedListHead == null) {
//                    mergedListHead = node;
//                    mergedListTail = node;
//                } else {
//                    mergedListTail.next = node;
//                    mergedListTail = node;
//                }
//                l2 = l2.next;
//            }
//        }
//
//
//        while (l1 != null) {
//            ListNode node = new ListNode(l1.val);
//
//            if (mergedListHead == null) {
//                mergedListHead = node;
//                mergedListTail = node;
//            } else {
//                mergedListTail.next = node;
//                mergedListTail = node;
//            }
//            l1 = l1.next;
//        }
//
//        while (l2 != null) {
//            ListNode node = new ListNode(l2.val);
//
//            if (mergedListHead == null) {
//                mergedListHead = node;
//                mergedListTail = node;
//            } else {
//                mergedListTail.next = node;
//                mergedListTail = node;
//            }
//            l2 = l2.next;
//        }
//
//        return mergedListHead;
//    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    private static void printNode(ListNode head) {
        if (head != null) {
            System.out.print(head.val + " ");
            if (head.next != null) {
                printNode(head.next);
            }
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);
        n4.next = n5;
        n5.next = n6;

        Q0021_MergeTwoSortedLists q = new Q0021_MergeTwoSortedLists();
        ListNode mergedList = q.mergeTwoLists(n1, n4);
        printNode(mergedList);
    }
}
