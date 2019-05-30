import java.util.Arrays;

/**
 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#0-1-背包
 https://leetcode.com/problems/partition-equal-subset-sum/description/
 Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 Note:
 Each of the array element will not exceed 100.
 The array size will not exceed 200.
 Example 1:

 Input: [1, 5, 11, 5]

 Output: true

 Explanation: The array can be partitioned as [1, 5, 5] and [11].
 Example 2:

 Input: [1, 2, 3, 5]

 Output: false

 Explanation: The array cannot be partitioned into equal sum subsets.

 https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
 */
public class Q0416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = computeArraySum(nums);
        if (sum % 2 != 0) {
            return false;
        }
        int W = sum / 2;
        // dp[i] true when sum i can be formed by the numbers
        boolean[] dp = new boolean[W + 1];
        dp[0] = true;
        Arrays.sort(nums); // sort, so that we can do dp[i-num].  bigger indices depend on smaller indices
        for (int num : nums) {                 // 0-1 背包一个物品只能用一次
            for (int i = W; i >= num; i--) {   // 从后往前，先计算 dp[i] 再计算 dp[i-num]
                dp[i] = dp[i] || dp[i - num];
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
        Q0416_PartitionEqualSubsetSum sol = new Q0416_PartitionEqualSubsetSum();
        int[] nums = {1, 5, 11, 5};
        boolean canPartition = sol.canPartition(nums);
    }
}
