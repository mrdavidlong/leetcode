import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 *
 *
 Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.


 For example:
 Given BST [1,null,2,2],

 1
  \
  2
  /
 2


 return [2].

 Note: If a tree has more than one mode, you can return them in any order.

 Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#bst
 */
public class Q0501_FindModeInBinarySearchTree {
    private int curCnt = 1;
    private int maxCnt = 1;
    private TreeNode preNode = null;

    public int[] findMode(TreeNode root) {
        List<Integer> maxCntNums = new ArrayList<>();
        inOrder(root, maxCntNums);
        int[] ret = new int[maxCntNums.size()];
        int idx = 0;
        for (int num : maxCntNums) {
            ret[idx++] = num;
        }
        return ret;
    }

    private void inOrder(TreeNode node, List<Integer> nums) {
        if (node == null) return;
        inOrder(node.left, nums);
        if (preNode != null) {
            if (preNode.val == node.val) curCnt++;
            else curCnt = 1;
        }
        if (curCnt > maxCnt) {
            maxCnt = curCnt;
            nums.clear();
            nums.add(node.val);
        } else if (curCnt == maxCnt) {
            nums.add(node.val);
        }
        preNode = node;
        inOrder(node.right, nums);
    }

    public static void main(String[] args) {
        Q0501_FindModeInBinarySearchTree sol = new Q0501_FindModeInBinarySearchTree();

        TreeNode t1 = new TreeNode(1);
        TreeNode t1b = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t2b = new TreeNode(2);

        t1.right = t2;
        t1.left = t1b;
        t2.left = t2b;

        int[] mode = sol.findMode(t1);
    }

}
