import java.util.HashMap;
import java.util.HashSet;

/*
Share
Given a string s, return the maximum number of ocurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.


Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3
Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0


Constraints:

1 <= s.length <= 10^5
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s only contains lowercase English letters.
 */
public class Q1297_Maximum_Number_Of_Occurrences_Of_A_Substring {
    // https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/discuss/888643/Java-easy-to-understand-solution-O(n)
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        if (s == null || s.length() == 0 || maxLetters == 0) return 0;
        HashMap<String, Integer> hm = new HashMap<>();
        int max = 0;
        for(int i = 0; i < s.length() - minSize + 1; i++) {
            String cur = s.substring(i, i + minSize);
            if (isValid(cur, maxLetters)) {
                hm.put(cur, hm.getOrDefault(cur, 0) + 1);
                max = Math.max(max, hm.get(cur));
            }
        }
        return max;
    }

    boolean isValid(String cur, int maxLetters) {
        // length of cur  = minSize and makes this method run O(1)
        HashSet<Character> hs = new HashSet<>();
        for (char c: cur.toCharArray()) hs.add(c);
        return hs.size() <= maxLetters;
    }

    public static void main(String[] args) {
        Q1297_Maximum_Number_Of_Occurrences_Of_A_Substring sol = new Q1297_Maximum_Number_Of_Occurrences_Of_A_Substring();
        int result = sol.maxFreq("aababcaab", 2, 3, 4);
        int result2 = sol.maxFreq("aaaa", 1, 3, 3);
        int result3 = sol.maxFreq("aabcabcab", 2, 2, 3);
        int result4 = sol.maxFreq("abcde", 2, 3, 3);
    }
}
