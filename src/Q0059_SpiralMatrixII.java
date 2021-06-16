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
//    public int[][] generateMatrix(int n) {
//        // Declaration
//        int[][] matrix = new int[n][n];
//
//        // Edge Case
//        if (n == 0) {
//            return matrix;
//        }
//
//        // Normal Case
//        int rowStart = 0;
//        int rowEnd = n-1;
//        int colStart = 0;
//        int colEnd = n-1;
//        int num = 1;
//
//        while (rowStart <= rowEnd && colStart <= colEnd) {
//            for (int i = colStart; i <= colEnd; i++) {
//                matrix[rowStart][i] = num++; //change
//            }
//            rowStart++;
//
//            for (int i = rowStart; i <= rowEnd; i++) {
//                matrix[i][colEnd] = num++; //change
//            }
//            colEnd--;
//
//            if (rowStart <= rowEnd) {
//                for (int i = colEnd; i >= colStart; i--) {
//                    matrix[rowEnd][i] = num++; //change
//                }
//                rowEnd--;
//            }
//
//            if (colStart <= colEnd) {
//                for (int i = rowEnd; i >= rowStart; i--) {
//                    matrix[i][colStart] = num++; //change
//                }
//                colStart++;
//            }
//        }
//
//        return matrix;
//    }

//    public int[][] generateMatrix(int n) {
//        int[][] result = new int[n][n];
//        int cnt = 1;
//        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//        int d = 0;
//        int row = 0;
//        int col = 0;
//        while (cnt <= n * n) {
//            result[row][col] = cnt++;
//            int r = Math.floorMod(row + dir[d][0], n);
//            int c = Math.floorMod(col + dir[d][1], n);
//
//            // change direction if next cell is non zero
//            if (result[r][c] != 0) d = (d + 1) % 4;
//
//            row += dir[d][0];
//            col += dir[d][1];
//        }
//        return result;
//    }

    public int[][] generateMatrix2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }
        int[] dc = new int[]{1,0,-1,0};
        int[] dr = new int[]{0,1,0,-1};
        int dir = 0, val = 0, r = 0, c = 0, limit = n*n;
        int[][] matrix = new int[n][n];
        while (val++ < limit) {
            matrix[r][c] = val;
            r += dr[dir];
            c += dc[dir];
            if (isInvalid(matrix,r, c)) {
                r -= dr[dir];
                c -= dc[dir];
                dir = (dir+1)%4;
                r += dr[dir];
                c += dc[dir];
            }
        }
        return matrix;
    }

    private boolean isInvalid(int[][] m, int r, int c) {
        return r < 0 || c < 0 || r >= m.length || c >= m.length || m[r][c] != 0;
    }

    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }
        int[] dc = new int[]{1,0,-1,0};
        int[] dr = new int[]{0,1,0,-1};
        int dir = 0, val = 1, r = 0, c = 0, limit = n*n;
        int[][] matrix = new int[n][n];
        while (val <= limit) {
            matrix[r][c] = val;
            int nextR = r + dr[dir];
            int nextC = c + dc[dir];
            if (nextR >= 0 && nextR < matrix.length && nextC >= 0 && nextC < matrix[0].length && matrix[nextR][nextC] == 0){
                r = nextR;
                c = nextC;
            } else {
                dir = (dir + 1) % 4;
                r += dr[dir];
                c += dc[dir];
            }
            val++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        Q0059_SpiralMatrixII sol = new Q0059_SpiralMatrixII();
        //int[][] matrix0 = sol.generateMatrix(0);
        int[][] matrix1 = sol.generateMatrix(1);
        int[][] matrix2 = sol.generateMatrix(2);
        int[][] matrix3 = sol.generateMatrix(3);
        int[][] matrix4 = sol.generateMatrix(4);
    }
}
