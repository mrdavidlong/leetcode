/**
 * https://leetcode.com/problems/palindromic-substrings/description/
 *
 * Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:

 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:

 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 Note:

 The input string length won't exceed 1000.
 */
public class Q0647_PalindromicSubstrings {
  private int cnt = 0;

  public int countSubstrings(String s) {
    for (int i = 0; i < s.length(); i++) {
      extendSubstrings(s, i, i);     // 奇数长度
      extendSubstrings(s, i, i + 1); // 偶数长度
    }
    return cnt;
  }

  private void extendSubstrings(String s, int start, int end) {
    while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
      start--;
      end++;
      cnt++;
    }
  }

  public static void main(String[] args) {
   Q0647_PalindromicSubstrings sol = new Q0647_PalindromicSubstrings();

   int count1 = sol.countSubstrings("abc");
   int count2 = sol.countSubstrings("aaa");
  }
}
