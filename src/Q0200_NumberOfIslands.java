/**
 * https://leetcode.com/problems/number-of-islands/description/
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#dfs
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 Input:
 11110
 11010
 11000
 00000

 Output: 1
 Example 2:

 Input:
 11000
 11000
 00100
 00011

 Output: 3
 */
public class Q0200_NumberOfIslands {
    private int m, n;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int islandsNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    dfs(grid, i, j);
                    islandsNum++;
                }
            }
        }
        return islandsNum;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : direction) {
            dfs(grid, i + d[0], j + d[1]);
        }
    }

    public static void main(String[] args) {
        Q0200_NumberOfIslands sol = new Q0200_NumberOfIslands();
        char[][] grid =
            {{'1','1','1','1','0'},
             {'1','1','0','1','0'},
             {'1','1','0','0','0'},
             {'0','0','0','0','0'}};
        int numOfIsands = sol.numIslands(grid);

        char[][] grid2 =
                {{'1','1','0','0','0'},
                 {'1','1','0','0','0'},
                 {'0','0','1','0','0'},
                 {'0','0','0','1','1'}};
        int numOfIsands2 = sol.numIslands(grid2);
    }
}
