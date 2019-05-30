import common.TreeNode;

/*
https://leetcode.com/problems/count-univalue-subtrees/
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4

 */
public class Q0250_CountUnivalueSubtrees {
    // https://leetcode.com/problems/count-univalue-subtrees/discuss/67573/My-Concise-JAVA-Solution
    public int countUnivalSubtrees(TreeNode root) {
        // using an array of int to pass by reference
        int[] count = new int[1];
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }
        boolean left = helper(node.left, count);
        boolean right = helper(node.right, count);
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Q0250_CountUnivalueSubtrees sol = new Q0250_CountUnivalueSubtrees();
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;

        int uniTreeCount = sol.countUnivalSubtrees(n1);
    }
}
