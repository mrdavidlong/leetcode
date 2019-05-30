import common.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]

         _______6______
        /              \
    ___2__          ___8__
   /      \        /      \
   0      4       7       9
         /  \
        3   5
 Example 1:

 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 Output: 6
 Explanation: The LCA of nodes 2 and 8 is 6.
 Example 2:

 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 Output: 2
 Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
 according to the LCA definition.
 Note:

 All of the nodes' values will be unique.
 p and q are different and both values will exist in the BST.


 */
public class Q0235_LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestorRecur(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;

        while (node != null) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else {
                return node;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Q0235_LowestCommonAncestorOfABinarySearchTree sol = new Q0235_LowestCommonAncestorOfABinarySearchTree();
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t8 = new TreeNode(8);
        TreeNode t0 = new TreeNode(0);
        TreeNode t4 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3);
        TreeNode t5 = new TreeNode(5);
        TreeNode t7 = new TreeNode(7);
        TreeNode t9 = new TreeNode(9);
        t6.left = t2;
        t6.right = t8;
        t2.left = t0;
        t2.right = t4;
        t8.left = t7;
        t8.right = t9;
        t4.left = t3;
        t4.right = t5;

        TreeNode common1 = sol.lowestCommonAncestor(t6, t2, t8);
        TreeNode common2 = sol.lowestCommonAncestor(t6, t2, t4);
        TreeNode common3 = sol.lowestCommonAncestor(t6, t0, t3);
    }
}
