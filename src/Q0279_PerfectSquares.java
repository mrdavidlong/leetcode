import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#搜索
 * https://leetcode.com/problems/perfect-squares/description/
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 Example 1:

 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.
 Example 2:

 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.
 */
public class Q0279_PerfectSquares {
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        queue.add(n);
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(cur - s);
                }
            }
        }
        return n; // default to n number of 1s
    }

    public int numSquaresDP(int n) {
        List<Integer> squareList = generateSquares(n);
        // dp[i] stores the min number of squares add up to number n
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int square : squareList) {
                if (square > i) {
                    break;
                }
                min = Math.min(min, dp[i - square] + 1); // dp[0] == 0, so if i == square then dp[i - square] == 0;
            }
            dp[i] = min;
        }
        return dp[n];
    }

    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,...
     */
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }

    public static void main(String[] args) {
        Q0279_PerfectSquares sol = new Q0279_PerfectSquares();
        int numOfSquares1 = sol.numSquares(12);
        int numOfSquares2 = sol.numSquares(13);

        int numOfSquaresDP1 = sol.numSquaresDP(12);
        int numOfSquaresDP2 = sol.numSquaresDP(13);
    }
}
