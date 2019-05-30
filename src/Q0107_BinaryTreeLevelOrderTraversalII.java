import common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
  /  \
 15   7
 return its bottom-up level order traversal as:

 [
 [15,7],
 [9,20],
 [3]
 ]
 */
public class Q0107_BinaryTreeLevelOrderTraversalII {
    // https://leetcode.com/problems/binary-tree-level-order-traversal-ii/discuss/34981/My-DFS-and-BFS-java-solution
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new LinkedList<>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode curNode = queue.poll();
                subList.add(curNode.val);
                if (curNode.left != null) queue.offer(curNode.left);
                if (curNode.right != null) queue.offer(curNode.right);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }

    public static void main(String[] args) {
        Q0107_BinaryTreeLevelOrderTraversalII sol = new Q0107_BinaryTreeLevelOrderTraversalII();
        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);

        t3.left = t9;
        t3.right = t20;
        t9.left = t15;
        t20.right = t7;

        List<List<Integer>> resultList = sol.levelOrderBottom(t3);
    }
}
