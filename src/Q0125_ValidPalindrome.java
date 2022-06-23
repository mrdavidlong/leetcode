/**
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Note: For the purpose of this problem, we define empty string as valid palindrome.

 Example 1:

 Input: "A man, a plan, a canal: Panama"
 Output: true
 Example 2:

 Input: "race a car"
 Output: false

 */
public class Q0125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;

        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Q0125_ValidPalindrome sol = new Q0125_ValidPalindrome();
        boolean isPalindrome =  sol.isPalindrome("A man, a plan, a canal: Panama");
        boolean isPalindrome2 =  sol.isPalindrome("race a car");
        boolean isPalindrome3 =  sol.isPalindrome("0P");
        boolean isPalindrome4 =  sol.isPalindrome("1a2");
    }
}
