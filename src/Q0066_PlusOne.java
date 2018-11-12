/**
 * https://leetcode.com/problems/plus-one/description/
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.
 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.
 */
public class Q0066_PlusOne {
    public int[] plusOneByDavid(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + carry;
            if (digits[i] == 10) {
                digits[i] = 0;
                carry = 1;
                if (i == 0) {
                    int[] result = new int[digits.length + 1];
                    result[0] = 1;
                    for (int j = 0; j < digits.length; j++) {
                        result[j+1] = digits[j];
                    }
                    return result;
                }
            } else {
                carry = 0;
            }
        }
        return digits;
    }

    // https://leetcode.com/problems/plus-one/discuss/24082/My-Simple-Java-Solution
    public int[] plusOne(int[] digits) {

        int n = digits.length;
        for (int i = n-1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }

    public static void main(String[] args) {
        Q0066_PlusOne sol = new Q0066_PlusOne();

        int[] ans = sol.plusOne(new int[] {1,2,3});
        int[] ans2 = sol.plusOne(new int[] {7,9});
        int[] ans3 = sol.plusOne(new int[] {9,9,9});
    }
}
