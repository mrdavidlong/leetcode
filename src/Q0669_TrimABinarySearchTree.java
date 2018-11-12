import common.Tree;
import common.TreeNode;

/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 *
 *
 Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

 Example 1:

 Input:
   1
  / \
 0   2

 L = 1
 R = 2

 Output:
 1
  \
  2
 Example 2:

 Input:
   3
  / \
 0   4
  \
   2
  /
 1

 L = 1
 R = 3

 Output:
     3
    /
   2
  /
 1
 */
public class Q0669_TrimABinarySearchTree {

//    public TreeNode trimBST(TreeNode root, int L, int R) {
//        if (root.val >= L && root.val <= R) {
//            root.left = (root.left != null && root.left.val < L) ? null : trimBST(root.left, L, R);
//            root.right = (root.right != null && root.right.val > R) ? null : trimBST(root.right, L, R);
//        } else if (root.val < L && root.val < R) {
//            root = trimBST(root.left, L, R);
//        } else if (root.val > L && root.val > R) {
//            root = trimBST(root.right, L, R);
//        }
//
//        return root;
//    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val > R) return trimBST(root.left, L, R);
        if (root.val < L) return trimBST(root.right, L, R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    public static void main(String[] args) {
        Q0669_TrimABinarySearchTree sol = new Q0669_TrimABinarySearchTree();

        TreeNode t1 = new TreeNode(1);
        TreeNode t0 = new TreeNode(0);
        TreeNode t2 = new TreeNode(2);

        t1.left = t0;
        t1.right = t2;

        TreeNode trimmedRoot = sol.trimBST(t1, 1, 2);

        TreeNode s3 = new TreeNode(3);
        TreeNode s0 = new TreeNode(0);
        TreeNode s4 = new TreeNode(4);
        TreeNode s2 = new TreeNode(2);
        TreeNode s1 = new TreeNode(1);

        s3.left = s0;
        s3.right = s4;
        s0.right = s2;
        s2.left = s1;

        TreeNode trimmedRoot2 = sol.trimBST(s3, 1, 3);

    }
}
