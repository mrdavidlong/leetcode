import java.util.Arrays;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#其它
 * https://leetcode.com/problems/product-of-array-except-self/description/
 * The idea is simply. The product basically is calculated using the numbers before the current number and the numbers after the current number. Thus, we can scan the array twice. First, we calcuate the running product of the part before the current number. Second, we calculate the running product of the part after the current number through scanning from the end of the array.
 */
public class Q0238_ProductOfArrayExceptSelf {
//    public int[] productExceptSelf(int[] nums) {
//        int n = nums.length;
//        int[] products = new int[n];
//        Arrays.fill(products, 1);
//        int left = 1;
//        for (int i = 1; i < n; i++) {
//            left *= nums[i - 1];
//            products[i] *= left;
//        }
//        int right = 1;
//        for (int i = n - 2; i >= 0; i--) {
//            right *= nums[i + 1];
//            products[i] *= right;
//        }
//        return products;
//    }

    // https://leetcode.com/problems/product-of-array-except-self/discuss/65622/Simple-Java-solution-in-O(n)-without-extra-space
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Q0238_ProductOfArrayExceptSelf sol = new Q0238_ProductOfArrayExceptSelf();
        int[] result = sol.productExceptSelf(new int[] {1,2,3,4});

        // input: [ 1, 2, 3, 4]
        // res 1: [ 1, 1, 2, 6]
        // res 2: [24,12, 8, 6]
    }
}
