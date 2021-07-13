import common.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
   2
  / \
 1   3
 Output: true
 Example 2:

     5
    / \
   1   4
      / \
     3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.
 */
public class Q0098_ValidateBinarySearchTree {
    // https://leetcode.com/problems/validate-binary-search-tree/discuss/32138/Another-passed-Java-solution
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;

        // left node has to be less than the current node, not equal
        if ((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;

        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }

    public static void main(String[] args) {
        Q0098_ValidateBinarySearchTree sol = new Q0098_ValidateBinarySearchTree();

        TreeNode s2 = new TreeNode(2);
        TreeNode s1 = new TreeNode(1);
        TreeNode s3 = new TreeNode(3);
        s2.left = s1;
        s2.right = s3;

        boolean isValid = sol.isValidBST(s2);

        TreeNode t5 = new TreeNode(5);
        TreeNode t1 = new TreeNode(1);
        TreeNode t4 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3);
        TreeNode t6 = new TreeNode(6);

        t5.left = t1;
        t5.right = t4;
        t4.left = t3;
        t4.right = t6;

        /*
        5
       / \
      1   4
          /\
         3  6
         */

        boolean isValid2 = sol.isValidBST(t5);

    }
}
