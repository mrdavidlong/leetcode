/**
 * https://leetcode.com/problems/arithmetic-slices/description/
 */
public class Q0413_ArithmeticSlices_Solution6_Formula {


    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int count = 0;
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                count++;
            } else {
                sum += (count + 1) * (count) / 2;
                count = 0;
            }
        }
        return sum += count * (count + 1) / 2;
    }

    public static void main(String[] args) {
        Q0413_ArithmeticSlices_Solution6_Formula sol1 = new Q0413_ArithmeticSlices_Solution6_Formula();
        int num1 = sol1.numberOfArithmeticSlices(new int[]{1, 2, 3});

        Q0413_ArithmeticSlices_Solution6_Formula sol2 = new Q0413_ArithmeticSlices_Solution6_Formula();
        int num2 = sol2.numberOfArithmeticSlices(new int[]{1, 2, 3, 4});

        Q0413_ArithmeticSlices_Solution6_Formula sol3 = new Q0413_ArithmeticSlices_Solution6_Formula();
        int num3 = sol3.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5});
    }

}
