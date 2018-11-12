import common.TreeNode;

/**
 * https://leetcode.com/problems/longest-univalue-path/description/
 *
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

 Note: The length of path between two nodes is represented by the number of edges between them.

 Example 1:

 Input:

     5
    / \
   4   5
  / \   \
 1   1   5
 Output:

 2
 Example 2:

 Input:

     1
    / \
   4   5
  / \   \
 4   4   5
 Output:

 2
 Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class Q0687_LongestUnivaluePath {
    private int path = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return path;
    }

    private int dfs(TreeNode root){
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftPath = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int rightPath = root.right != null && root.right.val == root.val ? right + 1 : 0;
        path = Math.max(path, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }

    public static void main(String[] args) {
        Q0687_LongestUnivaluePath sol = new Q0687_LongestUnivaluePath();

        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(5);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;

        int path1 = sol.longestUnivaluePath(t1);

        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(4);
        TreeNode s3 = new TreeNode(5);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(4);
        TreeNode s6 = new TreeNode(5);

        s1.left = s2;
        s1.right = s3;
        s2.left = s4;
        s2.right = s5;
        s3.right = s6;

        int path2 = sol.longestUnivaluePath(s1);

    }
}
