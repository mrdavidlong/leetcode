import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
 *
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 Example 1:

 Input:
     3
    / \
   9  20
  /  \
 15   7
 Output: [3, 14.5, 11]
 Explanation:
 The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 Note:

 The range of node's value is in the range of 32-bit signed integer.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#层次遍历
 */
public class Q0637_AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            double sum = 0;
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ret.add(sum / cnt);
        }
        return ret;
    }

    public static void main(String[] args) {
        Q0637_AverageOfLevelsInBinaryTree sol = new Q0637_AverageOfLevelsInBinaryTree();

        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);

        t3.left = t9;
        t3.right = t20;
        t9.left = t15;
        t9.right = t7;

        List<Double> averages = sol.averageOfLevels(t3);

    }
}
