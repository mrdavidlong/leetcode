import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.



Example 1:

       1
      / \
     2   3
      \   \
       5    4

Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Example 2:

Input: root = [1,null,3]
Output: [1,3]
Example 3:

Input: root = []
Output: []

 */
public class Q0199_Binary_Tree_Right_Side_View {

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {{ offer(root); }};
        ArrayDeque<TreeNode> currLevel = new ArrayDeque();
        List<Integer> rightside = new ArrayList();

        TreeNode node = null;
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel.clone();
            nextLevel.clear();

            while (!currLevel.isEmpty()) {
                node = currLevel.poll();

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null)
                    nextLevel.offer(node.left);
                if (node.right != null)
                    nextLevel.offer(node.right);
            }

            // The current level is finished.
            // Its last element is the rightmost one.
            if (currLevel.isEmpty())
                rightside.add(node.val);
        }
        return rightside;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n4.left = n7;
        n6.right = n8;

        List<Integer> ans = rightSideView(n1);
        int i = 0;
    }

}
