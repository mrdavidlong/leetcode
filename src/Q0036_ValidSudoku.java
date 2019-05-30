import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/valid-sudoku/
 */
public class Q0036_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        // validate rows
        for (int r = 0; r < board.length; r++) {
            Set<Character> set = new HashSet<>();
            for (int c = 0; c < board[0].length; c++) {
                char ch = board[r][c];
                if (ch != '.') {
                    if (set.contains(ch)) return false;
                    else set.add(ch);
                }
            }
        }

        // validate cols
        for (int c = 0; c < board[0].length; c++) {
            Set<Character> set = new HashSet<>();
            for (int r = 0; r < board.length; r++) {
                char ch = board[r][c];
                if (ch != '.') {
                    if (set.contains(ch)) return false;
                    else set.add(ch);
                }
            }
        }

        // validate the nine 3x3 squares
        for (int r = 0; r < board.length; r += 3) {
            for (int c = 0; c < board[0].length; c += 3) {
                Set<Character> set = new HashSet<>();
                for (int r2 = r; r2 < r+3; r2++) {
                    for (int c2 = c; c2 < c+3; c2++) {
                        char ch = board[r2][c2];
                        if (ch != '.') {
                            if (set.contains(ch)) return false;
                            else set.add(ch);
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q0036_ValidSudoku sol = new Q0036_ValidSudoku();

        char[][] matrix1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        boolean isValid1 = sol.isValidSudoku(matrix1);

        char[][] matrix2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        boolean isValid2 = sol.isValidSudoku(matrix2);

        char[][] matrix3 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','5','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        boolean isValid3 = sol.isValidSudoku(matrix3);
    }
}
