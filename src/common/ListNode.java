package common;

/**
 * Created by davidlong on 6/30/18.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public void print() {
        System.out.print(val + " ");
        if (next != null) {
            next.print();
        }
    }
//
//    public static void print(ListNode n) {
//        while(n != null) {
//            System.out.print(n.val + " ");
//            n = n.next;
//        }
//    }
}

