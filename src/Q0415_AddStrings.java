/**
 * https://leetcode.com/problems/add-strings/description/
 *
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串加法减法
 */
public class Q0415_AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder str = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (carry == 1 || i >= 0 || j >= 0) {
            int x = i < 0 ? 0 : num1.charAt(i--) - '0';
            int y = j < 0 ? 0 : num2.charAt(j--) - '0';
            str.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return str.reverse().toString();
    }

    public static void main(String[] args) {
        Q0415_AddStrings sol = new Q0415_AddStrings();
        String result = sol.addStrings("123","59");
    }
}
