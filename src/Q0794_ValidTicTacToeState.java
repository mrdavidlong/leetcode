/*
https://leetcode.com/problems/valid-tic-tac-toe-state/
A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Note:

board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.
 */
public class Q0794_ValidTicTacToeState {
    /*
    https://leetcode.com/problems/valid-tic-tac-toe-state/solution/
    Approach #1: Ad-Hoc [Accepted]

Intuition

Let's try to think about the necessary conditions for a tic-tac-toe board to be valid.

Since players take turns, the number of 'X's must be equal to or one greater than the number of 'O's.

A player that wins has to win on the move that they make:

If the first player wins, the number of 'X's is one more than the number of 'O's
If the second player wins, the number of 'X's is equal to the number of 'O's.
The board can't simultaneously have three 'X's and three 'O's in a row: once one player has won (on their move), there are no subsequent moves.

It turns out these conditions are also sufficient to check the validity of all boards. This is because we can check these conditions in reverse: in any board, either no player, one player, or both players have won. In the first two cases, we should check the previously stated counting conditions (a relationship between xCount and oCount), and this is sufficient. In the last case, it is not allowed to have both players win simultaneously, so our check was also sufficient.

Algorithm

We'll count the number of 'X's and 'O's as xCount and oCount.

We'll also use a helper function win(player) which checks if that player has won. This checks the 8 horizontal, vertical, or diagonal lines of the board for if there are three in a row equal to that player.

After, we just have to check our conditions as stated above.
     */
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (String row: board)
            for (char c: row.toCharArray()) {
                if (c == 'X') xCount++;
                if (c == 'O') oCount++;
            }

        if (oCount != xCount && oCount != xCount - 1) return false;
        if (win(board, 'X') && oCount != xCount - 1) return false;
        if (win(board, 'O') && oCount != xCount) return false;
        return true;
    }

    public boolean win(String[] B, char P) {
        // B: board, P: player
        for (int i = 0; i < 3; ++i) {
            if (P == B[0].charAt(i) && P == B[1].charAt(i) && P == B[2].charAt(i))
                return true;
            if (P == B[i].charAt(0) && P == B[i].charAt(1) && P == B[i].charAt(2))
                return true;
        }
        if (P == B[0].charAt(0) && P == B[1].charAt(1) && P == B[2].charAt(2))
            return true;
        if (P == B[0].charAt(2) && P == B[1].charAt(1) && P == B[2].charAt(0))
            return true;
        return false;
    }

    public static void main(String[] args) {
        Q0794_ValidTicTacToeState sol = new Q0794_ValidTicTacToeState();
        boolean isValid1 = sol.validTicTacToe(new String[] { "O  ", "   ", "   " }); // false
        boolean isValid2 = sol.validTicTacToe(new String[] { "XOX", " X ", "   " }); // false
        boolean isValid3 = sol.validTicTacToe(new String[] { "XXX", "   ", "OOO" }); // false
        boolean isValid4 = sol.validTicTacToe(new String[] { "XOX", "O O", "XOX" }); // true
    }
}
