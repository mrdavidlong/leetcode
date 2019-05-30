import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/best-meeting-point/
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input:

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance
             of 2+2+2=6 is minimal. So return 6.
 */
public class Q0296_BestMeetingPoint_BFS {
    /*
    https://leetcode.com/problems/best-meeting-point/solution/

    Approach #1 (Breadth-first Search) [Time Limit Exceeded]

A brute force approach is to evaluate all possible meeting points in the grid. We could apply breadth-first search originating from each of the point.

While inserting a point into the queue, we need to record the distance of that point from the meeting point. Also, we need an extra visited table to record which point had already been visited to avoid being inserted into the queue again.
*/

    public int minTotalDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int distance = search(grid, row, col);
                minDistance = Math.min(distance, minDistance);
            }
        }
        return minDistance;
    }

    private int search(int[][] grid, int row, int col) {
        Queue<Point> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        q.add(new Point(row, col, 0));
        int totalDistance = 0;
        while (!q.isEmpty()) {
            Point point = q.poll();
            int r = point.row;
            int c = point.col;
            int d = point.distance;
            if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) {
                continue;
            }
            if (grid[r][c] == 1) {
                totalDistance += d;
            }
            visited[r][c] = true;
            q.add(new Point(r + 1, c, d + 1));
            q.add(new Point(r - 1, c, d + 1));
            q.add(new Point(r, c + 1, d + 1));
            q.add(new Point(r, c - 1, d + 1));
        }
        return totalDistance;
    }

    public class Point {
        int row;
        int col;
        int distance;
        public Point(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    /*
Complexity analysis

Time complexity :
O(m^2n^2). For each point in the m × n size grid, the breadth-first search takes at most m×n steps to reach all points. Therefore the time complexity is


Space complexity : O(mn). The visited table consists of m × n elements map to each point in the grid. We insert at most m×n points into the queue.

*/

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };

        Q0296_BestMeetingPoint_BFS sol = new Q0296_BestMeetingPoint_BFS();
        int minDistance = sol.minTotalDistance(grid);
    }


}
