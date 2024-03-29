/**
 * https://leetcode.com/problems/edit-distance/description/
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:

 Insert a character
 Delete a character
 Replace a character
 Example 1:

 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')
 Example 2:

 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串编辑
 */
public class Q0072_EditDistance {
    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串编辑
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int m = word1.length(), n = word2.length();

        // if one of the strings is empty
        if (n * m == 0)
            return n + m;

        // dp[i][j] is the min edit distance between the words up to index i of word 1 and index j of word 2
        int[][] dp = new int[m + 1][n + 1];

        // init boundaries
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /*
    Complexity Analysis

Time complexity : O(mn) as it follows quite straightforward for the inserted loops.
Space complexity : O(mn) since at each step we keep the results of all previous computations.
     */

    public static void main(String[] args) {
        Q0072_EditDistance sol = new Q0072_EditDistance();
        int distance = sol.minDistance("sea", "see");
        int distance1 = sol.minDistance("horse", "ros");
        int distance2 = sol.minDistance("intention", "execution");
    }
}
