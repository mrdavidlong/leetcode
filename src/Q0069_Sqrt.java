/**
 * https://leetcode.com/problems/sqrtx/description/
 */
public class Q0069_Sqrt {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 1, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            } else if (mid > sqrt) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return h; // h get you the lower number (i.e. floor)
    }

    public static void main(String[] args) {
        Q0069_Sqrt sol = new Q0069_Sqrt();
/*
Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
         */
        int sqrt = sol.mySqrt(8);
        //int sqrt = sol.mySqrt(50);
    }
}
