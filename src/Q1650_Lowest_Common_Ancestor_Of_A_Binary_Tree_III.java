public class Q1650_Lowest_Common_Ancestor_Of_A_Binary_Tree_III {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int x) {
            this.val = x;
        }
    };

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            // switch head is null
            a = (a == null) ? q : a.parent;
            b = (b == null) ? p : b.parent;
        }
        return a;
    }

    public static void main(String[] args) {
        Q1650_Lowest_Common_Ancestor_Of_A_Binary_Tree_III sol = new Q1650_Lowest_Common_Ancestor_Of_A_Binary_Tree_III();
        Node n2 = new Node(2);
        Node n1 = new Node(1);
        n1.parent = n2;
        Node n3 = new Node(3);
        n3.parent = n2;
        Node n4 = new Node(4);
        n4.parent = n3;

        Node lca = sol.lowestCommonAncestor(n2, n3);
        Node lca2 = sol.lowestCommonAncestor(n1, n3);

        Node a = new Node(1);
        Node b = new Node(2);
        b.parent = a;

        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        d.parent = c;
        e.parent = d;
        Node lca3 = sol.lowestCommonAncestor(b, e);

    }
}
