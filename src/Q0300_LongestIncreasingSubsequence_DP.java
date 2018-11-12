/**
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/
 */
public class Q0300_LongestIncreasingSubsequence_DP {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < dp.length; i++) {
            // get max out dp[j] where nums[j] < nums[i]
            int maxDpJInOrder = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxDpJInOrder = Math.max(maxDpJInOrder, dp[j]);
                }
            }

            // get max of dp[j] where nums[j] < nums[i], then + 1
            dp[i] = maxDpJInOrder + 1;

            // get max of dp[i]
            maxLength = Math.max(maxLength, dp[i]);
        }
        print(dp);
        return maxLength;
    }

    private static void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.printf("%3d", dp[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Q0300_LongestIncreasingSubsequence_DP sol = new Q0300_LongestIncreasingSubsequence_DP();
        int length1 = sol.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18});
    }
}
