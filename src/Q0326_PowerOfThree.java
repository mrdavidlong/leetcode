/**
 * https://leetcode.com/problems/power-of-three/description/
 *
 * Given an integer, write a function to determine if it is a power of three.

 Example 1:

 Input: 27
 Output: true
 Example 2:

 Input: 0
 Output: false
 Example 3:

 Input: 9
 Output: true
 Example 4:

 Input: 45
 Output: false
 Follow up:
 Could you do it without using any loop / recursion?
 */
public class Q0326_PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    public static void main(String[] args) {
        Q0326_PowerOfThree sol = new Q0326_PowerOfThree();
        boolean isPowerOf3 = sol.isPowerOfThree(27);
        boolean isPowerOf3b = sol.isPowerOfThree(30);
    }
}
