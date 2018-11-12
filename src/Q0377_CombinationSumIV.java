import java.util.Arrays;

/**
 * https://leetcode.com/problems/combination-sum-iv/description/
 *
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.


 nums = [1, 2, 3]
 target = 3

 The possible combination ways are:
 (1, 1, 1)
 (1, 2)
 (2, 1)
 (3)
 output is 4

 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?
 *
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#0-1-背包
 *
 *
 * This is actually PERMUTATION, NOT COMBINATION
 */
public class Q0377_CombinationSumIV {
//    public int combinationSum4(int[] nums, int target) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        // ways[i] is the number of combination to get to target sum i
//        int[] ways = new int[target + 1];
//        ways[0] = 1;
//        Arrays.sort(nums);
//        for (int i = 1; i <= target; i++) {
//            for (int j = 0; j < nums.length && nums[j] <= i; j++) {
//                ways[i] += ways[i - nums[j]];
//            }
//            printWays(ways);
//        }
//        return ways[target];
//    }

    // https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
    // https://www.youtube.com/watch?v=niZlmOtG4jM
    public int combinationSum4Rec(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4Rec(nums, target - nums[i]);
            }
        }
        return res;
    }

    public int combinationSum4Memo(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return helper(nums, target, memo);
    }

    private int helper(int[] nums, int target, int[] memo) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i], memo);
            }
        }
        memo[target] = res;
        return res;
    }

    public int combinationSum4DP(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
            printWays(dp);
        }
        return dp[target];
    }



    public static void main(String[] args) {
        Q0377_CombinationSumIV sol = new Q0377_CombinationSumIV();
        int[] nums = {1,2,3};
        int target = 4;
        int combinations = sol.combinationSum4Rec(nums, target);

        /*
        [1,1,0,0,0]
        [1,1,2,0,0]
        [1,1,2,4,0]
        [1,1,2,4,7]
        answer: 7
        */

        int combinations2 = sol.combinationSum4Memo(nums, target);

    }

    private static void printWays(int[] ways) {
        for (int i = 0; i < ways.length; i++) {
            System.out.print(ways[i]);
        }
        System.out.println();
    }
}
