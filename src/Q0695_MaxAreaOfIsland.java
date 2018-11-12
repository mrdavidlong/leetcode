/**
 * https://leetcode.com/problems/max-area-of-island/description/
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#dfs
 */
public class Q0695_MaxAreaOfIsland {

    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j, m, n));
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        int area = 1;
        for (int[] d : direction) {
            area += dfs(grid, r + d[0], c + d[1], m, n);
        }
        return area;
    }

    public static void main(String[] args) {
        Q0695_MaxAreaOfIsland sol = new Q0695_MaxAreaOfIsland();

        int[][] grid =
            {{0,0,1,0,0,0,0,1,0,0,0,0,0},
             {0,0,0,0,0,0,0,1,1,1,0,0,0},
             {0,1,1,0,1,0,0,0,0,0,0,0,0},
             {0,1,0,0,1,1,0,0,1,0,1,0,0},
             {0,1,0,0,1,1,0,0,1,1,1,0,0},
             {0,0,0,0,0,0,0,0,0,0,1,0,0},
             {0,0,0,0,0,0,0,1,1,1,0,0,0},
             {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        int maxArea = sol.maxAreaOfIsland(grid);

    }
}
