import common.TreeNode;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 *
 *
 Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

 You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

 Example 1:

 Input:
 Tree 1                     Tree 2
 1                         2
 / \                       / \
 3   2                     1   3
 /                           \   \
 5                             4   7
 Output:
 Merged tree:
 3
 / \
 4   5
 / \   \
 5   4   7
 Note: The merging process must start from the root nodes of both trees.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#树
 */
public class Q0617_MergeTwoBinaryTrees {


    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;

        if (t1 == null) return t2;
        if (t2 == null) return t1;

        TreeNode n = new TreeNode(t1.val + t2.val);
        n.left = mergeTrees(t1.left, t2.left);
        n.right = mergeTrees(t1.right, t2.right);
        return n;
    }

    public static void main(String[] args) {
        TreeNode t1_1 = new TreeNode(1);
        TreeNode t1_3 = new TreeNode(3);
        TreeNode t1_2 = new TreeNode(2);
        TreeNode t1_5 = new TreeNode(5);

        TreeNode t2_2 = new TreeNode(2);
        TreeNode t2_1 = new TreeNode(1);
        TreeNode t2_3 = new TreeNode(3);
        TreeNode t2_4 = new TreeNode(4);
        TreeNode t2_7 = new TreeNode(7);

        t1_1.left = t1_3;
        t1_1.right = t1_2;
        t1_3.left = t1_5;
        t1_1.printTreeInOrder();

        t2_2.left = t2_1;
        t2_2.right = t2_3;
        t2_1.right = t2_4;
        t2_3.right = t2_7;
        t2_2.printTreeInOrder();

        Q0617_MergeTwoBinaryTrees sol = new Q0617_MergeTwoBinaryTrees();
        TreeNode newT = sol.mergeTrees(t1_1, t2_2);
        newT.printTreeInOrder();

    }
}
