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
//    public int[] productExceptSelf(int[] nums) {
//        int n = nums.length;
//        int[] res = new int[n];
//        res[0] = 1;
//        for (int i = 1; i < n; i++) {
//            res[i] = res[i - 1] * nums[i - 1];
//        }
//        int right = 1;
//        for (int i = n - 1; i >= 0; i--) {
//            res[i] *= right;
//            right *= nums[i];
//        }
//        return res;
//    }

//    Given numbers [2, 3, 4, 5], regarding the third number 4, the product of array except 4 is 2*3*5 which consists of two parts: left 2*3 and right 5. The product is left*right. We can get lefts and rights:
//
//    Numbers:     2    3    4     5
//    Lefts:            2  2*3 2*3*4
//    Rights:  3*4*5  4*5    5
//    Let’s fill the empty with 1:
//
//    Numbers:     2    3    4     5
//    Lefts:       1    2  2*3 2*3*4
//    Rights:  3*4*5  4*5    5     1
//    We can calculate lefts and rights in 2 loops. The time complexity is O(n).
//
//    We store lefts in result array. If we allocate a new array for rights. The space complexity is O(n). To make it O(1), we just need to store it in a variable which is right in @lycjava3’s code.

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // Calculate lefts and store in res.
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left = left * nums[i - 1];
            res[i] = left;
        }
        // Calculate rights and the product from the end of the array.
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1)
                right = right * nums[i + 1];
            res[i] *= right;
        }
        return res;
    }

    public static void main(String[] args) {
        Q0238_ProductOfArrayExceptSelf sol = new Q0238_ProductOfArrayExceptSelf();
        int[] result = sol.productExceptSelf(new int[] {1,2,3,4});

        // input: [ 1, 2, 3, 4]
        //left product: [1, 1, 2, 6]
        //right product:[24,12,4, 1]
        // multiply left and right: [24,12, 8, 6]

        // res 1: [ 1, 1, 2, 6]
        // res 2: [24,12, 8, 6]
    }
}
