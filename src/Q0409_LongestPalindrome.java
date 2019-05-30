/**
 *
 * https://leetcode.com/problems/longest-palindrome/description/
 *
 Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

 This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class Q0409_LongestPalindrome {
    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串
    public int longestPalindrome(String s) {
        int[] cnts = new int[256];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int palindrome = 0;
        for (int cnt : cnts) {
            palindrome += (cnt / 2) * 2;
        }
        if (palindrome < s.length()) {
            palindrome++;   // 这个条件下 s 中一定有单个未使用的字符存在，可以把这个字符放到回文的最中间
        }
        return palindrome;
    }

    public static void main(String[] args) {
        Q0409_LongestPalindrome sol = new Q0409_LongestPalindrome();

        int length = sol.longestPalindrome("abccccdd");
    }
}
