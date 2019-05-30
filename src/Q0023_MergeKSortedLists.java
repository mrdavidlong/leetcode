import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import common.ListNode;

/**
 * Created by davidlong on 6/30/18.
 *
 *
 * Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6

 */
public class Q0023_MergeKSortedLists {
//
//    public ListNode mergeKLists(List<ListNode> lists) {
//        if (lists==null||lists.size()==0) return null;
//
//        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
//            @Override
//            public int compare(ListNode n1,ListNode n2){
//                if (n1.val < n2.val)
//                    return -1;
//                else if (n1.val == n2.val)
//                    return 0;
//                else
//                    return 1;
//            }
//        });
//
//        ListNode dummy = new ListNode(0);
//        ListNode tail = dummy;
//
//        for (ListNode node : lists)
//            if (node != null)
//                queue.add(node);
//
//        while (!queue.isEmpty()){
//            tail.next = queue.poll();
//            tail=tail.next;
//
//            if (tail.next != null)
//                queue.add(tail.next);
//        }
//        return dummy.next;
//    }

    // https://leetcode.com/problems/merge-k-sorted-lists/discuss/10528/A-java-solution-based-on-Priority-Queue
//    public ListNode mergeKLists(List<ListNode> lists) {
//        if (lists == null || lists.size() == 0) return null;
//
//        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.size(), new Comparator<ListNode>(){
//            @Override
//            public int compare(ListNode n1,ListNode n2){
//                if (n1.val < n2.val)
//                    return -1;
//                else if (n1.val == n2.val)
//                    return 0;
//                else
//                    return 1;
//            }
//        });
//
//        ListNode head = null;
//        ListNode tail = null;
//
//        for (ListNode node : lists)
//            if (node != null)
//                queue.offer(node);
//
//        while (!queue.isEmpty()){
//            ListNode n = queue.poll();
//            if (head == null && tail == null) {
//                head = n;
//                tail = n;
//            } else {
//                tail.next = n;
//                tail = tail.next;
//            }
//
//            if (tail.next != null)
//                queue.offer(tail.next);
//        }
//        return head;
//    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

//        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
//            @Override
//            public int compare(ListNode n1, ListNode n2){
//                if (n1.val < n2.val)
//                    return -1;
//                else if (n1.val == n2.val)
//                    return 0;
//                else
//                    return 1;
//            }
//        });
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode head = null;
        ListNode tail = null;

        for (ListNode node : lists)
            if (node != null)
                queue.offer(node);

        while (!queue.isEmpty()){
            ListNode n = queue.poll();
            if (head == null && tail == null) {
                head = n;
                tail = n;
            } else {
                tail.next = n;
                tail = tail.next;
            }

            if (tail.next != null)
                queue.offer(tail.next);
        }
        return head;
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
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);
        n4.next = n5;
        n5.next = n6;

        ListNode n7 = new ListNode(2);
        ListNode n8 = new ListNode(6);
        n7.next = n8;

        Q0023_MergeKSortedLists q = new Q0023_MergeKSortedLists();
        //List<ListNode> listOfLists = Arrays.asList(n1, n4, n7);
        ListNode[] listOfLists = new ListNode[] {n1, n4, n7};
        ListNode resultHead = q.mergeKLists(listOfLists);

        printNode(resultHead);
    }
}
