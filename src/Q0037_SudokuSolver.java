public class Q0037_SudokuSolver {

    // This is better!
    //https://leetcode.com/problems/sudoku-solver/discuss/15752/Straight-Forward-Java-Solution-Using-Backtracking
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {

                if (board[r][c] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++) { // trial. Try 1 through 9
                        if (isValid(board, r, c, ch)) {
                            board[r][c] = ch; // Put ch for this cell

                            if (solve(board))
                                return true; // If it's the solution return true
                            else
                                board[r][c] = '.'; // Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.' && board[row][i] == c) return false; // check row

            if (board[i][col] != '.' && board[i][col] == c) return false; // check column

            if (board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; // check 3*3 block
        }
        return true;
    }

    public void print(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Q0037_SudokuSolver sol = new Q0037_SudokuSolver();

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

        sol.solveSudoku(matrix1);

        sol.print(matrix1);
    }
}
