/**
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:

 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]
 Note:

 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
 */
public class Q0283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx < nums.length) {
            nums[idx++] = 0;
        }
    }

    public static void main(String[] args) {
        Q0283_MoveZeroes sol = new Q0283_MoveZeroes();
        int[] a = {0,1,0,3,12};
        sol.moveZeroes(a);
        int i = 0;
    }
}
