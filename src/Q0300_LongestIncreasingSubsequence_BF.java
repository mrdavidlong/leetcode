/**
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/
 */
public class Q0300_LongestIncreasingSubsequence_BF {
    public int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }

    public static void main(String[] args) {
        Q0300_LongestIncreasingSubsequence_BF sol = new Q0300_LongestIncreasingSubsequence_BF();
        int length1 = sol.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18});
    }
}
