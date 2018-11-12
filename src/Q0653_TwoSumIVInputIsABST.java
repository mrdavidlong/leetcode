import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 *
 *
 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

 Example 1:

 Input:
     5
    / \
   3   6
  / \   \
 2   4   7

 Target = 9

 Output: True
 Example 2:

 Input:
     5
    / \
   3   6
  / \   \
 2   4   7

 Target = 28

 Output: False
 */
public class Q0653_TwoSumIVInputIsABST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            int sum = nums.get(i) + nums.get(j);
            if (sum == k) return true;
            if (sum < k) i++;
            else j--;
        }
        return false;
    }

    private void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }

    public static void main(String[] args) {
        Q0653_TwoSumIVInputIsABST sol = new Q0653_TwoSumIVInputIsABST();

        TreeNode t5 = new TreeNode(5);
        TreeNode t3 = new TreeNode(3);
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t7 = new TreeNode(7);

        t5.left = t3;
        t5.right = t6;
        t3.left = t2;
        t3.right = t4;
        t6.right = t7;

        boolean hasSum = sol.findTarget(t5, 9);

        TreeNode s5 = new TreeNode(5);
        TreeNode s3 = new TreeNode(3);
        TreeNode s6 = new TreeNode(6);
        TreeNode s2 = new TreeNode(2);
        TreeNode s4 = new TreeNode(4);
        TreeNode s7 = new TreeNode(7);
        s5.left = s3;
        s5.right = s6;
        s3.left = s2;
        s3.right = s4;
        s6.right = s7;

        boolean hasSum2 = sol.findTarget(s5, 28);
        
    }
}
