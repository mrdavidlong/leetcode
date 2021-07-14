public class Q0037_SudokuSolverBest {

    // Modified from https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
    public void solveSudoku(char[][] board) {
        boolean[][] rowsUsed = new boolean[9][10];  // 9 rows; 0 to 9 value, but 0 is not used, it just makes the math easier
        boolean[][] colsUsed = new boolean[9][10];
        boolean[][] cubesUsed = new boolean[9][10];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rowsUsed[i][num] = true;
                    colsUsed[j][num] = true;
                    cubesUsed[cubeNum(i, j)][num] = true;
                }
            }
        }

        backtracking(board, 0, 0, rowsUsed, colsUsed, cubesUsed);
    }

    private boolean backtracking(char[][] board, int row, int col, boolean[][] rowsUsed, boolean[][] colsUsed, boolean[][] cubesUsed) {
        // go to the next position to solve, i.e. position that is not a '.'
        while (row < 9 && board[row][col] != '.') {
            row = (col == 8) ? row + 1 : row;
            col = (col == 8) ? 0 : col + 1;
        }

        // reached the end, that means we have a solution
        if (row == 9) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (rowsUsed[row][num] || colsUsed[col][num] || cubesUsed[cubeNum(row, col)][num]) {
                continue;
            }
            rowsUsed[row][num] = colsUsed[col][num] = cubesUsed[cubeNum(row, col)][num] = true;
            board[row][col] = (char)(num + '0');

            if (backtracking(board, row, col, rowsUsed, colsUsed, cubesUsed)) {
                return true;
            }

            // if the previous solution doesn't work, then remove the num
            board[row][col] = '.';
            rowsUsed[row][num] = colsUsed[col][num] = cubesUsed[cubeNum(row, col)][num] = false;
        }
        return false;
    }

    private int cubeNum(int i, int j) {
        int r = i / 3;
        int c = j / 3;
        return r * 3 + c;
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
        Q0037_SudokuSolverBest sol = new Q0037_SudokuSolverBest();

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
