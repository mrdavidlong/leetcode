/**
 * https://leetcode.com/problems/interleaving-string/description/
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 Example 1:

 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 Output: true
 Example 2:

 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 Output: false

 https://www.youtube.com/watch?v=ih2OZ9-M3OM
 */
public class Q0097_InterleavingString {
//    public boolean isInterleave(String s1, String s2, String s3) {
//        int s1Index = 0;
//        int s2Index = 0;
//        int both = 0;
//        int i = 0;
//        for (; i < s3.length() && s1Index < s1.length() && s2Index < s2.length(); i++) {
//            char s1Char = s1.charAt(s1Index);
//            char s2Char = s2.charAt(s2Index);
//            char s3Char = s3.charAt(i);
//
//            if (s3Char != s1Char && s3Char != s2Char) return false;
//
//            if (s3Char == s1Char && s3Char != s2Char) {
//                s1Index++;
//                if (both > 0) {
//                    s2Index -= both;
//                    both = 0;
//                }
//            } else if (s3Char != s1Char && s3Char == s2Char) {
//                s2Index++;
//                if (both > 0) {
//                    s1Index -= both;
//                    both = 0;
//                }
//            } else {
//                s1Index++;
//                s2Index++;
//                both++;
//            }
//        }
//
//        if (both != 0) return false;
//
//        if (i < s3.length()) {
//            while (s1Index < s1.length() && i < s3.length()) {
//                char s1Char = s1.charAt(s1Index);
//                char s3Char = s3.charAt(i);
//                if (s1Char != s3Char) return false;
//                s1Index++;
//                i++;
//            }
//
//            while (s2Index < s2.length() && i < s3.length()) {
//                char s2Char = s2.charAt(s2Index);
//                char s3Char = s3.charAt(i);
//                if (s2Char != s3Char) return false;
//                s2Index++;
//                i++;
//            }
//        }
//
//        return s1Index == s1.length() && s2Index == s2.length() && i == s3.length();
//    }

    // https://leetcode.com/problems/interleaving-string/solution/
    // Solution 1: Brute Force
    public boolean is_InterleaveBruteForce(String s1,int i,String s2,int j,String res,String s3)
    {
        if(res.equals(s3) && i==s1.length() && j==s2.length())
            return true;
        boolean ans=false;
        if (i < s1.length())
            ans |= is_InterleaveBruteForce(s1,i+1, s2, j,res + s1.charAt(i), s3);
        if ( j < s2.length())
            ans |= is_InterleaveBruteForce(s1, i, s2,j+1,res + s2.charAt(j), s3);
        return ans;

    }
    public boolean is_InterleaveBruteForce(String s1, String s2, String s3) {
        return is_InterleaveBruteForce(s1,0,s2,0,"",s3);
    }

    // Approach 2: Recursion with memoization
    public boolean is_InterleaveBFWithMemo(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && is_InterleaveBFWithMemo(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_InterleaveBFWithMemo(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
    public boolean is_InterleaveBFWithMemo(String s1, String s2, String s3) {
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_InterleaveBFWithMemo(s1, 0, s2, 0, s3, 0, memo);
    }


    // Approach 3: Using 2D Dynamic Programming
    public boolean isInterleave2D(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // Approach 4: Using 1D Dynamic Programming
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[] = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }

    public static void main(String[] args) {
        Q0097_InterleavingString sol = new Q0097_InterleavingString();

        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean isInterleave = sol.isInterleave(s1, s2, s3);

        String t1 = "aabcc";
        String t2 = "dbbca";
        String t3 = "aadbbbaccc";
        boolean isInterleave2 = sol.isInterleave(t1, t2, t3);

        String u1 = "";
        String u2 = "b";
        String u3 = "b";
        boolean isInterleave3 = sol.isInterleave(u1, u2, u3);

        String v1 = "";
        String v2 = "";
        String v3 = "a";
        boolean isInterleave4 = sol.isInterleave(v1, v2, v3);

        String w1 = "";
        String w2 = "abc";
        String w3 = "ab";
        boolean isInterleave5 = sol.isInterleave(w1, w2, w3);

        String x1 = "aa";
        String x2 = "ab";
        String x3 = "aaba";
        boolean isInterleave6 = sol.isInterleave(x1, x2, x3);
    }
}
