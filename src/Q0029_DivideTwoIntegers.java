/**
 * Created by davidlong on 7/1/18.
 */
public class Q0029_DivideTwoIntegers {
//    public int divideByDavid(int dividend, int divisor) {
//        // Special case when overflow
//        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
//        if (dividend == Integer.MIN_VALUE && divisor == 1) return Integer.MIN_VALUE;
//
//        long dividendL = dividend;
//        long divisorL = divisor;
//
//        int sign = 1;
//        if (dividendL < 0 && divisorL > 0) {
//            sign = -1;
//            dividendL = -dividendL;
//        }
//
//        if (dividendL > 0 && divisorL < 0) {
//            sign = -1;
//            divisorL = -divisorL;
//        }
//
//        if (dividendL < 0 && divisorL < 0) {
//            dividendL = -dividendL;
//            divisorL = -divisorL;
//        }
//
//        int i = 0;
//        while (dividendL >= divisorL) {
//            dividendL -= divisorL;
//            i++;
//        }
//        return sign * i;
//    }

    // https://leetcode.com/problems/divide-two-integers/discuss/13397/Clean-Java-solution-with-some-comment.
    public int divide(int dividend, int divisor) {
        //Reduce the problem to positive long integer to make it easier.
        //Use long to avoid integer overflow cases.
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            sign = -1;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        //Take care the edge cases.
        if (ldivisor == 0) return Integer.MAX_VALUE;
        if ((ldividend == 0) || (ldividend < ldivisor))	return 0;

        long lans = ldivide(ldividend, ldivisor);

        int ans;
        if (lans > Integer.MAX_VALUE){ //Handle overflow.
            ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
        // Recursion exit condition
        if (ldividend < ldivisor) return 0;

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
        return multiple + ldivide(ldividend - sum, ldivisor);
    }

    public static void main(String[] args) {
        Q0029_DivideTwoIntegers sol = new Q0029_DivideTwoIntegers();
        
        int dividend1 = 100, divisor1 = 3;
        int quotient1 = sol.divide(dividend1, divisor1);
        System.out.println("quotient1 = " + quotient1);
        
        int dividend2 = -2147483648, divisor2 = -1;
        int quotient2 = sol.divide(dividend2, divisor2);
        System.out.println("quotient2 = " + quotient2);
        
    }
}
