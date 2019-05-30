import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/isomorphic-strings/description/
 *
 * Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 Example 1:

 Input: s = "egg", t = "add"
 Output: true
 Example 2:

 Input: s = "foo", t = "bar"
 Output: false
 Example 3:

 Input: s = "paper", t = "title"
 Output: true
 Note:
 You may assume both s and t have the same length.

 */
public class Q0205_IsomorphicStrings {
    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串
    public boolean isIsomorphic(String s, String t) {
        int[] prevIndexOfS = new int[256];
        Arrays.fill(prevIndexOfS, -1);
        int[] preIndexOfT = new int[256];
        Arrays.fill(preIndexOfT, -1);
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i), tChar = t.charAt(i);
            if (prevIndexOfS[sChar] != preIndexOfT[tChar]) {
                return false;
            }
            prevIndexOfS[sChar] = i;
            preIndexOfT[tChar] = i;
        }
        return true;
    }

    public static void main(String[] args) {
        Q0205_IsomorphicStrings sol = new Q0205_IsomorphicStrings();

        boolean isIso = sol.isIsomorphic("egg", "add");
        boolean isIso2 = sol.isIsomorphic("foo", "bar");
        boolean isIso3 = sol.isIsomorphic("paper", "title");
        boolean isIso4 = sol.isIsomorphic("ab", "aa");

    }
}
