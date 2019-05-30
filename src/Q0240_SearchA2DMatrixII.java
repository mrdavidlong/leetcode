/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 Example:

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.


 */
public class Q0240_SearchA2DMatrixII {
    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (target == matrix[row][col]) return true;
            else if (target < matrix[row][col]) col--;
            else row++;
        }
        return false;
    }

    public static void main(String[] args) {
        Q0240_SearchA2DMatrixII sol = new Q0240_SearchA2DMatrixII();

        int[][] matrix = new int[][]
        {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        
        boolean found1 = sol.searchMatrix(matrix, 5);
        boolean found2 = sol.searchMatrix(matrix, 20);
    }
}
