import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class Q0296_BestMeetingPoint_Median {
    /*
    https://leetcode.com/problems/best-meeting-point/solution/

Approach #3 (Sorting) [Accepted]

Finding the best meeting point in a 2D grid seems difficult. Let us take a step back and solve the 1D case which is much simpler. Notice that the Manhattan distance is the sum of two independent variables. Therefore, once we solve the 1D case, we can solve the 2D case as two independent 1D problems.

Let us look at some 1D examples below:

Case #1: 1-0-0-0-1

Case #2: 0-1-0-1-0
We know the best meeting point must locate somewhere between the left-most and right-most point. For the above two cases, we would select the center point at
x
=
2
x=2 as the best meeting point. How about choosing the mean of all points as the meeting point?

Consider this case:

Case #3: 1-0-0-0-0-0-0-1-1
Using the mean gives us
x
ˉ
=
0
+
7
+
8
3
=
5
x
ˉ
 =
3
0+7+8
​
 =5 as the meeting point. The total distance is
10
10.

But the best meeting point should be at
x
=
7
x=7 and the total distance is
8
8.

You may argue that the mean is close to the optimal point. But imagine a larger case with many 1's congregating on the right side and just a single 1 on the left-most side. Using the mean as the meeting point would be far from optimal.

Besides mean, what is a better way to represent the distribution of points? Would median be a better representation? Indeed. In fact, the median must be the optimal meeting point.

Case #4: 1-1-0-0-1
To see why this is so, let us look at case #4 above and choose the median
x
=
1
x=1 as our initial meeting point. Assume that the total distance traveled is d. Note that we have equal number of points distributed to its left and to its right. Now let us move one step to its right where
x
=
2
x=2 and notice how the distance changes accordingly.

Since there are two points to the left of
x
=
2
x=2, we add
2
∗
(
+
1
)
2∗(+1) to d. And d is offset by –1 since there is one point to the right. This means the distance had overall increased by 1.

Therefore, it is clear that:

As long as there is equal number of points to the left and right of the meeting point, the total distance is minimized.
Case #5: 1-1-0-0-1-1
One may think that the optimal meeting point must fall on one of the 1's. This is true for cases with odd number of 1's, but not necessarily true when there are even number of 1's, just like case #5 does. You can choose any of the
x
=
1
x=1 to
x
=
4
x=4 points and the total distance is minimized. Why?

The implementation is direct. First we collect both the row and column coordinates, sort them and select their middle elements. Then we calculate the total distance as the sum of two independent 1D problems.
*/

    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        int row = rows.get(rows.size() / 2);
        Collections.sort(cols);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    /*

Note that in the code above we do not need to sort rows, why?

Complexity analysis

Time complexity : O(mnlogmn). Since there could be at most m×n points, therefore the time complexity is O(mnlogmn) due to sorting.

Space complexity : O(mn).
*/

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };

        Q0296_BestMeetingPoint_Median sol = new Q0296_BestMeetingPoint_Median();
        int minDistance = sol.minTotalDistance(grid);
    }


}
