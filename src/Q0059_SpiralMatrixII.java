/**
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 *
 *
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

 Example:

 Input: 3
 Output:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 */
public class Q0059_SpiralMatrixII {
    // https://leetcode.com/problems/spiral-matrix-ii/discuss/22289/My-Super-Simple-Solution.-Can-be-used-for-both-Spiral-Matrix-I-and-II
    public int[][] generateMatrix(int n) {
        // Declaration
        int[][] matrix = new int[n][n];

        // Edge Case
        if (n == 0) {
            return matrix;
        }

        // Normal Case
        int rowStart = 0;
        int rowEnd = n-1;
        int colStart = 0;
        int colEnd = n-1;
        int num = 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = num++; //change
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++; //change
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    matrix[rowEnd][i] = num++; //change
                }
                rowEnd--;
            }

            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    matrix[i][colStart] = num++; //change
                }
                colStart++;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Q0059_SpiralMatrixII sol = new Q0059_SpiralMatrixII();
        int[][] matrix0 = sol.generateMatrix(0);
        int[][] matrix1 = sol.generateMatrix(1);
        int[][] matrix2 = sol.generateMatrix(2);
        int[][] matrix3 = sol.generateMatrix(3);
        int[][] matrix4 = sol.generateMatrix(4);
    }
}
