/**
 *
 * https://leetcode.com/problems/minimum-path-sum/description/
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.

 Example:

 Input:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 Output: 7
 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class Q0064_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        for(int i=1;i<row;i++)
            grid[i][0] = grid[i][0]+grid[i-1][0];
        for(int j=1;j<col;j++)
            grid[0][j] = grid[0][j-1]+grid[0][j];

        for(int i=1;i<row;i++) {
            for(int j=1;j<col;j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[row-1][col-1];
    }

    public static void main(String[] args) {
        Q0064_MinimumPathSum sol = new Q0064_MinimumPathSum();
        int[][] grid1 = {
            {1,3,1},
            {1,5,1},
            {4,2,1}
        };
        int sum1 = sol.minPathSum(grid1);

        //[[1,3,1],[1,5,1],[4,2,1]]
    }

}
