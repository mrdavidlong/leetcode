import common.TreeNode;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/description/
 *
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

 Example:

 Input: The root of a Binary Search Tree like this:
     5
   /   \
 2     13

 Output: The root of a Greater Tree like this:
    18
  /   \
 20     13

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#bst

 */
public class Q0538_ConvertBSTToGreaterTree {

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traver(root);
        return root;
    }

    private void traver(TreeNode node) {
        if (node == null) return;
        traver(node.right);
        sum += node.val;
        node.val = sum;
        traver(node.left);
    }

    public static void main(String[] args) {
        Q0538_ConvertBSTToGreaterTree sol = new Q0538_ConvertBSTToGreaterTree();

        TreeNode t5 = new TreeNode(5);
        TreeNode t2 = new TreeNode(2);
        TreeNode t13 = new TreeNode(13);
        t5.left = t2;
        t5.right = t13;

        TreeNode result = sol.convertBST(t5);
    }

}
