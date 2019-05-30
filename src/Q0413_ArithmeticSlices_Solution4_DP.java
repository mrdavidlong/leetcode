/**
 * https://leetcode.com/problems/arithmetic-slices/description/
 */
public class Q0413_ArithmeticSlices_Solution4_DP {

    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < dp.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Q0413_ArithmeticSlices_Solution4_DP sol1 = new Q0413_ArithmeticSlices_Solution4_DP();
        int num1 = sol1.numberOfArithmeticSlices(new int[]{1, 2, 3});

        Q0413_ArithmeticSlices_Solution4_DP sol2 = new Q0413_ArithmeticSlices_Solution4_DP();
        int num2 = sol2.numberOfArithmeticSlices(new int[]{1, 2, 3, 4});

        Q0413_ArithmeticSlices_Solution4_DP sol3 = new Q0413_ArithmeticSlices_Solution4_DP();
        int num3 = sol3.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5});
    }

}
