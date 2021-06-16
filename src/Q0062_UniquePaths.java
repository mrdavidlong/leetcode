import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/unique-paths/description/
 *
 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?


 Above is a 7 x 3 grid. How many possible unique paths are there?

 Note: m and n will be at most 100.

 Example 1:

 Input: m = 3, n = 2
 Output: 3
 Explanation:
 From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 1. Right -> Right -> Down
 2. Right -> Down -> Right
 3. Down -> Right -> Right
 Example 2:

 Input: m = 7, n = 3
 Output: 28

 https://leetcode.com/problems/unique-paths/
 */
public class Q0062_UniquePaths {
//    public int uniquePaths(int m, int n) {
//        int[] dp = new int[n];
//        Arrays.fill(dp, 1);
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[j] = dp[j] + dp[j - 1];
//            }
//        }
//        return dp[n - 1];
//    }

    //https://leetcode.com/problems/unique-paths/discuss/22953/Java-DP-solution-with-complexity-O(n*m)
    /*
The assumptions are

When (n==0||m==0) the function always returns 1 since the robot
can't go left or up.
For all other cells. The result = uniquePaths(m-1,n)+uniquePaths(m,n-1)
Therefore I populated the edges with 1 first and use DP to get the full 2-D array.

Please give any suggestions on improving the code.
    */
//    public int uniquePaths(int m, int n) {
//        Integer[][] map = new Integer[m][n];
//        for (int i = 0; i < m; i++) {
//            map[i][0] = 1;
//        }
//
//        for (int j= 0; j < n; j++) {
//            map[0][j] = 1;
//        }
//
//        for (int i = 1; i < m; i++) {
//            for(int j = 1; j < n; j++) {
//                map[i][j] = map[i-1][j] + map[i][j-1];
//            }
//        }
//        return map[m-1][n-1];
//    }

    // https://leetcode.com/problems/unique-paths/discuss/22980/Clean-and-simple-DP-java
    public int uniquePaths(int r, int c) {
        int[][] grid = new int[r][c];
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                if (i == 0 || j == 0)
                    grid[i][j] = 1;
                else
                    grid[i][j] = grid[i][j-1] + grid[i-1][j];
            }
        }
        return grid[r-1][c-1];
    }

    // https://leetcode.com/problems/unique-paths/solution/
    public int uniquePathsOfficialSolution(int m, int n) {
        int[][] d = new int[m][n];

        for(int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for(int col = 1; col < m; ++col) {
            for(int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }

    // BEST
    // https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1]; // adding top and left value
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

        Q0062_UniquePaths sol = new Q0062_UniquePaths();
        int numOfPaths1 = sol.uniquePaths(1,1); // 1
        int numOfPaths2a = sol.uniquePaths(1,2); // 1
        int numOfPaths2b = sol.uniquePaths(2,1); // 1
        int numOfPaths2c = sol.uniquePaths(2,2); // 2
        int numOfPaths2d = sol.uniquePaths(3,2); // 3
        int numOfPaths3 = sol.uniquePaths(7,3); // 28

        int numOfPaths2_1 = sol.uniquePaths2(1,1); // 1
        int numOfPaths2_2a = sol.uniquePaths2(1,2); // 1
        int numOfPaths2_2b = sol.uniquePaths2(2,1); // 1
        int numOfPaths2_2c = sol.uniquePaths2(2,2); // 2
        int numOfPaths2_2d = sol.uniquePaths2(3,2); // 3
        int numOfPaths2_3 = sol.uniquePaths2(7,3); // 28
    }
}
