import java.util.HashMap;
import java.util.Map;

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

    //https://leetcode.com/problems/decode-ways/solution/
    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodingsRec(String s) {
        return recursiveWithMemo(0, s);
    }

    private int recursiveWithMemo(int index, String str) {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length() - 1) {
            return 1;
        }


        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }

        // Save for memoization
        memo.put(index, ans);

        return ans;
    }

    //https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
    /*
    I used a dp array of size n + 1 to save subproblem solutions.
    dp[i], where it's the max ways for string s with length of i
    dp[0] means an empty string will have one way to decode,
    dp[1] means the way to decode a string of size 1.
    I then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.
    */
    public int numDecodingsDP2(String s) {
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

    // https://leetcode.com/problems/decode-ways/solution/
    public int numDecodingsDP(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }

    // https://leetcode.com/problems/decode-ways/solution/
    public int numDecodingsBest(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }

            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    public static void main(String[] args) {
        Q0091_DecodeWays sol = new Q0091_DecodeWays();
        //int waysEmpty = sol.numDecodingsDP("");
        int ways0 = sol.numDecodingsDP("0");
        int ways02 = sol.numDecodingsDP("01");
        int ways = sol.numDecodingsDP("10");
        int ways1 = sol.numDecodingsDP("12");
        int ways2 = sol.numDecodingsDP("226");
        int ways3 = sol.numDecodingsDP("2261");
        int ways4 = sol.numDecodingsDP("2211");
        int ways5 = sol.numDecodingsRec("2211");
    }
}
