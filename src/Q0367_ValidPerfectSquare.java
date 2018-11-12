/**
 * https://leetcode.com/problems/valid-perfect-square/description/
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Output: true
 Example 2:

 Input: 14
 Output: false

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#其它
 */
public class Q0367_ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int subNum = 1;
        while (num > 0) {
            num -= subNum;
            subNum += 2;
        }
        return num == 0;
    }

    public static void main(String[] args) {
        Q0367_ValidPerfectSquare sol = new Q0367_ValidPerfectSquare();
        boolean isSquare = sol.isPerfectSquare(16);
    }
}
