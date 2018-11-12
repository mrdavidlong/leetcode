import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
 */
public class Q0646_MaximumLengthOfPairChain_DP {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);


        // get max of (dp[i] + 1) where pairs[i][1] < pairs[j][0]
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                if (pairs[i][1] < pairs[j][0])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for (int x: dp) if (x > ans) ans = x;
        return ans;
    }

    public static void main(String[] args) {
        Q0646_MaximumLengthOfPairChain_DP sol = new Q0646_MaximumLengthOfPairChain_DP();

        int[][] pairs1 = {{1,2}, {3,4}, {2,3}};
        int maxLength1 = sol.findLongestChain(pairs1);

        int[][] pairs2 = {{1,2}, {1,3}, {6,7}, {2,3}};
        int maxLength2 = sol.findLongestChain(pairs2);
    }
}
