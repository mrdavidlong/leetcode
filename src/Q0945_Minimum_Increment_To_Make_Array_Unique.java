import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums, a move consists of choosing any nums[i], and incrementing it by 1.

Return the least number of moves to make every value in nums unique.



Example 1:

Input: nums = [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].
Example 2:

Input: nums = [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.


Note:

0 <= nums.length <= 40000
0 <= nums[i] < 40000
 */
public class Q0945_Minimum_Increment_To_Make_Array_Unique {
    //https://leetcode.com/problems/minimum-increment-to-make-array-unique/solution/
    // Time: O(n)
    // Space: O(n)
    public int minIncrementForUnique(int[] nums) {
        int[] count = new int[100000];
        for (int x: nums) count[x]++;

        int ans = 0, taken = 0;

        for (int x = 0; x < 100000; ++x) {
            if (count[x] >= 2) {
                taken += count[x] - 1;
                ans -= x * (count[x] - 1);
            } else if (taken > 0 && count[x] == 0) {
                taken--;
                ans += x;
            }
        }

        return ans;
    }

    //https://leetcode.com/problems/minimum-increment-to-make-array-unique/solution/
    // Time: O(n long n)
    // Space: O(n)
//    public int minIncrementForUnique2(int[] nums) {
//        Arrays.sort(nums);
//        int ans = 0, taken = 0;
//
//        for (int i = 1; i < nums.length; ++i) {
//            if (nums[i-1] == nums[i]) {
//                taken++;
//                ans -= nums[i];
//            } else {
//                int give = Math.min(taken, nums[i] - nums[i-1] - 1);
//                ans += give * (give + 1) / 2 + give * nums[i-1];
//                taken -= give;
//            }
//        }
//
//        if (nums.length > 0)
//            ans += taken * (taken + 1) / 2 + taken * nums[nums.length - 1];
//
//        return ans;
//    }

    //https://leetcode.com/problems/minimum-increment-to-make-array-unique/discuss/197687/JavaC%2B%2BPython-Straight-Forward
    // Time: O(n long n)
    // Space: O(n)
    public int minIncrementForUnique3(int[] nums) {
        Arrays.sort(nums);
        int res = 0, need = 0;
        for (int x : nums) {
            res += Math.max(need - x, 0);
            need = Math.max(x, need) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Q0945_Minimum_Increment_To_Make_Array_Unique sol = new Q0945_Minimum_Increment_To_Make_Array_Unique();
        int result = sol.minIncrementForUnique(new int[] {1, 2, 2});
        int result2 = sol.minIncrementForUnique(new int[] {3,2,1,2,1,7});
//        int result3 = sol.minIncrementForUnique2(new int[] {1, 2, 2});
//        int result4 = sol.minIncrementForUnique2(new int[] {3,2,1,2,1,7});
        int result5 = sol.minIncrementForUnique3(new int[] {1, 2, 2});
        int result6 = sol.minIncrementForUnique3(new int[] {3,2,1,2,1,7});
        int result7 = sol.minIncrementForUnique3(new int[] {5,7,7,8,10});
    }
}
