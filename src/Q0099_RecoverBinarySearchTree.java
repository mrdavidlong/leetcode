import common.Tree;
import common.TreeNode;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Example 1:

 Input: [1,3,null,null,2]

   1
  /
 3
  \
  2

 Output: [3,1,null,null,2]

    3
   /
  1
   \
    2
 Example 2:

 Input: [3,1,4,null,null,2]

     3
    / \
   1   4
  /
 2

 Output: [2,1,4,null,null,3]

     2
    / \
   1   4
  /
 3

 */
public class Q0099_RecoverBinarySearchTree {

    // https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal

    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        // In order traversal to find the two elements
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);

        // Start of "do some business",
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        // End of "do some business"

        traverse(root.right);
    }

    public static void main(String[] args) {
        Q0099_RecoverBinarySearchTree sol = new Q0099_RecoverBinarySearchTree();

//        TreeNode t1 = new TreeNode(1);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//
//        t3.left = t1;
//        t3.right = t4;
//        t1.left = t2;
//
//        sol.recoverTree(t1);


        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s3 = new TreeNode(3);
        s1.left = s3;
        s3.right = s2;

        sol.recoverTree(s1);

        int i =  0;
    }
}
