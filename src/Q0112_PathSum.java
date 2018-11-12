import common.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/description/
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  6
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#树
 */
public class Q0112_PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        return sumPath(root, 0, sum);
    }

    private boolean sumPath(TreeNode root, int sumSoFar, int sum) {
        if (root == null) return false;

        sumSoFar = root.val + sumSoFar;

        if (root.left == null && root.right == null) {
            return sumSoFar == sum;
        }

        boolean hasPath = sumPath(root.left, sumSoFar, sum);
        if (hasPath) return true;
        return sumPath(root.right, sumSoFar, sum);
    }

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        TreeNode t8 = new TreeNode(8);
        TreeNode t11 = new TreeNode(11);
        TreeNode t13 = new TreeNode(13);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);

        t5.left = t4;
        t5.right = t8;
        t4.left = t11;
        t11.left = t7;
        t11.right = t2;
        t8.left = t13;
        t6.right = t1;

        t5.printTreeInOrder();

        Q0112_PathSum sol = new Q0112_PathSum();
        boolean hasSum = sol.hasPathSum(t5, 22);
        System.out.println(hasSum);


    }

}
