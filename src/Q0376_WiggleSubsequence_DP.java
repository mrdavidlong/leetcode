/**
 * Created by davidlong on 9/8/18.
 */
public class Q0376_WiggleSubsequence_DP {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i],down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i],up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    public static void main(String[] args) {
        Q0376_WiggleSubsequence_DP sol = new Q0376_WiggleSubsequence_DP();
        int[] nums1 = {1,7,4,9,2,5};
        sol.wiggleMaxLength(nums1);

        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        sol.wiggleMaxLength(nums2);

        int[] nums3 = {1,2,3,4,5,6,7,8,9};
        sol.wiggleMaxLength(nums3);

    }
}
