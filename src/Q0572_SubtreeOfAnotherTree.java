import common.Tree;
import common.TreeNode;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/description/
 *
 Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:

     3
    / \
   4   5
  / \
 1   2
 Given tree t:
   4
  / \
 1   2
 Return true, because t has the same structure and node values with a subtree of s.

 Example 2:
 Given tree s:

       3
      / \
     4   5
    / \
   1   2
  /
 0
 Given tree t:
   4
  / \
 1    2
 Return false.

 */
public class Q0572_SubtreeOfAnotherTree {
    // Wrong:
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        if (s == null && t == null) return true;
//        if (s == null || t == null) return false;
//
//        boolean isMatch = false;
//        if (t.val == s.val) {
//            isMatch = isSubtree(s.left, t.left) && isSubtree(s.right, t.right);
//        }
//        return isMatch || isSubtree(s.left, t) || isSubtree(s.right, t);
//    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isSubtreeWithRoot(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSubtreeWithRoot(TreeNode s, TreeNode t) {
        if (t == null && s == null) return true;
        if (t == null || s == null) return false;
        if (t.val != s.val) return false;
        return isSubtreeWithRoot(s.left, t.left) && isSubtreeWithRoot(s.right, t.right);
    }

    public static void main(String[] args) {
        Q0572_SubtreeOfAnotherTree sol = new Q0572_SubtreeOfAnotherTree();

        TreeNode s3 = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        s3.left = s4;
        s3.right = s5;
        s4.left = s1;
        s4.right = s2;

        TreeNode t4 = new TreeNode(4);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        t4.left = t1;
        t4.right = t2;

//        t3.printTreeInOrder();
//        s4.printTreeInOrder();
//        Tree t = new Tree(t3);
//        t.printTopView();
//
//        Tree s = new Tree(s4);
//        s.printTopView();

        boolean isST = sol.isSubtree(s3, t4);


        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a0 = new TreeNode(0);
        a3.left = a4;
        a3.right = a5;
        a4.left = a1;
        a4.right = a2;
        a1.left = a0;

        TreeNode b4 = new TreeNode(4);
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        b4.left = b1;
        b4.right = b2;

        boolean isST2 = sol.isSubtree(a3, b4);

        TreeNode c1 = new TreeNode(1);
        TreeNode c1b = new TreeNode(1);
        c1.left = c1b;

        TreeNode d1 = new TreeNode(1);

        boolean isST3 = sol.isSubtree(c1, c1b);


        TreeNode e3 = new TreeNode(3);
        TreeNode e4 = new TreeNode(4);
        TreeNode e5 = new TreeNode(5);
        TreeNode e1 = new TreeNode(1);
        TreeNode e2 = new TreeNode(2);
        e3.left = e4;
        e3.right = e5;
        e4.left = e1;
        e5.left = e2;

        TreeNode f3 = new TreeNode(3);
        TreeNode f1 = new TreeNode(1);
        TreeNode f2 = new TreeNode(2);
        f3.left = f1;
        f3.right = f2;

        boolean isST4 = sol.isSubtree(e3, f3);
    }
}
