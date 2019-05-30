/**
 * Created by davidlong on 9/8/18.
 */
public class Q0376_WiggleSubsequence_LinearDP2SecondBest {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return Math.max(down, up);
    }

    public static void main(String[] args) {
        Q0376_WiggleSubsequence_LinearDP2SecondBest sol = new Q0376_WiggleSubsequence_LinearDP2SecondBest();
        int[] nums1 = {1,7,4,9,2,5};
        sol.wiggleMaxLength(nums1);

        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        sol.wiggleMaxLength(nums2);

        int[] nums3 = {1,2,3,4,5,6,7,8,9};
        sol.wiggleMaxLength(nums3);

    }

}
