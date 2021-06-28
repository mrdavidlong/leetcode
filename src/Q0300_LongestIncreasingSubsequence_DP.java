import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/
 */
public class Q0300_LongestIncreasingSubsequence_DP {
    // time complexity: O(N^2)
    // space  complexity: O(N)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int c: dp) {
            longest = Math.max(longest, c);
        }

        return longest;
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
