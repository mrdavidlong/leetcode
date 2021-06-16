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

    //////////////////
    // Official Solution
    // https://leetcode.com/problems/sudoku-solver/solution/

    // box size
    int n = 3;
    // row size
    int N = n * n;

    int [][] rows = new int[N][N + 1];
    int [][] columns = new int[N][N + 1];
    int [][] boxes = new int[N][N + 1];

    char[][] board;

    boolean sudokuSolved = false;

    public boolean couldPlace(int d, int row, int col) {
/*
Check if one could place a number d in (row, col) cell
*/
        int idx = (row / n ) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
/*
Place a number d in (row, col) cell
*/
        int idx = (row / n ) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char)(d + '0');
    }

    public void removeNumber(int d, int row, int col) {
/*
Remove a number which didn't lead to a solution
*/
        int idx = (row / n ) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
/*
Call backtrack function in recursion
to continue to place numbers
till the moment we have a solution
*/
        // if we're in the last cell
        // that means we have the solution
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        }
        // if not yet
        else {
            // if we're in the end of the row
            // go to the next row
            if (col == N - 1) backtrack(row + 1, 0);
                // go to the next column
            else backtrack(row, col + 1);
        }
    }

    public void backtrack(int row, int col) {
/*
Backtracking
*/
        // if the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    // if sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised
                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        }
        else placeNextNumbers(row, col);
    }

    public void solveSudoku2(char[][] board) {
        this.board = board;

        // init rows, columns and boxes
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
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
        sol.solveSudoku2(matrix1);

        sol.print(matrix1);
    }
}
