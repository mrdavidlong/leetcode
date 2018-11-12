/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 *
 * Implement a trie with insert, search, and startsWith methods.

 Example:

 Trie trie = new Trie();

 trie.insert("apple");
 trie.search("apple");   // returns true
 trie.search("app");     // returns false
 trie.startsWith("app"); // returns true
 trie.insert("app");
 trie.search("app");     // returns true
 Note:

 You may assume that all inputs are consist of lowercase letters a-z.
 All inputs are guaranteed to be non-empty strings.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#trie
 */
public class Q0208_ImplementTriePrefixTree {
    static class Trie {

        private class Node {
            Node[] children = new Node[26];
            boolean isLeaf;
        }

        private Node root = new Node();

        public Trie() {
        }

        public void insert(String word) {
            insert(word, root);
        }

        private void insert(String word, Node node) {
            if (node == null) return;
            if (word.length() == 0) {
                node.isLeaf = true;
                return;
            }
            int index = indexForChar(word.charAt(0));
            if (node.children[index] == null) {
                node.children[index] = new Node();
            }
            insert(word.substring(1), node.children[index]);
        }

        public boolean search(String word) {
            return search(word, root);
        }

        private boolean search(String word, Node node) {
            if (node == null) return false;
            if (word.length() == 0) return node.isLeaf;
            int index = indexForChar(word.charAt(0));
            return search(word.substring(1), node.children[index]);
        }

        public boolean startsWith(String prefix) {
            return startWith(prefix, root);
        }

        private boolean startWith(String prefix, Node node) {
            if (node == null) return false;
            if (prefix.length() == 0) return true;
            int index = indexForChar(prefix.charAt(0));
            return startWith(prefix.substring(1), node.children[index]);
        }

        private int indexForChar(char c) {
            return c - 'a';
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        boolean search1 = trie.search("apple");   // returns true
        boolean search2 = trie.search("app");     // returns false
        boolean startsWith = trie.startsWith("app"); // returns true
        trie.insert("app");
        boolean search3 = trie.search("app");     // returns true
    }
}
