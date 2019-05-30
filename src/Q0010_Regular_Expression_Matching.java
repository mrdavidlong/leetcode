/**
 *https://leetcode.com/problems/regular-expression-matching/description/
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 Example 3:

 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".
 Example 4:

 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 Example 5:

 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false
 * https://leetcode.com/problems/regular-expression-matching/solution/
 */
public class Q0010_Regular_Expression_Matching {

    public boolean isMatchRec(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatchRec(text, pattern.substring(2)) || // assume zero occurrence for the starred char in text
                    (first_match && isMatchRec(text.substring(1), pattern))); // assume one or more occurrence for the starred char in text, if first_match
        } else {
            return first_match && isMatchRec(text.substring(1), pattern.substring(1));
        }
    }

    // Top-Down Variation
    enum Result {
        TRUE, FALSE
    }

    public boolean isMatchMemo(String text, String pattern) {
        Result[][] memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern, memo);
    }

    // i is index to the text
    // j is index to the pattern
    public boolean dp(int i, int j, String text, String pattern, Result[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }

        boolean ans;
        if (j == pattern.length()) {
            ans = (i == text.length());
        } else {
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*') {
                ans = (dp(i, j+2, text, pattern, memo) || // assume no starred char in text, so remove the starred char and star in the pattern
                        first_match && dp(i+1, j, text, pattern, memo)); // first match, so assume one or more starred char in text, so remove one char from text
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern, memo);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    // https://www.youtube.com/watch?v=l3hda49XcDE
    // https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/RegexMatching.java
    /**
     * Dynamic programming technique for regex matching.
     */
    public boolean isMatch(String text, String pattern) {
        if (text == null && pattern == null) return true;
        else if (text == null || pattern == null) return false;

        boolean[][] T = new boolean[text.length() + 1][pattern.length() + 1];
        T[0][0] = true;

        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            if (pattern.charAt(i-1) == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern.charAt(j - 1) == '.' || pattern.charAt(j - 1) == text.charAt(i - 1)) { // both same character,
                    T[i][j] = T[i-1][j-1]; // take off last char from both text and pattern
                } else if (pattern.charAt(j - 1) == '*')  {
                    T[i][j] = T[i][j - 2]; // assume no zero char in text
                    if (pattern.charAt(j-2) == '.' || pattern.charAt(j - 2) == text.charAt(i - 1)) { // text has the same starred char
                        T[i][j] = T[i][j] | T[i - 1][j]; // take off one char from text
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[text.length()][pattern.length()];
    }

    // Bottom-Up Variation
    public boolean isMatchBottomUpDP(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*') {
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Q0010_Regular_Expression_Matching q = new Q0010_Regular_Expression_Matching();

        String s0 = "abccfga";
        //String p0 = "abc*f.a";
        String p0 = "a*";
        boolean isMatchRec = q.isMatchRec(s0, p0);
        System.out.println("isMatchRec = " + isMatchRec);

        //boolean isMatchMemo = q.isMatchMemo(s0, p0);
        boolean isMatchMemo = q.isMatchMemo( "abccfga", "abc*f.a");
        //boolean isMatchMemo = q.isMatch("", "a*b*");
        System.out.println("isMatchMemo = " + isMatchMemo);

        boolean isMatch = q.isMatch(s0, p0);
        System.out.println("isMatch = " + isMatch);

        String s1 = "aa";
        String p1 = "a";
        boolean isMatch1 = q.isMatch(s1, p1);
        System.out.println("isMatch1 = " + isMatch1);

        String s2 = "aa";
        String p2 = "a*";
        boolean isMatch2 = q.isMatch(s2, p2);
        System.out.println("isMatch2 = " + isMatch2);

        String s3 = "aa";
        String p3 = ".*";
        boolean isMatch3 = q.isMatch(s3, p3);
        System.out.println("isMatch3 = " + isMatch3);

        String s4 = "aab";
        String p4 = "c*a*b";
        boolean isMatch4 = q.isMatch(s4, p4);
        System.out.println("isMatch4 = " + isMatch4);


        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        boolean isMatch5 = q.isMatch(s5, p5);
        System.out.println("isMatch5 = " + isMatch5);

        String s6 = "ab";
        String p6 = ".*c";
        boolean isMatch6 = q.isMatch(s6, p6);
        System.out.println("isMatch6 = " + isMatch6);

        String s7 = "aaa";
        String p7 = "a*a";
        boolean isMatch7 = q.isMatch(s7, p7);
        System.out.println("isMatch7 = " + isMatch7);
    }
}
