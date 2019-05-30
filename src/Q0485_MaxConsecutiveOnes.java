/**
 * https://leetcode.com/problems/max-consecutive-ones/description/
 *
 Given a binary array, find the maximum number of consecutive 1s in this array.

 Example 1:

 Input: [1,1,0,1,1,1]
 Output: 3
 Explanation: The first two digits or the last three digits are consecutive 1s.
 The maximum number of consecutive 1s is 3.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000
 */
public class Q0485_MaxConsecutiveOnes {
    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cur = 0;
        for (int x : nums) {
            cur = x == 0 ? 0 : cur + 1;
            max = Math.max(max, cur);
        }
        return max;
    }

    public static void main(String[] args) {
        Q0485_MaxConsecutiveOnes sol = new Q0485_MaxConsecutiveOnes();

        int maxOnes = sol.findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1});
    }
}
