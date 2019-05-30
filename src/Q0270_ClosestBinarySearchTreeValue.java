import common.TreeNode;

/*
https://leetcode.com/problems/closest-binary-search-tree-value/
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */
public class Q0270_ClosestBinarySearchTreeValue {

    // https://leetcode.com/problems/closest-binary-search-tree-value/discuss/70331/Clean-and-concise-java-solution
    public int closestValue(TreeNode root, double target) {
        int ret = root.val;
        while (root != null){
            if (Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            }
            root = root.val > target ? root.left: root.right;
        }
        return ret;
    }

    public static void main(String[] args) {
        Q0270_ClosestBinarySearchTreeValue sol = new Q0270_ClosestBinarySearchTreeValue();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n4.left = n2;
        n4.right = n5;
        n2.left = n1;
        n2.right = n3;

        int value = sol.closestValue(n4, 3.714286);
        int value2 = sol.closestValue(n4, 3.1);

    }

}
