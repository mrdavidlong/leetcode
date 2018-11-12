import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
  \
   2
  /
 3

 Output: [3,2,1]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Q0145_BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) return order;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            order.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        Collections.reverse(order);
        return order;
    }

    public static void main(String[] args) {

        Q0145_BinaryTreePostorderTraversal sol = new Q0145_BinaryTreePostorderTraversal();

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left = t3;

        List<Integer> postOrder = sol.postorderTraversal(t1);
    }

}
