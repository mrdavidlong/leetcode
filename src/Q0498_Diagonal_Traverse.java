/*
https://leetcode.com/problems/diagonal-traverse/
Share
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.



Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105

 */
public class Q0498_Diagonal_Traverse {
    //https://leetcode.com/problems/diagonal-traverse/discuss/97711/Java-15-lines-without-using-boolean
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int r = 0, c = 0, m = matrix.length, n = matrix[0].length, arr[] = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) { // moving up
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else { // moving down
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Q0498_Diagonal_Traverse sol = new Q0498_Diagonal_Traverse();
        int[] diag = sol.findDiagonalOrder(new int[][]
                {{1,2,3},
                 {4,5,6},
                 {7,8,9}});
        int[] diag2 = sol.findDiagonalOrder(new int[][]
                {{1,2},
                 {3,4}});
    }
}
