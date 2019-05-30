import common.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/description/
 *
 * Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as:

 a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 Example 1:

 Given the following tree [3,9,20,null,null,15,7]:

 3
 / \
 9  20
 /  \
 15   7
 Return true.

 Example 2:

 Given the following tree [1,2,2,3,3,null,null,4,4]:

 1
 / \
 2   2
 / \
 3   3
 / \
 4   4
 Return false.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#递归

 */
public class Q0110_BalancedBinaryTreeBest {

    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        return height(root)!=-1;

    }
    public int height(TreeNode node){
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = height(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (leftHeight - rightHeight < -1 || leftHeight - rightHeight > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Q0110_BalancedBinaryTreeBest sol = new Q0110_BalancedBinaryTreeBest();

        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);

        t3.left = t9;
        t3.right = t20;
        t20.left = t15;
        t20.right = t7;

        boolean isBalanced = sol.isBalanced(t3);

    }
}
