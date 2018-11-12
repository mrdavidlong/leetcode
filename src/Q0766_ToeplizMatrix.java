/**
 * https://leetcode.com/problems/toeplitz-matrix/description/
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

 Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


 Example 1:

 Input:
 matrix = [
 [1,2,3,4],
 [5,1,2,3],
 [9,5,1,2]
 ]
 Output: True
 Explanation:
 In the above grid, the diagonals are:
 "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 In each diagonal all elements are the same, so the answer is True.
 Example 2:

 Input:
 matrix = [
 [1,2],
 [2,2]
 ]
 Output: False
 Explanation:
 The diagonal "[1, 2]" has different elements.

 Note:

 matrix will be a 2D array of integers.
 matrix will have a number of rows and columns in range [1, 20].
 matrix[i][j] will be integers in range [0, 99].
 */
public class Q0766_ToeplizMatrix {
    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (!check(matrix, matrix[0][i], 0, i)) {
                return false;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (!check(matrix, matrix[i][0], i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int[][] matrix, int expectValue, int row, int col) {
        if (row >= matrix.length || col >= matrix[0].length) {
            return true;
        }
        if (matrix[row][col] != expectValue) {
            return false;
        }
        return check(matrix, expectValue, row + 1, col + 1);
    }

    public static void main(String[] args) {
        Q0766_ToeplizMatrix sol = new Q0766_ToeplizMatrix();

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        };
        boolean isToeplitz = sol.isToeplitzMatrix(matrix);

        int[][] matrix2 = {
                {1, 2},
                {2, 2}
        };
        boolean isToeplitz2 = sol.isToeplitzMatrix(matrix2);
    }

}
