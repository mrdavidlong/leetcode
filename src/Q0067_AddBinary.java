/**
 * https://leetcode.com/problems/add-binary/description/
 *
 * Given two binary strings, return their sum (also a binary string).

 The input strings are both non-empty and contains only characters 1 or 0.

 Example 1:

 Input: a = "11", b = "1"
 Output: "100"
 Example 2:

 Input: a = "1010", b = "1011"
 Output: "10101"

  1010
 +1011
 -----
 10101

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串加法减法
 */
public class Q0067_AddBinary {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder str = new StringBuilder();
        while (carry == 1 || i >= 0 || j >= 0) {
            if (i >= 0 && a.charAt(i--) == '1') {
                carry++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                carry++;
            }
            str.append(carry % 2);
            carry /= 2;
        }
        return str.reverse().toString();
    }

    public static void main(String[] args) {
        Q0067_AddBinary sol = new Q0067_AddBinary();
        String result = sol.addBinary("11", "1");
        String result2 = sol.addBinary("1010", "1011");
    }
}
