/**
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 *
 Implement atoi which converts a string to an integer.

 The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

 The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

 If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

 If no valid conversion could be performed, a zero value is returned.

 Note:

 Only the space character ' ' is considered as whitespace character.
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 Example 1:

 Input: "42"
 Output: 42
 Example 2:

 Input: "   -42"
 Output: -42
 Explanation: The first non-whitespace character is '-', which is the minus sign.
 Then take as many numerical digits as possible, which gets 42.
 Example 3:

 Input: "4193 with words"
 Output: 4193
 Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 Example 4:

 Input: "words and 987"
 Output: 0
 Explanation: The first non-whitespace character is 'w', which is not a numerical
 digit or a +/- sign. Therefore no valid conversion could be performed.
 Example 5:

 Input: "-91283472332"
 Output: -2147483648
 Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 Therefore INT_MIN (−2^31) is returned.

 */
public class Q0008_StringToInteger {
    public static int myAtoi(String str) {
        if (str.isEmpty()) return 0;

        int sign = 1, base = 0, i = 0;

        // get rid of white spaces
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        // process the +/- sign
        if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            if (str.charAt(i) == '-') sign = -1;
            i++;
        }

        // while digits
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            // handle overflow
            if (base > Integer.MAX_VALUE / 10
                    || (base == Integer.MAX_VALUE / 10
                        && ((sign == 1 && str.charAt(i) - '0' > 7)
                            || (sign == -1 && str.charAt(i) - '0' > 8)))) {
                if (sign == 1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }

            base = 10 * base + (str.charAt(i) - '0');
            i++;
        }
        return base * sign;
    }

    public static void main(String[] args) {
        String input = "   -123";
        int output = myAtoi(input);
        System.out.println("output = " + output);

        String input2 = Integer.toString(Integer.MAX_VALUE);
        int output2 = myAtoi(input2);
        System.out.println("output2 = " + output2);

        String input3 = Integer.toString(Integer.MIN_VALUE);
        int output3 = myAtoi(input3);
        System.out.println("output3 = " + output3);

        String input4 = "+";
        int output4 = myAtoi(input4);
        System.out.println("output4 = " + output4);
    }
}
