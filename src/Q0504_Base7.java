/**
 * https://leetcode.com/problems/base-7/description/
 *
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#进制转换
 * Given an integer, return its base 7 string representation.

 Example 1:
 Input: 100
 Output: "202"
 Example 2:
 Input: -7
 Output: "-10"
 Note: The input will be in range of [-1e7, 1e7].
 */
public class Q0504_Base7 {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean isNegative = num < 0;
        if (isNegative) {
            num = -num;
        }
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        String ret = sb.reverse().toString();
        return isNegative ? "-" + ret : ret;
    }

    public static void main(String[] args) {
        Q0504_Base7 sol = new Q0504_Base7();
        String base7_1 = sol.convertToBase7(100);
        String base7_2 = sol.convertToBase7(-7);
    }
}
