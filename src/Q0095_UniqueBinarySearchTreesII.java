import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

 Example:

 Input: 3
 Output:
 [
 [1,null,3,2],
 [3,2,null,1],
 [3,1,null,null,2],
 [2,1,3],
 [1,null,2,null,3]
 ]
 Explanation:
 The above output corresponds to the 5 unique BST's shown below:

 1         3     3      2      1
  \       /     /      / \      \
  3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 */
//public class Q0095_UniqueBinarySearchTreesII {
//}
/*
I start by noting that 1..n is the in-order traversal for any BST with nodes 1 to n. So if I pick i-th node as my root, the left subtree will contain elements 1 to (i-1), and the right subtree will contain elements (i+1) to n. I use recursive calls to get back all possible trees for left and right subtrees and combine them in all possible ways with the root.
*/
public class Q0095_UniqueBinarySearchTreesII {
    // https://www.youtube.com/watch?v=GZ0qvkTAjmw
    // https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/31494/A-simple-recursive-solution
//    public List<TreeNode> generateTrees(int n) {
//        if (n <= 0) return new ArrayList<>();
//        return genTrees(1,n);
//    }
//
//    public List<TreeNode> genTrees(int start, int end) {
//        List<TreeNode> list = new ArrayList<>();
//
//        if (start > end) {
//            list.add(null);
//            return list;
//        }
//
//        if (start == end) {
//            list.add(new TreeNode(start));
//            return list;
//        }
//
//        List<TreeNode> leftTreeList, rightTreeList;
//        for (int i = start; i <= end; i++) {
//            leftTreeList = genTrees(start, i-1);
//            rightTreeList = genTrees(i+1, end);
//
//            for (TreeNode left: leftTreeList) {
//                for (TreeNode right: rightTreeList) {
//                    TreeNode root = new TreeNode(i);
//                    root.left = left;
//                    root.right = right;
//                    list.add(root);
//                }
//            }
//        }
//
//        return list;
//    }

    // https://leetcode.com/problems/unique-binary-search-trees-ii/solution/
    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is chosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is chosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generate_trees(1, n);
    }

    public static void main(String[] args) {
        Q0095_UniqueBinarySearchTreesII sol = new Q0095_UniqueBinarySearchTreesII();
        //List<TreeNode> list = sol.generateTrees(5);
        List<TreeNode> list0 = sol.generateTrees(0);
        List<TreeNode> list1 = sol.generateTrees(1);
        List<TreeNode> list2 = sol.generateTrees(2);
        List<TreeNode> list3 = sol.generateTrees(3);
    }
}
