import common.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 *
 * Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 return its depth = 3.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#递归
 */
public class Q0104_MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        Q0104_MaximumDepthOfBinaryTree sol = new Q0104_MaximumDepthOfBinaryTree();

        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);

        t3.left = t9;
        t3.right = t20;
        t20.left = t15;
        t20.right = t7;

        int depth = sol.maxDepth(t3);

    }

}
