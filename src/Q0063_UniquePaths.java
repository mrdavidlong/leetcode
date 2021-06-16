/**
 *
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 Now consider if some obstacles are added to the grids. How many unique paths would there be?



 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Note: m and n will be at most 100.

 Example 1:

 Input:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 Output: 2
 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right


 */
public class Q0063_UniquePaths {
    // https://leetcode.com/problems/unique-paths-ii/discuss/23430/Bottom-up-iterative-solution-O(mn)-no-extra-space
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (obstacleGrid[r][c] == 1) obstacleGrid[r][c] = 0;
                else {
                    if (r == m - 1 && c == n - 1) obstacleGrid[r][c] = 1; // 1 way start from and end at the end
                    else if (r == m - 1) obstacleGrid[r][c] = obstacleGrid[r][c + 1];
                    else if (c == n - 1) obstacleGrid[r][c] = obstacleGrid[r + 1][c];
                    else obstacleGrid[r][c] = obstacleGrid[r][c + 1] + obstacleGrid[r + 1][c];
                }
            }
        }

        return obstacleGrid[0][0];
    }

    public int uniquePathsWithObstaclesStartToEnd(int[][] obstacleGrid) {
        int rLength = obstacleGrid.length;
        int cLength = obstacleGrid[0].length;

        for (int r = 0; r < rLength; r++) {
            for (int c = 0; c < cLength; c++) {
                if (obstacleGrid[r][c] == 1) {
                    obstacleGrid[r][c] = 0;
                } else {
                    if (r == 0 && c == 0) {
                        obstacleGrid[r][c] = 1; // 1 way start from (0,0) and end at (0,0)
                    } else if (r == 0) {
                        obstacleGrid[r][c] = obstacleGrid[r][c - 1]; // if no obstacles in the first row, all values in the first row will be 1.  But if there is an obstacle, then all values ot the right of the obstacle will be 0
                    } else if (c == 0) {
                        obstacleGrid[r][c] = obstacleGrid[r - 1][c];
                    } else {
                        obstacleGrid[r][c] = obstacleGrid[r][c - 1] + obstacleGrid[r - 1][c];
                    }
                }
            }
        }

        return obstacleGrid[rLength-1][cLength-1];
    }

    // BEST
    // https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution
    public int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1]; // adding top and left value
            }
        }
        return dp[width - 1];
    }

    // https://leetcode.com/problems/unique-paths-ii/solution/
    public int uniquePathsWithObstaclesOfficialSolution(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }

    public static void main(String[] args) {

        Q0063_UniquePaths sol = new Q0063_UniquePaths();
        int[][] obstacleGrid =
        {
            {0,0,0},
            {0,1,0},
            {0,0,0}
        };

        int numOfPaths1 = sol.uniquePathsWithObstacles(obstacleGrid);

        int[][] obstacleGrid2 =
                {
                        {0,0,0},
                        {0,1,0},
                        {0,0,0}
                };

        int numOfPaths2 = sol.uniquePathsWithObstaclesStartToEnd(obstacleGrid2);

        int[][] obstacleGrid3 =
                {
                        {0,0,0},
                        {1,0,0},
                        {0,0,0}
                };
        int numOfPaths3 = sol.uniquePathsWithObstaclesDP(obstacleGrid3);



    }
}
