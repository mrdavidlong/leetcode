/**
 * https://leetcode.com/problems/climbing-stairs/solution/
 *
 */
public class Q0070_ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    public int climbStairsMemo(int n) {
        int memo[] = new int[n + 1];
        return climbStairsMemoCore(0, n, memo);
    }
    public int climbStairsMemoCore(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climbStairsMemoCore(i + 1, n, memo) + climbStairsMemoCore(i + 2, n, memo);
        return memo[i];
    }

    public int climbStairsDP(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Q0070_ClimbingStairs sol = new Q0070_ClimbingStairs();
        int numOfWays3 = sol.climbStairs(3);
        int numOfWays4 = sol.climbStairs(4);

        int numOfWays4DP = sol.climbStairsDP(4);
    }
}
