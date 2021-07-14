import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/*
532. K-diff Pairs in an Array
Medium

1227

1613

Add to List

Share
Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

0 <= i < j < nums.length
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.



Example 1:

Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:

Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:

Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Example 4:

Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
Output: 2
Example 5:

Input: nums = [-1,-2,-3], k = 1
Output: 2


Constraints:

1 <= nums.length <= 104
-107 <= nums[i] <= 107
0 <= k <= 107
 */
public class Q0532_K_Diff_Pairs_In_An_Array {

    // Time: O(n^2)
    // Space: O(n)
    public int findPairsBF(int[] nums, int k) {
        Arrays.sort(nums);

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                if (Math.abs(nums[j] - nums[i]) == k)
                    result++;
            }
        }

        return result;
    }

    // Time: O(n log n)
    // Space: O(n)
    public int findPairs2Pointers(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0, right = 1;
        int result = 0;

        while (left < nums.length && right < nums.length) {
            if (left == right || nums[right] - nums[left] < k) {
                // List item 1 in the text
                right++;
            } else if (nums[right] - nums[left] > k) {
                // List item 2 in the text
                left++;
            } else {
                // List item 3 in the text
                left++;
                result++;
                while (left < nums.length && nums[left] == nums[left - 1])
                    left++;
            }
        }
        return result;
    }

    // Time: O(n)
    // Space: O(n)
    public int findPairs(int[] nums, int k) {
        int result = 0;

        HashMap<Integer,Integer> counter = new HashMap<>();
        for (int n: nums) {
            counter.put(n, counter.getOrDefault(n, 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry: counter.entrySet()) {
            int x = entry.getKey();
            int val = entry.getValue();
            if (k > 0 && counter.containsKey(x + k)) {
                result++;
            } else if (k == 0 && val > 1) { // For example, if we have nums = [1,1,1,1] and k = 0, we have one unique (1,1) pair. In this case, our hash map will be {1: 4}, and this condition is satisfied since we have more than one occurrence of number 1.
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q0532_K_Diff_Pairs_In_An_Array sol = new Q0532_K_Diff_Pairs_In_An_Array();
        int numOfPairs = sol.findPairs(new int[] {3,1,4,1,5}, 2);
        int numOfPairs2 = sol.findPairs(new int[] {1,2,3,4,5}, 1);
        int numOfPairs3 = sol.findPairs(new int[] {1,3,1,5,4}, 0);
        int numOfPairs4 = sol.findPairs(new int[] {1,2,4,4,3,3,0,9,2,3}, 3);
        int numOfPairs5 = sol.findPairs(new int[] {-1,-2,-3}, 1);

    }
}
