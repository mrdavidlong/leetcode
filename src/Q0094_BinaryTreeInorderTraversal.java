import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
  \
  2
  /
 3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Q0094_BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) return order;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // push all the left nodes into the stack, and then pop, so we can process the left node first
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            order.add(node.val);
            cur = node.right;
        }
        return order;
    }

    public List<Integer> inorderTraversalRec(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) return order;
        inorderTraversalRecCore(root, order);
        return order;
    }

    private void inorderTraversalRecCore(TreeNode node, List<Integer> order) {
        if (node != null) {
            inorderTraversalRecCore(node.left, order);
            order.add(node.val);
            inorderTraversalRecCore(node.right, order);
        }
    }

    public static void main(String[] args) {

        Q0094_BinaryTreeInorderTraversal sol = new Q0094_BinaryTreeInorderTraversal();

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left = t3;

        List<Integer> inOrderRec = sol.inorderTraversalRec(t1);
        List<Integer> inOrder = sol.inorderTraversal(t1);

    }
}
