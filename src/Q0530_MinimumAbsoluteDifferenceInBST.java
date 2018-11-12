import common.TreeNode;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

 Example:

 Input:

 1
 \
 3
 /
 2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 Note: There are at least two nodes in this BST.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#bst
 */
public class Q0530_MinimumAbsoluteDifferenceInBST {
    private int minDiff = Integer.MAX_VALUE;
    private TreeNode preNode = null;

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return minDiff;
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        if (preNode != null) minDiff = Math.min(minDiff, node.val - preNode.val);
        preNode = node;
        inOrder(node.right);
    }

    public static void main(String[] args) {
        Q0530_MinimumAbsoluteDifferenceInBST sol = new Q0530_MinimumAbsoluteDifferenceInBST();

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.right = t3;
        t3.left = t2;

        int minDiff = sol.getMinimumDifference(t1);
    }

}
