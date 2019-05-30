import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/n-queens/description/
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */

public class Q0051_NQueensBestByDavid {

    // Inspired by  https://leetcode.com/problems/n-queens/discuss/19805/My-easy-understanding-Java-Solution and https://leetcode.com/problems/n-queens-ii/discuss/20058/Accepted-Java-Solution
    private final Set<Integer> occupiedCols = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag1s = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag2s = new HashSet<Integer>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';

        List<List<String>> result = new ArrayList<>();
        dfs(board, 0 /* rowIndex */, result);
        return result;
    }

    private void dfs(char[][] board, int rowIndex, List<List<String>> result) {
        if (rowIndex == board.length) {
            result.add(construct(board));
            return;
        }

        for (int colIndex = 0; colIndex < board[0].length; colIndex++) {
            if (validate(rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                occupiedCols.add(colIndex);
                occupiedDiag1s.add(diag1(rowIndex, colIndex));
                occupiedDiag2s.add(diag2(rowIndex, colIndex));
                dfs(board, rowIndex + 1, result);
                board[rowIndex][colIndex] = '.';
                occupiedCols.remove(colIndex);
                occupiedDiag1s.remove(diag1(rowIndex, colIndex));
                occupiedDiag2s.remove(diag2(rowIndex, colIndex));
            }
        }
    }

    private int diag1(int r, int c) {
        return r - c;
    }

    private int diag2(int r, int c) {
        return r + c;
    }

    private boolean validate(int r, int c) {
        if (occupiedCols.contains(c)
            || occupiedDiag1s.contains(diag1(r, c))
            || occupiedDiag2s.contains(diag2(r, c))) return false;

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
        Q0051_NQueensBestByDavid sol = new Q0051_NQueensBestByDavid();
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
