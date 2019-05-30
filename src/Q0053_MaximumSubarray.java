/**
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.


 */
public class Q0053_MaximumSubarray {
    // all neg, returns the one closet to 0
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = nums[0];
        int maxSum = sum;
        for (int i = 1; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // all neg, returns 0
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Q0053_MaximumSubarray sol = new Q0053_MaximumSubarray();
        int maxSubarray1 = sol.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        int maxSubarray2 = sol.maxSubArray(new int[] {-20,-10-5});
        int maxSubarray3 = sol.maxSubArray2(new int[] {-20,-10-5});
    }
}
