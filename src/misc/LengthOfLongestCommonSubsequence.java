package misc;

/**
 *
 *
 */
public class LengthOfLongestCommonSubsequence {
    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#最长公共子序列
    public int lengthOfLCS(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    //https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
      /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lengthOfLCS( char[] X, char[] Y, int m, int n )
    {
        if (m == 0 || n == 0)
            return 0;
        if (X[m-1] == Y[n-1])
            return 1 + lengthOfLCS(X, Y, m - 1, n - 1);
        else
            return max(lengthOfLCS(X, Y, m, n - 1), lengthOfLCS(X, Y, m - 1, n));
    }

    /* Utility function to get max of 2 integers */
    int max(int a, int b)
    {
        return (a > b)? a : b;
    }

    public static void main(String[] args)
    {
        LengthOfLongestCommonSubsequence sol = new LengthOfLongestCommonSubsequence();
        int[] nums1 = {1,2,3,4,5,6};
        int[] nums2 = {2,4,6};
        System.out.println("Length of LCS is" + " " + sol.lengthOfLCS( nums1, nums2) );

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X=s1.toCharArray();
        char[] Y=s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " " + sol.lengthOfLCS(X, Y, m, n));
    }

}
