import common.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

           _______3______
          /              \
      ___5__          ___1__
     /      \        /      \
    6       2       0       8
           /  \
          7   4
 Example 1:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of of nodes 5 and 1 is 3.
 Example 2:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
 according to the LCA definition.
 Note:

 All of the nodes' values will be unique.
 p and q are different and both values will exist in the binary tree.

 */
public class Q0236_LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else { // both left and right is not null, so root is the LCA
            return root;
        }
        //return left == null ? right : (right == null ? left : root);
    }

    public static void main(String[] args) {
        Q0236_LowestCommonAncestorOfABinaryTree sol = new Q0236_LowestCommonAncestorOfABinaryTree();
        TreeNode t0 = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);

        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t1.left = t0;
        t1.right = t8;
        t2.left = t7;
        t2.right = t4;

        /*
         3
       /   \
      5     1
     / \    / \
    6   2  0   8
       / \
      7   4
         */

        TreeNode common1 = sol.lowestCommonAncestor(t3, t5, t1);
        TreeNode common2 = sol.lowestCommonAncestor(t3, t5, t4);
        TreeNode common3 = sol.lowestCommonAncestor(t3, t6, t4);

    }
}
