/**
 * Created by davidlong on 7/5/18.
 */
public class Q0665_NonDecreasingArray {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length && cnt < 2; i++) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            cnt++;
            if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return cnt <= 1;
    }

//    Example 1:
//    Input: [4,2,3]
//    Output: True
//    Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
//    Example 2:
//    Input: [4,2,1]
//    Output: False
//    Explanation: You can't get a non-decreasing array by modify at most one element.
    public static void main(String[] args) {
        Q0665_NonDecreasingArray sol = new Q0665_NonDecreasingArray();
        int[] nums1 = {4,2,3};
        boolean canAscendByModifyOne1 = sol.checkPossibility(nums1); // true

        int[] nums2 = {4,2,1};
        boolean canAscendByModifyOne2 = sol.checkPossibility(nums2); // false

        int[] nums3 = {1,2,7,9,4,10};
        boolean canAscendByModifyOne3 = sol.checkPossibility(nums3); // false
    }
}
