import common.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted array: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

       0
      / \
    -3   9
   /   /
 -10  5
 */
public class Q0108_ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int start, int end){
        if (start > end) return null;
        int mIdx = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mIdx]);
        root.left =  toBST(nums, start, mIdx - 1);
        root.right = toBST(nums, mIdx + 1, end);
        return root;
    }

    public static void main(String[] args) {
        Q0108_ConvertSortedArrayToBinarySearchTree sol = new Q0108_ConvertSortedArrayToBinarySearchTree();
        TreeNode bst = sol.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
    }
}
