/*
https://leetcode.com/problems/range-sum-query-2d-mutable/
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:

The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class Q0308_RangeSumQuery2DMutable {

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

    /*
    https://leetcode.com/problems/range-sum-query-2d-mutable/discuss/75870/Java-2D-Binary-Indexed-Tree-Solution-clean-and-short-17ms
    // time should be O(log(m) * log(n))
     */
//    public static class NumMatrix {
//
//        int[][] tree;
//        int[][] nums;
//        int m;
//        int n;
//
//        public NumMatrix(int[][] matrix) {
//            if (matrix.length == 0 || matrix[0].length == 0) return;
//            m = matrix.length;
//            n = matrix[0].length;
//            tree = new int[m+1][n+1];
//            nums = new int[m][n];
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    update(i, j, matrix[i][j]);
//                }
//            }
//        }
//
//        public void update(int row, int col, int val) {
//            if (m == 0 || n == 0) return;
//            int delta = val - nums[row][col];
//            nums[row][col] = val;
//            for (int i = row + 1; i <= m; i += i & (-i)) {
//                for (int j = col + 1; j <= n; j += j & (-j)) {
//                    tree[i][j] += delta;
//                }
//            }
//        }
//
//        public int sumRegion(int row1, int col1, int row2, int col2) {
//            if (m == 0 || n == 0) return 0;
//            return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
//        }
//
//        public int sum(int row, int col) {
//            int sum = 0;
//            for (int i = row; i > 0; i -= i & (-i)) {
//                for (int j = col; j > 0; j -= j & (-j)) {
//                    sum += tree[i][j];
//                }
//            }
//            return sum;
//        }
//    }

/*
https://leetcode.com/problems/range-sum-query-2d-mutable/discuss/75852/15ms-easy-to-understand-java-solution
We use colSums[i][j] = the sum of ( matrix[0][j], matrix[1][j], matrix[2][j],......,matrix[i - 1][j] ).
 */

    public static class NumMatrix {
        private int[][] colSums;
        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null
                    || matrix.length == 0
                    || matrix[0].length == 0) {
                return;
            }

            this.matrix = matrix;

            int m = matrix.length;
            int n = matrix[0].length;
            colSums = new int[m + 1][n];
            for (int i = 1; i <= m; i++) {
                for (int j = 0; j < n; j++) {
                    colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
                }
            }
            //printMatrix(colSums);
        }

        //time complexity for the worst case scenario: O(m)
        public void update ( int row, int col, int val){
            for (int i = row + 1; i < colSums.length; i++) {
                colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
            }

            matrix[row][col] = val;
        }
        //time complexity for the worst case scenario: O(n)
        public int sumRegion ( int row1, int col1, int row2, int col2){
            int ret = 0;

            for (int j = col1; j <= col2; j++) {
                ret += colSums[row2 + 1][j] - colSums[row1][j];
            }

            return ret;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[][] matrix = {
          {3, 0, 1, 4, 2},
          {5, 6, 3, 2, 1},
          {1, 2, 0, 1, 5},
          {4, 1, 0, 1, 7},
          {1, 0, 3, 0, 5}
        };
        printMatrix(matrix);
        NumMatrix numMatrix = new NumMatrix(matrix);
        int sum = numMatrix.sumRegion(2, 1, 4, 3);
        numMatrix.update(3,2,2);
        int sum2 = numMatrix.sumRegion(2, 1, 4, 3);
    }
}
