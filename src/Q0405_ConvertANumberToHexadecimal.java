/**
 *
 * https://leetcode.com/problems/convert-a-number-to-hexadecimal/description/
 *
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

 Note:

 All letters in hexadecimal (a-f) must be in lowercase.
 The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 The given number is guaranteed to fit within the range of a 32-bit signed integer.
 You must not use any method provided by the library which converts/formats the number to hex directly.
 Example 1:

 Input:
 26

 Output:
 "1a"
 Example 2:

 Input:
 -1

 Output:
 "ffffffff"
 */
public class Q0405_ConvertANumberToHexadecimal {

    /*
Basic idea: each time we take a look at the last four digits of
            binary version of the input, and maps that to a hex char
            shift the input to the right by 4 bits, do it again
            until input becomes 0.

*/

    public String toHex(int num) {
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 0b1111]); // 0b stands for binary number, so it is 15
            num >>>= 4; // 因为考虑的是补码形式，因此符号位就不能有特殊的意义，需要使用无符号右移，左边填 0
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Q0405_ConvertANumberToHexadecimal sol = new Q0405_ConvertANumberToHexadecimal();
        String hex1 = sol.toHex(26);
        String hex2 = sol.toHex(-1);
    }
}
