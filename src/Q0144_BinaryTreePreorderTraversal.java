import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
  \
   2
  /
 3

 Output: [1,2,3]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Q0144_BinaryTreePreorderTraversal {
//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> preOrder = new ArrayList<>();
//        preorderTraversalCore(root, preOrder);
//        return preOrder;
//    }
//
//    private void preorderTraversalCore(TreeNode root, List<Integer> preOrder) {
//        if (root != null) {
//            preOrder.add(root.val);
//            preorderTraversalCore(root.left, preOrder);
//            preorderTraversalCore(root.right, preOrder);
//        }
//    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ret.add(node.val);
            if (node.right != null) stack.push(node.right);  // 先右后左，保证左子树先遍历
            if (node.left != null) stack.push(node.left);
        }
        return ret;
    }

    public static void main(String[] args) {
        Q0144_BinaryTreePreorderTraversal sol = new Q0144_BinaryTreePreorderTraversal();

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left = t3;

        List<Integer> preOrder =  sol.preorderTraversal(t1);

    }
}
