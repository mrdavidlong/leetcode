import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-harmonious-subsequence/description/
 *
 * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

 Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

 Example 1:

 Input: [1,3,2,2,5,2,3,7]
 Output: 5
 Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 Note: The length of the input array will not exceed 20,000.

 */
public class Q0594_LongestHarmoniousSubsequence {
//    public int findLHS(int[] nums) {
//        if (nums == null || nums.length == 0) throw new IllegalArgumentException("nums is empty");
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i);
//        }
//
//        int prev = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (Math.abs(nums[i] - prev) <= 1) {
//
//            }
//        }
//    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> countForNum = new HashMap<>();
        for (int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : countForNum.keySet()) {
            if (countForNum.containsKey(num + 1)) {
                longest = Math.max(longest, countForNum.get(num + 1) + countForNum.get(num));
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        Q0594_LongestHarmoniousSubsequence sol = new Q0594_LongestHarmoniousSubsequence();

        int length = sol.findLHS(new int[] {1,3,2,2,5,2,3,7});

    }
}
