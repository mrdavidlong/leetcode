/**
 * https://leetcode.com/problems/sort-colors/description/
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#荷兰国旗问题
 *
 Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note: You are not suppose to use the library's sort function for this problem.

 Example:

 Input: [2,0,2,1,1,0]
 Output: [0,0,1,1,2,2]
 */
public class Q0075_SortColors {

    //https://leetcode.com/problems/sort-colors/discuss/26500/Four-different-solutions
    // two pass O(m+n) space
    // using counting sort
    public void sortColors(int A[]) {
        int n = A.length;
        int num0 = 0, num1 = 0, num2 = 0;

        for(int i = 0; i < n; i++) {
            if (A[i] == 0) num0++;
            else if (A[i] == 1) num1++;
            else if (A[i] == 2) num2++;
        }

        for(int i = 0; i < num0; i++) A[i] = 0;
        for(int i = 0; i < num1; i++) A[num0+i] = 1;
        for(int i = 0; i < num2; i++) A[num0+num1+i] = 2;
    }

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#排序
    public void sortColorsThreePointers(int[] nums) {
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            } else {
                one++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        Q0075_SortColors sol = new Q0075_SortColors();
        /*

Input: [2,0,2,1,1,0]
Next:  [0,0,2,1,1,2]
Next:  [0,0,1,1,2,2]
Output: [0,0,1,1,2,2]

         */
        int[] nums = {2,0,2,1,1,0};
        sol.sortColors(nums);
    }
}
