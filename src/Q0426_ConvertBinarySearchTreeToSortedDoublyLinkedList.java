
/*
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.


Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.



 */
public class Q0426_ConvertBinarySearchTreeToSortedDoublyLinkedList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

//    public Node treeToDoublyList(Node root) {
//        if (root == null) {
//            return null;
//        }
//
//        Node leftHead = treeToDoublyList(root.left);
//        Node rightHead = treeToDoublyList(root.right);
//        root.left = root;
//        root.right = root;
//        return connect(connect(leftHead, root), rightHead);
//    }
//
//    // Used to connect two circular doubly linked lists. n1 is the head as well as n2.
//    private Node connect(Node n1, Node n2) {
//        if (n1 == null) {
//            return n2;
//        }
//        if (n2 == null) {
//            return n1;
//        }
//
//        Node tail1 = n1.left;
//        Node tail2 = n2.left;
//
//        tail1.right = n2;
//        n2.left = tail1;
//        tail2.right = n1;
//        n1.left = tail2;
//
//        return n1;
//    }

    // the smallest (first) and the largest (last) nodes
    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            }
            else {
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node;
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }

    public static void main(String[] args) {
        Q0426_ConvertBinarySearchTreeToSortedDoublyLinkedList sol = new Q0426_ConvertBinarySearchTreeToSortedDoublyLinkedList();
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);
        Node n5 = new Node(5, null, null);
        Node n4 = new Node(4, n2, n5);

        Node ddl = sol.treeToDoublyList(n4);
    }
}
