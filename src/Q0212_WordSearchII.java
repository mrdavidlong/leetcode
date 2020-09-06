import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/word-search-ii/
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input:
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
Note:
You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Q0212_WordSearchII {
    // modified from https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        TrieNode root = buildTrie(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, visited, res);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, boolean[][] visited, Set<String> res) {
        char c = board[i][j];

        if (visited[i][j] || p.children[c - 'a'] == null) return;

        p = p.children[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
        }

        visited[i][j] = true;
        if (i > 0) dfs(board, i - 1, j ,p, visited, res);
        if (j > 0) dfs(board, i, j - 1, p, visited, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, visited, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, visited, res);
        visited[i][j] = false;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) p.children[i] = new TrieNode();
                p = p.children[i];
            }
            p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public static void main(String[] args) {

        char[][] board =
            {
              {'o','a','a','n'},
              {'h','t','a','e'},
              {'i','h','k','r'},
              {'i','f','l','v'}
            };
        String[] words = {"oath","pea","eat","rain"};

        Q0212_WordSearchII sol = new Q0212_WordSearchII();
        List<String> foundWords = sol.findWords(board, words);
    }
}
