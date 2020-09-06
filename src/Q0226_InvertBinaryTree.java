import common.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/description/
 *
 *
 Invert a binary tree.

 Example:

 Input:

 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 Output:

 4
 /   \
 7     2
 / \   / \
 9   6 3   1

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#树
 */
public class Q0226_InvertBinaryTree {
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//
//        TreeNode tempNode = invertTree(root.left);
//        root.left = invertTree(root.right);
//        root.right = tempNode;
//        return root;
//    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;  // 后面的操作会改变 left 指针，因此先保存下来
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t9 = new TreeNode(9);

        t4.left = t2;
        t4.right = t7;

        t2.left = t1;
        t2.right = t3;

        t7.left = t6;
        t7.right = t9;

        t4.printTreeInOrder();
        Q0226_InvertBinaryTree sol = new Q0226_InvertBinaryTree();
        TreeNode root = sol.invertTree(t4);
        root.printTreeInOrder();



    }
}
