/**
 * https://leetcode.com/problems/decode-ways/description/
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class Q0091_DecodeWays {
    //https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
    /*
    I used a dp array of size n + 1 to save subproblem solutions.
    dp[i], where it's the max ways for string s with length of i
    dp[0] means an empty string will have one way to decode,
    dp[1] means the way to decode a string of size 1.
    I then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.
    */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // NOTE: 1 way to decode empty string
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i)); // 1's digit
            int second = Integer.valueOf(s.substring(i - 2, i)); // 10's digit
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Q0091_DecodeWays sol = new Q0091_DecodeWays();
        int waysEmpty = sol.numDecodings("");
        int ways0 = sol.numDecodings("0");
        int ways02 = sol.numDecodings("01");
        int ways = sol.numDecodings("10");
        int ways1 = sol.numDecodings("12");
        int ways2 = sol.numDecodings("226");
        int ways3 = sol.numDecodings("2261");
        int ways4 = sol.numDecodings("2211");
    }
}
