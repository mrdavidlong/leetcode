/*
https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:

Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class Q0562_LongestLineOfConsecutiveOneInMatrix {
    // https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/solution/
    /*
    Approach #1 Brute Force [Accepted]

Algorithm

The brute force approach is really simple. We directly traverse along every valid line in the given matrix: i.e. Horizontal, Vertical, Diagonal aline above and below the middle diagonal, Anti-diagonal line above and below the middle anti-diagonal. Each time during the traversal, we keep on incrementing the
c
o
u
n
t
count if we encounter continuous 1's. We reset the
c
o
u
n
t
count for any discontinuity encountered. While doing this, we also keep a track of the maximum
c
o
u
n
t
count found so far.
Complexity Analysis

Time complexity :
O
(
n
2
)
O(n
2
 ). We traverse along the entire matrix 4 times.
Space complexity :
O
(
1
)
O(1). Constant space is used.

     */
    public int longestLine(int[][] M) {
        if (M.length == 0)
            return 0;
        int ones = 0;
        //horizontal
        for (int i = 0; i < M.length; i++) {
            int count = 0;
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        //vertical
        for (int i = 0; i < M[0].length; i++) {
            int count = 0;
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        //upper diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = 0, y = i; x < M.length && y < M[0].length; x++, y++) {
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        //lower diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = i, y = 0; x < M.length && y < M[0].length; x++, y++) {
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        //upper anti-diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = 0, y = M[0].length - i - 1; x < M.length && y >= 0; x++, y--) {
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        //lower anti-diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = i, y = M[0].length - 1; x < M.length && y >= 0; x++, y--) {
                //System.out.println(x+" "+y);
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        return ones;

    }
}
