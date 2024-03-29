import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-product-of-three-numbers/description/
 *
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

 Example 1:
 Input: [1,2,3]
 Output: 6
 Example 2:
 Input: [1,2,3,4]
 Output: 24
 Note:
 The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class Q0628_MaximumProductOfThreeNumbers_BF {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    public static void main(String[] args) {
        Q0628_MaximumProductOfThreeNumbers_BF sol = new Q0628_MaximumProductOfThreeNumbers_BF();
        int max = sol.maximumProduct(new int[] {1,2,3,4});
        int max2 = sol.maximumProduct(new int[] {-7,-5,1,2,3,4});
    }
}
