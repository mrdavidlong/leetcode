import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/candy-crush/
This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.



Example:

Input: board =  [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]  Output: [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]  Explanation:


Note:

The length of board will be in the range [3, 50].
The length of board[i] will be in the range [3, 50].
Each board[i][j] will initially start as an integer in the range [1, 2000].
 */
public class Q0723_CandyCrush {
    /*
     https://leetcode.com/problems/candy-crush/discuss/109221/AC-JAVA-Solution-easy-to-understand
     The idea is not to count how many same "candies" are in a row or column, but to check if this candy is eligible for crushing. If any candy is eligible, store the corresponding coordinates in a HashSet.
After traversing the whole board, set the valid candies to "0" then crush (using 2-pointer method in a column).
Here goes the code:
      */
    public int[][] candyCrush(int[][] board) {
        Set<Coordinates> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cur = board[i][j];
                if (cur == 0) continue;
                if ((i - 2 >= 0 && board[i - 1][j] == cur && board[i - 2][j] == cur) ||                                                 // check left 2 candies
                        (i + 2 <= board.length - 1 && board[i + 1][j] == cur && board[i + 2][j] == cur) ||                                   // check right 2 candies
                        (j - 2 >= 0 && board[i][j - 1] == cur && board[i][j - 2] == cur) ||                                                 // check 2 candies top
                        (j + 2 <= board[i].length - 1 && board[i][j + 1] == cur && board[i][j + 2] == cur) ||                               // check 2 candies below
                        (i - 1 >= 0 && i + 1 <= board.length - 1 && board[i - 1][j] == cur && board[i + 1][j] == cur) ||                    // check if it is a mid candy in row
                        (j - 1 >= 0 && j + 1 <= board[i].length - 1 && board[i][j - 1] == cur && board[i][j + 1] == cur)) {                // check if it is a mid candy in column
                    set.add(new Coordinates(i, j));
                }
            }
        }
        if (set.isEmpty()) return board;      //stable board
        for (Coordinates c : set) {
            int x = c.i;
            int y = c.j;
            board[x][y] = 0;      // change to "0"
        }
        drop(board);
        return candyCrush(board);
    }
    private void drop(int[][] board) {                                          // using 2-pointer to "drop"
        for (int j = 0; j < board[0].length; j++) {
            int bot = board.length - 1;
            int top = board.length - 1;
            while (top >= 0) {
                if (board[top][j] == 0) {
                    top--;
                }
                else {
                    board[bot--][j] = board[top--][j];
                }
            }
            while (bot >= 0) {
                board[bot--][j] = 0;
            }
        }
    }
}

class Coordinates {
    int i;
    int j;
    Coordinates(int x, int y) {
        i = x;
        j = y;
    }
}
