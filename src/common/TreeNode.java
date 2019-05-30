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

	public void addNodeRec(int val) {
		if (val <= this.val) {
			if (this.left != null) {
				this.left.addNodeRec(val);
			} else {
				this.left = new TreeNode(val);
			}
		} else {
			if (this.right != null) {
				this.right.addNodeRec(val);
			} else {
				this.right = new TreeNode(val);
			}
		}
	}

	public void addNode(int val) {
		TreeNode curNode = this;
		while (true) {
			if (val <= curNode.val) {
				if (curNode.left != null) {
					curNode = curNode.left;
				} else {
					curNode.left = new TreeNode(val);
					break;
				}
			} else {
				if (curNode.right != null) {
					curNode = curNode.right;
				} else {
					curNode.right = new TreeNode(val);
					break;
				}
			}
		}
	}

	public TreeNode findNode(int val) {
		TreeNode curNode = this;
		while (true) {
			if (val < curNode.val) {
				if (curNode.left != null) {
					curNode = curNode.left;
				} else {
					return null;
				}
			} else if (val > curNode.val) {
				if (curNode.right != null) {
					curNode = curNode.right;
				} else {
					return null;
				}
			} else {
				return curNode;
			}
		}
	}

	public static void main(String[] args) {
		TreeNode t10 = new TreeNode(10);
		t10.addNode(5);
		t10.addNode(15);
		t10.addNode(1);
		t10.addNode(7);
		t10.addNode(12);
		t10.addNode(17);
		t10.printTreeInOrder();

		TreeNode t17 = t10.findNode(17);
		TreeNode t19 = t10.findNode(19);
	}
}
