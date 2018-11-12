/**
 *https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串编辑
 *
 * https://leetcode.com/problems/delete-operation-for-two-strings/description/
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

 Example 1:
 Input: "sea", "eat"
 Output: 2
 Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 Note:
 The length of given words won't exceed 500.
 Characters in given words can only be lower-case letters.


Official answer:
 https://leetcode.com/problems/delete-operation-for-two-strings/solution/

 */
public class Q0583_DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return m + n - 2 * dp[m][n]; // dp[m][n] is the longest common subsequence
    }

    public static void main(String[] args) {
        Q0583_DeleteOperationForTwoStrings sol = new Q0583_DeleteOperationForTwoStrings();
        int dist = sol.minDistance("sea", "eat");
    }
}
