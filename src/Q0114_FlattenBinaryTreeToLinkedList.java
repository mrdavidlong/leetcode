import common.TreeNode;

/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class Q0114_FlattenBinaryTreeToLinkedList {
//    private TreeNode prev = null;
//
//    public void flatten(TreeNode root) {
//        if (root == null)
//            return;
//        flatten(root.right);
//        flatten(root.left);
//        root.right = prev;
//        root.left = null;
//        prev = root;
//    }

//    public void flatten2(TreeNode root) {
//        flatten2(root,null);
//    }
//
//    private TreeNode flatten2(TreeNode root, TreeNode pre) {
//        if (root == null) return pre;
//        pre = flatten2(root.right,pre);
//        pre = flatten2(root.left,pre);
//        root.right = pre;
//        root.left = null;
//        pre = root;
//        return pre;
//    }


    //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/solution/
    private TreeNode flattenTree(TreeNode node) {

        // Handle the null scenario
        if (node == null) {
            return null;
        }

        // For a leaf node, we simply return the
        // node as is.
        if (node.left == null && node.right == null) {
            return node;
        }

        //Recursively flatten the left subtree
        TreeNode leftTail = this.flattenTree(node.left);

        // Recursively flatten the right subtree
        TreeNode rightTail = this.flattenTree(node.right);

        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side
        // anymore.
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        // We need to return the "rightmost" node after we are
        // done wiring the new connections.
        return rightTail == null ? leftTail : rightTail;
    }

    public void flatten(TreeNode root) {
        this.flattenTree(root);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.right = n6;
/*
       1
      / \
     2   5
    / \   \
   3   4   6
 */
        n1.printTreeInOrder();

        Q0114_FlattenBinaryTreeToLinkedList sol = new Q0114_FlattenBinaryTreeToLinkedList();
        sol.flatten(n1);

        n1.printTreeInOrder();
    }
}
