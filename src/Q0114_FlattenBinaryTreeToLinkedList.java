import common.TreeNode;

public class Q0114_FlattenBinaryTreeToLinkedList {
//    private TreeNode prev = null;
//
//    public void flatten(TreeNode root) {
//        if (root == null)
//            return;
//        flatten(root.right);
//        flatten(root.left);
//        root.right = prev;
//        root.left = null;
//        prev = root;
//    }

    public void flatten(TreeNode root) {
        flatten(root,null);
    }

    private TreeNode flatten(TreeNode root, TreeNode pre) {
        if (root == null) return pre;
        pre = flatten(root.right,pre);
        pre = flatten(root.left,pre);
        root.right = pre;
        root.left = null;
        pre = root;
        return pre;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.right = n6;

        n1.printTreeInOrder();

        Q0114_FlattenBinaryTreeToLinkedList sol = new Q0114_FlattenBinaryTreeToLinkedList();
        sol.flatten(n1);

        n1.printTreeInOrder();
    }
}
