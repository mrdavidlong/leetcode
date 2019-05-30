import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/n-queens/description/
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */

public class Q0051_NQueensFromLeetcodeSol {

    // https://leetcode.com/problems/n-queens/discuss/19805/My-easy-understanding-Java-Solution
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';

        List<List<String>> result = new ArrayList<>();
        dfs(board, 0 /* colIndex */, result);
        return result;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> result) {
        if (colIndex == board.length) {
            result.add(construct(board));
            return;
        }

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            if (validate(board, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                dfs(board, colIndex + 1, result);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int r, int c) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'Q' && (r + j == c + i || r + c == i + j || r == i)) // upper left to lower right, lower left to upper right, same row
                    return false;
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    public static void main(String[] args) {
        Q0051_NQueensFromLeetcodeSol sol = new Q0051_NQueensFromLeetcodeSol();
        List<List<String>> result = sol.solveNQueens(4);
        for (List<String> list : result) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
            System.out.println();
        }
    }

}
