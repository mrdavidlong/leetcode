/**
 * https://leetcode.com/problems/factorial-trailing-zeroes/description/
 *
 *
 Given an integer n, return the number of trailing zeroes in n!.

 Example 1:

 Input: 3
 Output: 0
 Explanation: 3! = 6, no trailing zero.
 Example 2:

 Input: 5
 Output: 1
 Explanation: 5! = 120, one trailing zero.
 Note: Your solution should be in logarithmic time complexity.
 */
public class Q0172_FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        //return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        if (n == 0) {
            return 0;
        } else {
            int nDiv5 = n/5;
            int recur = trailingZeroes(n / 5);
            return nDiv5 + recur;
        }
    }

    public static void main(String[] args) {
        Q0172_FactorialTrailingZeroes sol = new Q0172_FactorialTrailingZeroes();
        int numOfZeros = sol.trailingZeroes(3);
        int numOfZeros1 = sol.trailingZeroes(5);
        int numOfZeros2 = sol.trailingZeroes(10);
        int numOfZeros3 = sol.trailingZeroes(15);
        int numOfZeros4 = sol.trailingZeroes(20);
        int numOfZeros5 = sol.trailingZeroes(25);
    }
}
