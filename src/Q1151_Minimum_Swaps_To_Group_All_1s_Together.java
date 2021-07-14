import java.util.Arrays;

/*
Medium

472

4

Add to List

Share
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.



Example 1:

Input: data = [1,0,1,0,1]
Output: 1
Explanation:
There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
Example 2:

Input: data = [0,0,0,1,0]
Output: 0
Explanation:
Since there is only one 1 in the array, no swaps needed.
Example 3:

Input: data = [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation:
One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
Example 4:

Input: data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
Output: 8


Constraints:

1 <= data.length <= 105
data[i] is 0 or 1.
 */
public class Q1151_Minimum_Swaps_To_Group_All_1s_Together {
    public int minSwaps(int[] data) {
        int ones = Arrays.stream(data).sum();
        int cnt_one = 0, max_one = 0;
        int left = 0, right = 0;

        while (right < data.length) {
            // updating the number of 1's by adding the new element
            cnt_one += data[right++];
            // maintain the length of the window to ones
            if (right - left > ones) {
                // updating the number of 1's by removing the oldest element
                cnt_one -= data[left++];
            }
            // record the maximum number of 1's in the window
            max_one = Math.max(max_one, cnt_one);
        }
        return ones - max_one;
    }

    // Time: O(n)
    // Space: O(1)
    public int minSwaps2(int[] data) {
        int windowSize = 0;
        for (int num : data) {
            windowSize += num;
        }

        int curOnesInWindow = 0;
        int maxOnesIntWindow = 0;
        for (int i = 0; i < data.length; i++) {
            curOnesInWindow += data[i];
            if (i >= windowSize) {
                curOnesInWindow -= data[i - windowSize];
            }
            maxOnesIntWindow = Math.max(maxOnesIntWindow, curOnesInWindow);
        }
        return windowSize - maxOnesIntWindow;
    }

    public static void main(String[] args) {
        Q1151_Minimum_Swaps_To_Group_All_1s_Together sol = new Q1151_Minimum_Swaps_To_Group_All_1s_Together();
        int swaps = sol.minSwaps(new int[] {1,0,1,0,1});
        int swaps2 = sol.minSwaps(new int[] {0,0,0,1,0});
        int swaps3 = sol.minSwaps(new int[] {1,0,1,0,1,0,0,1,1,0,1});
        int swaps4 = sol.minSwaps(new int[] {1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1});
    }
}
