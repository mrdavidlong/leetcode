/**
 * https://leetcode.com/problems/missing-number/description/
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1:

 Input: [3,0,1]
 Output: 2
 Example 2:

 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8
 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?


 */
public class Q0268_MissingNumber {

    public int missingNumber(int[] nums) {
        int size = nums.length;
        int sumPlusMissingNumber = size * (size+1) / 2;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sumPlusMissingNumber - sum;
    }

    public static void main(String[] args) {
        Q0268_MissingNumber sol = new Q0268_MissingNumber();
        int missingNumber = sol.missingNumber(new int[] {3,0,1});
        int missingNumber2 = sol.missingNumber(new int[] {9,6,4,2,3,5,7,0,1});
    }
}
