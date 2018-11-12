import common.TreeNode;

/**
 * https://leetcode.com/problems/same-tree/description/
 *
 * Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

 Example 1:

 Input:
  1         1
 / \       / \
 2   3     2   3

 [1,2,3],   [1,2,3]

 Output: true
 Example 2:

 Input:
   1         1
  /           \
 2             2

 [1,2],     [1,null,2]

 Output: false
 Example 3:

 Input:
   1         1
  / \       / \
 2   1     1   2

 [1,2,1],   [1,1,2]

 Output: false
 */
public class Q0100_SameTree {
    // https://leetcode.com/problems/same-tree/discuss/32687/Five-line-Java-solution-with-recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    public static void main(String[] args) {
        Q0100_SameTree sol = new Q0100_SameTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        boolean same = sol.isSameTree(n1, t1);
    }
}
