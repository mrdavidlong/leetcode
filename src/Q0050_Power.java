/**
 * https://leetcode.com/problems/powx-n/description/
 *
 * Implement pow(x, n), which calculates x raised to the power n (xn).

 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 Example 3:

 Input: 2.00000, -2
 Output: 0.25000
 Explanation: 2-2 = 1/22 = 1/4 = 0.25
 Note:

 -100.0 < x < 100.0
 n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class Q0050_Power {
//    public double powRec(double x, int n) {
//        if (n == 0)
//            return 1;
//        if (n < 0){
//            n = -n;
//            x = 1/x;
//        }
//        return (n % 2 == 0) ? powRec(x * x, n / 2) : x * powRec(x * x, n / 2);
//    }
//
//    public double pow(double x, int n) {
//        if (n < 0) {
//            n = -n;
//            x = 1/x;
//        }
//
//        double ret = 1;
//
//        while (n != 0) {
//            if (n % 2 == 1) {
//                ret *= x;
//            }
//            x *= x;
//            n /= 2;
//        }
//
//        return ret;
//    }

    // https://leetcode.com/problems/powx-n/solution/
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0050_Power sol = new Q0050_Power();

        double power2a = sol.myPow(2, 3);
        double power2b = sol.myPow(2, 4);
        double power2c = sol.myPow(2, 5);
        double power3 = sol.myPow(3, 3);
        double power5 = sol.myPow(5,3);
        double power2neg2 = sol.myPow(2, -2);
    }
}
