/**
 * https://leetcode.com/problems/arithmetic-slices/description/
 */
public class Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest {

    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int dp = 0;
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else
                dp = 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest sol1 = new Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest();
        int num1 = sol1.numberOfArithmeticSlices(new int[]{1, 2, 3});

        Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest sol2 = new Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest();
        int num2 = sol2.numberOfArithmeticSlices(new int[]{1, 2, 3, 4});

        Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest sol3 = new Q0413_ArithmeticSlices_Solution5_DPConstantSpaceBest();
        int num3 = sol3.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5});
    }

}
