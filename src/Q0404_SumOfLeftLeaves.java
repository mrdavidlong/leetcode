import common.TreeNode;

/**
 * https://leetcode.com/problems/sum-of-left-leaves/description/
 *
 * Find the sum of all left leaves in a given binary tree.

 Example:

  3
 / \
 9  20
   /  \
  15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

 */
public class Q0404_SumOfLeftLeaves {
//    int sum = 0;
//
//    public int sumOfLeftLeaves(TreeNode root) {
//        if (root == null) return 0;
//        sumOfLeftLeavesCore(root);
//        return sum;
//    }
//
//    private void sumOfLeftLeavesCore(TreeNode root) {
//        if (root == null) return;
//        if (root.left != null && root.left.left == null && root.left.right == null) {
//            sum += root.left.val;
//        }
//        sumOfLeftLeavesCore(root.left);
//        sumOfLeftLeavesCore(root.right);
//    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }

    private boolean isLeaf(TreeNode node){
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        Q0404_SumOfLeftLeaves sol = new Q0404_SumOfLeftLeaves();

        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);

        t3.left = t9;
        t3.right = t20;
        t20.left = t15;
        t20.right = t7;

        int sum = sol.sumOfLeftLeaves(t3);
    }
}
