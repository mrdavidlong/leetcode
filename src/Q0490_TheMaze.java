import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/the-maze/

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class Q0490_TheMaze {
    /*
    https://leetcode.com/problems/the-maze/solution/

    Approach #1 Depth First Search [Time Limit Exceeded]

We can view the given search space in the form of a tree. The root node of the tree represents the starting position. Four different routes are possible from each position i.e. left, right, up or down. These four options can be represented by 4 branches of each node in the given tree. Thus, the new node reached from the root traversing over the branch represents the new position occupied by the ball after choosing the corresponding direction of travel.

Maze_Tree

In order to do this traversal, one of the simplest schemes is to undergo depth first search. In this case, we choose one path at a time and try to go as deep as possible into the levels of the tree before going for the next path. In order to implement this, we make use of a recursive function dfs(maze, start, desination, visited). This function takes the given
m
a
z
e
maze array, the
s
t
a
r
t
start position and the
d
e
s
t
i
n
a
t
i
o
n
destination position as its arguments along with a
v
i
s
i
t
e
d
visited array.
v
i
s
i
t
e
d
visited array is a 2-D boolean array of the same size as that of
m
a
z
e
maze. A True value at
v
i
s
i
t
e
d
[
i
]
[
j
]
visited[i][j] represents that the current position has already been reached earlier during the path traversal. We make use of this array so as to keep track of the same paths being repeated over and over. We mark a True at the current position in the
v
i
s
i
t
e
d
visited array once we reach that particular positon in the
m
a
z
e
maze.

From every
s
t
a
r
t
start position, we can move continuously in either left, right, upward or downward direction till we reach the boundary or a wall. Thus, from the
s
t
a
r
t
start position, we determine all the end points which can be reached by choosing the four directions. For each of the cases, the new endpoint will now act as the new start point for the traversals. The destination, obviously remains unchanged. Thus, now we call the same function four times for the four directions, each time with a new start point obtained previously.

If any of the function call returns a True value, it means we can reach the desination.

The following animation depicts the process:

Current
1 / 10

Complexity Analysis

Time complexity :
O
(
m
n
)
O(mn). Complete traversal of maze will be done in the worst case. Here,
m
m and
n
n refers to the number of rows and coloumns of the maze.

Space complexity :
O
(
m
n
)
O(mn).
v
i
s
i
t
e
d
visited array of size
m
∗
n
m∗n is used.

Approach #2 Breadth First Search [Accepted]

Algorithm

The same search space tree can also be explored in a Depth First Search manner. In this case, we try to explore the search space on a level by level basis. i.e. We try to move in all the directions at every step. When all the directions have been explored and we still don't reach the destination, then only we proceed to the new set of traversals from the new positions obtained.

In order to implement this, we make use of a
q
u
e
u
e
queue. We start with the ball at the
s
t
a
r
t
start position. For every current position, we add all the new positions possible by traversing in all the four directions(till reaching the wall or boundary) into the
q
u
e
u
e
queue to act as the new start positions and mark these positions as True in the
v
i
s
i
t
e
d
visited array. When all the directions have been covered up, we remove a position value,
s
s, from the front of the
q
u
e
u
e
queue and again continue the same process with
s
s acting as the new
s
t
a
r
t
start position.

Further, in order to choose the direction of travel, we make use of a
d
i
r
dir array, which contains 4 entries. Each entry represents a one-dimensional direction of travel. To travel in a particular direction, we keep on adding the particular entry of the
d
i
r
s
dirs array till we hit a wall or a boundary. For a particular start position, we do this process of
d
i
r
dir addition for all all the four directions possible.

If we hit the destination position at any moment, we return a True directly indicating that the
d
e
s
t
i
n
a
t
i
o
n
destination position can be reached starting from the
s
t
a
r
t
start position.

The following animation depicts the process:

Current
1 / 15

Complexity Analysis

Time complexity :
O
(
m
n
)
O(mn). Complete traversal of maze will be done in the worst case. Here,
m
m and
n
n refers to the number of rows and coloumns of the maze.

Space complexity :
O
(
m
n
)
O(mn).
v
i
s
i
t
e
d
visited array of size
m
∗
n
m∗n is used and
q
u
e
u
e
queue size can grow upto
m
∗
n
m∗n in worst case.
     */
    // https://leetcode.com/problems/the-maze/solution/
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue< int[] > queue = new LinkedList< >();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            if (s[0] == destination[0] && s[1] == destination[1])
                return true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                if (!visited[x - dir[0]][y - dir[1]]) {
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }
        return false;
    }
}
