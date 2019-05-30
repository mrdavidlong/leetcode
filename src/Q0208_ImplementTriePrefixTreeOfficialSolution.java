import java.util.HashMap;
import java.util.Map;

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
public class Q0208_ImplementTriePrefixTreeOfficialSolution {
    static class Trie {
        class TrieNode {
            public boolean isWord;
            public Map<Character, TrieNode> childrenMap = new HashMap<>();
        }

        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.childrenMap.get(c) == null){
                    // insert a new node if the path does not exist
                    cur.childrenMap.put(c, new TrieNode());
                }
                cur = cur.childrenMap.get(c);
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(cur.childrenMap.get(c) == null) {
                    return false;
                }
                cur = cur.childrenMap.get(c);
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for(int i = 0;i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(cur.childrenMap.get(c) == null) {
                    return false;
                }
                cur = cur.childrenMap.get(c);
            }
            return true;
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
