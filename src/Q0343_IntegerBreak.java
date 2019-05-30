/**
 *https://leetcode.com/problems/integer-break/description/
 *
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

 Example 1:

 Input: 2
 Output: 1
 Explanation: 2 = 1 + 1, 1 × 1 = 1.
 Example 2:

 Input: 10
 Output: 36
 Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class Q0343_IntegerBreak {
    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#分割整数
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                //dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
                int a = j * (i - j); // try the number (i-j) then times j
                int b = j * dp[i - j]; // try the break down of the number (i-j) then times j
                int c = Math.max(a, b); // get max of the above two options
                dp[i] = Math.max(dp[i], c); // get the max with the dp[i];
            }
        }
        return dp[n];
    }

    // http://since1992.org/2016/05/23/Leetcode-343-Integer-Break/
    int integerBreak2(int n) {
        if (n < 4) return n-1;

        int[] dp = new int[n+1];
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Q0343_IntegerBreak sol = new Q0343_IntegerBreak();
        int maxIntBreak1 = sol.integerBreak(2);
        int maxIntBreak2 = sol.integerBreak(10);

        int maxIntBreak1b = sol.integerBreak2(2);
        int maxIntBreak2b = sol.integerBreak2(10);
    }
}
