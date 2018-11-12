import common.TreeNode;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 *
 * Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

  3
 / \
 9  20
   /  \
  15   7
 return its minimum depth = 2.
 */
public class Q0111_MinimumDepthOfBinaryTree {
//    public int minDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        } else if (root.left == null && root.right == null) {
//            return 1;
//        } else if (root.left == null) {
//            return 1 + minDepth(root.right);
//        } else if (root.right == null) {
//            return 1 + minDepth(root.left);
//        } else if  (root.left != null && root.right != null) {
//            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
//        }
//        return 0;
//    }
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    public static void main(String[] args) {
        Q0111_MinimumDepthOfBinaryTree sol = new Q0111_MinimumDepthOfBinaryTree();

        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);

        t3.left = t9;
        t3.right = t20;
        t20.left = t15;
        t20.right = t7;

        int minDepth = sol.minDepth(t3);



    }
}
