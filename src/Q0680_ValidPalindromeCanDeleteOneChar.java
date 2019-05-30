/**
 * Created by davidlong on 7/5/18.
 */
//https://leetcode.com/problems/valid-palindrome-ii/description/
public class Q0680_ValidPalindromeCanDeleteOneChar {
    // https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#双指针
    public boolean validPalindrome(String s) {
        int i = -1, j = s.length();
        while (++i < --j) {
            if (s.charAt(i) != s.charAt(j)) {
                // only need to check the inner strings on both sides
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/valid-palindrome-ii/solution/
    // Solution 1
    public boolean isPalindrome(CharSequence s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public boolean validPalindromeLCBF(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            if (isPalindrome(sb)) return true;
            sb.insert(i, c);
        }
        return isPalindrome(s);
    }

    // https://leetcode.com/problems/valid-palindrome-ii/solution/
    // Solution 2
    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
    public boolean validPalindromeLCBest(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Q0680_ValidPalindromeCanDeleteOneChar sol = new Q0680_ValidPalindromeCanDeleteOneChar();

//        Example 1:
//        Input: "aba"
//        Output: True
        boolean isPalindrome1 = sol.validPalindrome("aba");

//        Example 2:
//        Input: "abca"
//        Output: True
//        Explanation: You could delete the character 'c'.
        boolean isPalindrome2 = sol.validPalindrome("abca");
    }
}
