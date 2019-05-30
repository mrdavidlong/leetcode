import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
public class Q0296_BestMeetingPoint_ManhattanDistance {
    /*
    https://leetcode.com/problems/best-meeting-point/solution/

Approach #2 (Manhattan Distance Formula) [Time Limit Exceeded]

You may notice that breadth-first search is unnecessary. You can just calculate the Manhattan distance using the formula:

distance(p1,p2)=∣p2.x−p1.x∣+∣p2.y−p1.y∣
*/

    public int minTotalDistance(int[][] grid) {
        List<Point> points = getAllPoints(grid);
        int minDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int distance = calculateDistance(points, row, col);
                minDistance = Math.min(distance, minDistance);
            }
        }
        return minDistance;
    }

    private int calculateDistance(List<Point> points, int row, int col) {
        int distance = 0;
        for (Point point : points) {
            distance += Math.abs(point.row - row) + Math.abs(point.col - col);
        }
        return distance;
    }

    private List<Point> getAllPoints(int[][] grid) {
        List<Point> points = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    points.add(new Point(row, col));
                }
            }
        }
        return points;
    }

    public class Point {
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /*
Complexity analysis

Time complexity : O(m^2 x n^2) Assume that k is the total number of houses. For each point in the m × n size grid, we calculate the manhattan distance in O(k).
Therefore the time complexity is O(mnk). But do note that there could be up to m×n houses, making the worst case time complexity to be O(m^2 x n^2)

Space complexity : O(mn).
*/

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };

        Q0296_BestMeetingPoint_ManhattanDistance sol = new Q0296_BestMeetingPoint_ManhattanDistance();
        int minDistance = sol.minTotalDistance(grid); // 6
    }


}
