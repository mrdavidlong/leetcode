/**
 https://leetcode.com/problems/word-search/description/
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 */
public class Q0079_WordSearch {
    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int rowLength;
    private int colLength;

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        rowLength = board.length;
        colLength = board[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];

        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if (existCore(0, r, c, visited, board, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean existCore(int curLen, int r, int c, boolean[][] visited, final char[][] board, final String word) {
        if (curLen == word.length()) {
            return true;
        }
        if (r < 0 || r >= rowLength || c < 0 || c >= colLength
                || board[r][c] != word.charAt(curLen) || visited[r][c]) {
            return false;
        }

        visited[r][c] = true;

        for (int[] d : direction) {
            if (existCore(curLen + 1, r + d[0], c + d[1], visited, board, word)) {
                return true;
            }
        }

        visited[r][c] = false;

        return false;
    }

//    board =
//            [
//            ['A','B','C','E'],
//            ['S','F','C','S'],
//            ['A','D','E','E']
//            ]
//
//    Given word = "ABCCED", return true.
//    Given word = "SEE", return true.
//    Given word = "ABCB", return false.
    public static void main(String[] args) {
        Q0079_WordSearch sol = new Q0079_WordSearch();

        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };

        String word1 = "ABCCED";
        boolean exist1 = sol.exist(board, word1);
        String word2 = "SEE";
        boolean exist2 = sol.exist(board, word2);
        String word3 = "ABCB";
        boolean exist3 = sol.exist(board, word3);
    }
}
