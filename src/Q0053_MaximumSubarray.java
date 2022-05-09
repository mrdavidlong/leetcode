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
            sum = (sum + nums[i] > 0) ? sum + nums[i] : 0;
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public int maxSubArrayOfficialSolution(int[] nums) {
        // Initialize our variables using the first element.
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];

        // Start with the 2nd element since we already used the first one.
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
            currentSubarray = Math.max(num, currentSubarray + num);
            maxSubarray = Math.max(maxSubarray, currentSubarray);
        }

        return maxSubarray;
    }

    public static void main(String[] args) {
        Q0053_MaximumSubarray sol = new Q0053_MaximumSubarray();
        int maxSubarray1 = sol.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        int maxSubarray2 = sol.maxSubArray(new int[] {-20,-10-5});
        int maxSubarray3 = sol.maxSubArray2(new int[] {-20,-10-5});
        int maxSubarray4 = sol.maxSubArrayOfficialSolution(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        int maxSubarray5 = sol.maxSubArrayOfficialSolution(new int[] {-20,-10-5});
    }
}
