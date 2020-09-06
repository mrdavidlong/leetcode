import common.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 *
 *
 Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 Example:
 Given a binary tree

 1
 / \
 2   3
 / \
 4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class Q0543_DiameterOfBinaryTree_PassByRef_Best {

    static class Max {
        private int value = 0;

        Max(int theValue) {
            value = theValue;
        }

        void setValue(int theValue) {
            value = theValue;
        }

        int getValue() {
            return value;
        }

    }

    public int diameterOfBinaryTree(TreeNode root) {
        Max max = new Max(0);
        depth(root, max);
        return max.getValue();
    }

    private int depth(TreeNode root, Max max) {
        if (root == null) return 0;

        int leftDepth = depth(root.left, max);
        int rightDepth = depth(root.right, max);

        max.setValue(Math.max(max.getValue(), leftDepth + rightDepth));
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        Q0543_DiameterOfBinaryTree_PassByRef_Best sol = new Q0543_DiameterOfBinaryTree_PassByRef_Best();

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        int diameter = sol.diameterOfBinaryTree(t1);
        System.out.println(diameter);
    }
}
