import java.util.ArrayList;
import java.util.List;
import common.TreeNode;
/**
 * https://leetcode.com/problems/binary-tree-paths/description/
 Given a binary tree, return all root-to-leaf paths.

 Note: A leaf is a node with no children.

 Example:

 Input:

 1
 /   \
 2     3
 \
 5

 Output: ["1->2->5", "1->3"]

 Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class Q0257_BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        binaryTreePathsCore(result, new ArrayList<>(), root);
        return result;
    }

    private void binaryTreePathsCore(List<String> result, List<Integer> tempList, TreeNode node) {
        if (node == null) {
            return;
        }
        tempList.add(node.val);
        if (isLeaf(node)) {
            result.add(buildPath(tempList));
        } else {
            binaryTreePathsCore(result, tempList, node.left);
            binaryTreePathsCore(result, tempList, node.right);
        }
        tempList.remove(tempList.size() - 1);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private String buildPath(List<Integer> values) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            str.append(values.get(i));
            if (i != values.size() - 1) {
                str.append("->");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {


//           1
//         /  \
//        2    3
//        \
//         5
//                ["1->2->5", "1->3"]

        Q0257_BinaryTreePaths sol = new Q0257_BinaryTreePaths();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n5;

        List<String> list = sol.binaryTreePaths(n1);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
