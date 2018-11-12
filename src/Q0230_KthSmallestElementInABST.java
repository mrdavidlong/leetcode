import common.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 *
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Example 1:

 Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
  2
 Output: 1
 Example 2:

 Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
 Output: 3
 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#bst
 */
public class Q0230_KthSmallestElementInABST {

    class Counter {
        public int cnt = 0;
        public int val = 0;
        public Counter(int ctn, int val) {
            this.cnt = ctn;
            this.val = val;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        Counter counter = new Counter(0, 0);
        inOrder(root, k, counter);
        return counter.val;
    }

    private void inOrder(TreeNode node, int k, Counter counter) {
        if (node == null) return;
        inOrder(node.left, k, counter);
        counter.cnt++;
        if (counter.cnt == k) {
            counter.val = node.val;
            return;
        }
        inOrder(node.right, k, counter);
    }

    public static void main(String[] args) {
        Q0230_KthSmallestElementInABST sol = new Q0230_KthSmallestElementInABST();
        TreeNode t3 = new TreeNode(3);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        t3.left = t1;
        t3.right = t4;
        t1.right = t2;

        int smallest1st = sol.kthSmallest(t3, 1);


        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s3 = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s6 = new TreeNode(6);
        s5.left = s3;
        s5.right = s6;
        s3.left = s2;
        s3.right = s4;
        s2.left = s1;

        int smallest3rd = sol.kthSmallest(s5, 3);

    }
}
