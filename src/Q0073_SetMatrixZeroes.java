/**
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 *
 *
 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

 Example 1:

 Input:
 [
 [1,1,1],
 [1,0,1],
 [1,1,1]
 ]
 Output:
 [
 [1,0,1],
 [0,0,0],
 [1,0,1]
 ]
 Example 2:

 Input:
 [
 [0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]
 ]
 Output:
 [
 [0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]
 ]
 Follow up:

 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?
 */
public class Q0073_SetMatrixZeroes {
    /*
    Time Complexity:
    O(M×N) where M and N are the number of rows and columns respectively.

    Space Complexity:
    O(M+N).
     */
    public void setZeroesByDavid(int[][] matrix) {
        boolean[] zeroRows = new boolean[matrix.length];
        boolean[] zeroColumns= new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroColumns[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (zeroRows[i] || zeroColumns[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // https://leetcode.com/problems/set-matrix-zeroes/solution/
    /*
    Time Complexity :
    O((M×N)×(M+N)) where M and N are the number of rows and columns respectively. Even though this solution avoids using space, but is very inefficient since in worst case for every cell we might have to zero out its corresponding row and column. Thus for all
    (M×N) cells zeroing out
    (M+N) cells.
    Space Complexity :
    O(1)
     */
    public void setZeroesBF(int[][] matrix) {
        int MODIFIED = -1000000;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == 0) {
                    // We modify the corresponding rows and column elements in place.
                    // Note, we only change the non zeroes to MODIFIED
                    for (int k = 0; k < C; k++) {
                        if (matrix[r][k] != 0) {
                            matrix[r][k] = MODIFIED;
                        }
                    }
                    for (int k = 0; k < R; k++) {
                        if (matrix[k][c] != 0) {
                            matrix[k][c] = MODIFIED;
                        }
                    }
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // Make a second pass and change all MODIFIED elements to 0 """
                if (matrix[r][c] == MODIFIED) {
                    matrix[r][c] = 0;
                }
            }
        }
    }



    public void setZeroes(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {

            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }

            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Q0073_SetMatrixZeroes sol = new Q0073_SetMatrixZeroes();
        int[][] matrix =
        {
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };
        sol.setZeroes(matrix);

        int[][] matrix2 =
        {
            {0,1,2,0},
            {3,4,5,2},
            {1,3,1,5}
        };
        sol.setZeroes(matrix2);

    }
}
