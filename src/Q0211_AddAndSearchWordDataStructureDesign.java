/*

https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

https://leetcode.com/problems/add-and-search-word-data-structure-design/discuss/59554/My-simple-and-clean-Java-code
 */

public class Q0211_AddAndSearchWordDataStructureDesign {

    public static class WordDictionary {
        public class TrieNode {
            public TrieNode[] children = new TrieNode[26];
            public String item = "";
        }

        private TrieNode root = new TrieNode();

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.item = word;
        }

        public boolean search(String word) {
            return match(word.toCharArray(), 0, root);
        }

        private boolean match(char[] chs, int k, TrieNode node) {
            if (k == chs.length) return !node.item.equals("");
            if (chs[k] != '.') {
                return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
            } else {
                for (int i = 0; i < node.children.length; i++) {
                    if (node.children[i] != null) {
                        if (match(chs, k + 1, node.children[i])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Q0211_AddAndSearchWordDataStructureDesign sol = new Q0211_AddAndSearchWordDataStructureDesign();
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        boolean found = dict.search("pad");
        boolean found2 = dict.search("bad");
        boolean found3 = dict.search(".ad");
        boolean found4 = dict.search("b..");

    }
}
