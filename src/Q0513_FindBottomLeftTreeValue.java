import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/description/
 *
 *
 Given a binary tree, find the leftmost value in the last row of the tree.

 Example 1:

 Input:

   2
  / \
 1   3

 Output:
 1
 Example 2:

 Input:

       1
      / \
     2   3
    /   / \
   4   5   6
     /
    7

 Output:
 7
 Note: You may assume the tree (i.e., the given root node) is not NULL.
 */
public class Q0513_FindBottomLeftTreeValue {

//    private int maxDepth(TreeNode root) {
//        if(root==null)
//            return 0;
//
//        int leftDepth = maxDepth(root.left);
//        int rightDepth = maxDepth(root.right);
//
//        int bigger = Math.max(leftDepth, rightDepth);
//
//        return bigger+1;
//    }
//
//    public int findBottomLeftValue(TreeNode root) {
//        int maxDepth = maxDepth(root);
//        return findBottomLeftValueCore(root, maxDepth, 1);
//    }
//
//    private int findBottomLeftValueCore(TreeNode root, int maxDepth, int curDepth) {
//        if (root == null) return -1;
//
//        int ret = findBottomLeftValueCore(root.left, maxDepth, curDepth + 1);
//        if (ret != -1) {
//            return ret;
//        }
//
//        if (curDepth == maxDepth) return root.val;
//
//        return findBottomLeftValueCore(root.right, maxDepth, curDepth + 1);
//    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.add(root.right);
            if (root.left != null) queue.add(root.left);
        }
        return root.val;
    }

    public static void main(String[] args) {
        Q0513_FindBottomLeftTreeValue sol = new Q0513_FindBottomLeftTreeValue();

        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);

        t2.left = t1;
        t2.right = t3;

        int bottomLeftValue = sol.findBottomLeftValue(t2);

        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s3 = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s6 = new TreeNode(6);
        TreeNode s7 = new TreeNode(7);

        s1.left = s2;
        s1.right = s3;
        s2.left = s4;
        s3.left = s5;
        s3.right = s6;
        s5.left = s7;

        int bottomLeftValue2 = sol.findBottomLeftValue(s1);


    }

}

