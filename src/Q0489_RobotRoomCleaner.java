import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/robot-room-cleaner/

Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
 */
public class Q0489_RobotRoomCleaner {
    /**
     * // This is the robot's control interface.
     * // You should not implement it, or speculate about its implementation
     */
      interface Robot {
          // Returns true if the cell in front is open and robot moves into the cell.
          // Returns false if the cell in front is blocked and robot stays in the current cell.
          public boolean move();
     
          // Robot will stay in the same cell after calling turnLeft/turnRight.
          // Each turn will be 90 degrees.
          public void turnLeft();
          public void turnRight();
     
          // Clean the current cell.
          public void clean();
      }

      /*
      https://leetcode.com/problems/robot-room-cleaner/discuss/139057/Very-easy-to-understand-Java-solution
      To track the cells the robot has cleaned, start a index pair (i, j) from (0, 0). When go up, i-1; go down, i+1; go left, j-1; go right: j+1.
Also use DIR to record the current direction of the robot
       */
    public void cleanRoom(Robot robot) {
        // A number can be added to each visited cell
        // use string to identify the class
        Set<String> visited = new HashSet<>();
        backtrack(robot, visited, 0, 0, 0);
    }

    public void backtrack(Robot robot, Set<String> visited, int i, int j, int direction) {
        String tmp = i + "->" + j;
        // avoid infinite loop
        if (visited.contains(tmp)) {
            return;
        }

        robot.clean();
        visited.add(tmp);

        for(int n = 0; n < 4; n++) {
            // the robot can to four directions, we use right turn
            if (robot.move()) {
                // can go directly. Find the (x, y) for the next cell based on current direction
                int x = i, y = j;
                switch (direction) {
                    case 0:
                        // go up, reduce i
                        x = i-1;
                        break;
                    case 90:
                        // go right
                        y = j+1;
                        break;
                    case 180:
                        // go down
                        x = i+1;
                        break;
                    case 270:
                        // go left
                        y = j-1;
                        break;
                    default:
                        break;
                }

                backtrack(robot, visited, x, y, direction);
                // go back to the starting position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();

            }
            // turn to next direction
            robot.turnRight();
            direction += 90;
            direction %= 360;
        }

    }

    public static void main(String[] args) {
        Q0489_RobotRoomCleaner sol = new Q0489_RobotRoomCleaner();

        int[][] room = {
              {1, 1, 1, 1, 1, 0, 1, 1},
              {1, 1, 1, 1, 1, 0, 1, 1},
              {1, 0, 1, 1, 1, 1, 1, 1},
              {0, 0, 0, 1, 0, 0, 0, 0},
              {1, 1, 1, 1, 1, 1, 1, 1}
            };
        int row = 1;
        int col = 3;


    }
     
}
