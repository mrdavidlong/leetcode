import java.util.Arrays;
import java.util.List;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#0-1-背包
 * https://leetcode.com/problems/word-break/description/
 *

 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".
 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 */
public class Q0139_WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i] is true when word at length i is breakable
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {   // 完全一个物品可以使用多次
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i))) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Q0139_WordBreak sol = new Q0139_WordBreak();
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean canBeBroken = sol.wordBreak(s, wordDict);

        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        boolean canBeBroken2 = sol.wordBreak(s2, wordDict2);

    }
}
