import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/target-sum/description/
 *
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 */
public class Q0494_TargetSum_Knapsack {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = computeArraySum(nums);
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        int W = (sum + S) / 2;
        int[] dp = new int[W + 1];
        // dp[i] means number of ways to pick numbers in nums to have a sum of i
        dp[0] = 1;
        Arrays.sort(nums);
        for (int num : nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[W];
    }

    private int computeArraySum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }


    public static void main(String[] args) {
        Q0494_TargetSum_Knapsack sol = new Q0494_TargetSum_Knapsack();
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        int ways = sol.findTargetSumWays(nums, S);
    }


}
