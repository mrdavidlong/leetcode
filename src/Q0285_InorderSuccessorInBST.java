import common.Tree;
import common.TreeNode;

/*
https://leetcode.com/problems/inorder-successor-in-bst/
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

Example 1:

Input: root = [2,1,3], p = 1

  2
 / \
1   3

Output: 2
Example 2:

Input: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /
1

Output: null
 */
public class Q0285_InorderSuccessorInBST {
    //https://leetcode.com/problems/inorder-successor-in-bst/discuss/72653/Share-my-Java-recursive-solution
    //Successor
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    //Predecessor
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val >= p.val) {
            return predecessor(root.left, p);
        } else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }

    public static void main(String[] args) {
        Q0285_InorderSuccessorInBST sol = new Q0285_InorderSuccessorInBST();

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n5.left = n3;
        n5.right = n6;
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;

        TreeNode successor = sol.successor(n5, n1);
        TreeNode successor2 = sol.successor(n5, n2);
        TreeNode successor3 = sol.successor(n5, n3);
        TreeNode successor4 = sol.successor(n5, n4);
        TreeNode successor5 = sol.successor(n5, n5);
        TreeNode successor6 = sol.successor(n5, n6);
    }
}
