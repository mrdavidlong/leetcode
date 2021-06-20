import java.util.*;

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
//    public List<String> findWords(char[][] board, String[] words) {
//        Set<String> res = new HashSet<>();
//        TrieNode root = buildTrie(words);
//        boolean[][] visited = new boolean[board.length][board[0].length];
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                dfs(board, i, j, root, visited, res);
//            }
//        }
//        return new ArrayList<>(res);
//    }
//
//    public void dfs(char[][] board, int i, int j, TrieNode p, boolean[][] visited, Set<String> res) {
//        char c = board[i][j];
//
//        if (visited[i][j] || p.children[c - 'a'] == null) return;
//
//        p = p.children[c - 'a'];
//        if (p.word != null) {
//            res.add(p.word);
//        }
//
//        visited[i][j] = true;
//        if (i > 0) dfs(board, i - 1, j ,p, visited, res);
//        if (j > 0) dfs(board, i, j - 1, p, visited, res);
//        if (i < board.length - 1) dfs(board, i + 1, j, p, visited, res);
//        if (j < board[0].length - 1) dfs(board, i, j + 1, p, visited, res);
//        visited[i][j] = false;
//    }
//
//    public TrieNode buildTrie(String[] words) {
//        TrieNode root = new TrieNode();
//        for (String w : words) {
//            TrieNode p = root;
//            for (char c : w.toCharArray()) {
//                int i = c - 'a';
//                if (p.children[i] == null) p.children[i] = new TrieNode();
//                p = p.children[i];
//            }
//            p.word = w;
//        }
//        return root;
//    }
//
//    class TrieNode {
//        TrieNode[] children = new TrieNode[26];
//        String word;
//    }
//
//
    //https://leetcode.com/problems/word-search-ii/solution/
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;
        public TrieNode() {}
    }

    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
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
