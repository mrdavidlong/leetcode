import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by davidlong on 7/4/18.
 */
//https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3.md#backtracking
public class Q0051_NQueensOK {

    public  List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] nQueens = new char[n][n];
        for (int i =  0; i < n; i++) {
            Arrays.fill(nQueens[i], '.');
        }
        boolean[] colUsed = new boolean[n];
        boolean[] diagonals45Used = new boolean[2 * n -  1];
        boolean[] diagonals135Used = new boolean[2 * n -  1];
        backtracking(n, 0, result, nQueens, colUsed, diagonals45Used, diagonals135Used);
        return result;
    }

    private void backtracking(int n, int row, List<List<String>> result, char[][] nQueens, boolean[] colUsed, boolean[] diagonals45Used, boolean[] diagonals135Used) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (char[] chars : nQueens) {
                list.add(new String(chars));
            }
            result.add(list);
            return;
        }

        for (int col =  0; col < n; col++) {
            int diagonals45Idx = row + col;
            int diagonals135Idx = n -  1  - (row - col);
            if (colUsed[col] || diagonals45Used[diagonals45Idx] || diagonals135Used[diagonals135Idx]) {
                continue;
            }

            nQueens[row][col] = 'Q';
            colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = true;

            backtracking(n, row + 1, result, nQueens, colUsed, diagonals45Used, diagonals135Used);

            colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = false;
            nQueens[row][col] = '.';
        }
    }

    public static void main(String[] args) {
        Q0051_NQueensOK sol = new Q0051_NQueensOK();
        List<List<String>> result = sol.solveNQueens(8);
        for (List<String> list : result) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
            System.out.println();
        }
    }

}
