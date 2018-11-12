package common;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int val) {
		this.val = val;
	}

	public void printTreeInOrder() {
		if (left != null) {
			left.printTreeInOrder();
		}
		System.out.println(val);
		if (right != null) {
			right.printTreeInOrder();
		}
	}
}
