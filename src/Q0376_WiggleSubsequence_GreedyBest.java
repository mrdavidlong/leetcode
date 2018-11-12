/**
 * https://leetcode.com/problems/wiggle-subsequence/solution/
 */
public class Q0376_WiggleSubsequence_GreedyBest {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int prevDiff = nums[1] - nums[0];
        int count = prevDiff == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)) { // the <= 0 and >= 0 comparison is to take care of the base case when nums[1] - nums[0] is 0
                count++;
                prevDiff = diff;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Q0376_WiggleSubsequence_GreedyBest sol = new Q0376_WiggleSubsequence_GreedyBest();
        int[] nums1 = {1,7,4,4,9,2,5};
        sol.wiggleMaxLength(nums1);

        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        sol.wiggleMaxLength(nums2);

        int[] nums3 = {1,2,3,4,5,6,7,8,9};
        sol.wiggleMaxLength(nums3);

    }
}
