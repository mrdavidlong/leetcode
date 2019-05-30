import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1
 Return:

 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 */
public class Q0113_PathSumII {

    // https://leetcode.com/problems/path-sum-ii/discuss/36673/Simple-DFS-Java-Solution
//     public void pathSumInner(TreeNode root, int sum, Stack<Integer> path, List<List<Integer>> resultList) {
//         path.push(root.val);
//         if(root.left == null && root.right == null)
//             if(sum == root.val) resultList.add(new ArrayList<Integer>(path));
//         if(root.left!=null) pathSumInner(root.left, sum-root.val, path, resultList);
//         if(root.right!=null)pathSumInner(root.right, sum-root.val, path, resultList);
//         path.pop();
//     }
//
//     public List<List<Integer>> pathSum(TreeNode root, int sum) {
//         List<List<Integer>> resultList = new ArrayList<List<Integer>>();
//         if(root==null) return resultList;
//         Stack<Integer> path = new Stack<Integer>();
//         pathSumInner(root, sum, path, resultList);
//         return resultList;
//     }

     // by david
    public void pathSumInner(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> resultList) {
        if (root != null) {
            path.addLast(root.val);
            if (root.left == null && root.right == null && sum == root.val) resultList.add(new ArrayList<>(path));
            if (root.left != null) pathSumInner(root.left, sum - root.val, path, resultList);
            if (root.right != null) pathSumInner(root.right, sum - root.val, path, resultList);
            path.removeLast();
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root==null) return resultList;
        LinkedList<Integer> path = new LinkedList<>();
        pathSumInner(root, sum, path, resultList);
        return resultList;
    }

    public static void main(String[] args) {
        Q0113_PathSumII sol = new Q0113_PathSumII();

        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        TreeNode t8 = new TreeNode(8);
        TreeNode t11 = new TreeNode(11);
        TreeNode t13 = new TreeNode(13);
        TreeNode t4b = new TreeNode(4);
        TreeNode t7 = new TreeNode(7);
        TreeNode t2 = new TreeNode(2);
        TreeNode t5b = new TreeNode(5);
        TreeNode t1 = new TreeNode(1);

        t5.left = t4;
        t5.right = t8;
        t4.left = t11;
        t11.left = t7;
        t11.right = t2;
        t8.left = t13;
        t8.right = t4b;
        t4b.left = t5b;
        t4b.right = t1;

        t5.printTreeInOrder();

        List<List<Integer>> resultList = sol.pathSum(t5, 22);
    }
}
