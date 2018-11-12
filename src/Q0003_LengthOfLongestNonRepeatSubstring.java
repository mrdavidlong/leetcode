import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class Q0003_LengthOfLongestNonRepeatSubstring {
    public int getLengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int maxLen = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                maxLen = Math.max(maxLen, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return maxLen;
    }

    // This is faster, it stores the last locations of the characters, and skip a bunch
    public int getLengthOfLongestSubstringBetter(String s) {
        int length = s.length();
        int maxLength = 0;
        int currentLength = 0;
        Map<Character, Integer> lastCharLocMap = new HashMap<>(); // current index of character

        // try to extend the range [i, j]
        for (int start = 0, end = 0; end < length; end++) {
            if (lastCharLocMap.containsKey(s.charAt(end))) {
                int lastCharLoc = lastCharLocMap.get(s.charAt(end));
                start = Math.max(lastCharLoc + 1, start); // move the start to one char after the repeat char
            }
            currentLength = end - start + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
            lastCharLocMap.put(s.charAt(end), end);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Q0003_LengthOfLongestNonRepeatSubstring sol = new Q0003_LengthOfLongestNonRepeatSubstring();

        String s = "abcabcbb";
        int result = sol.getLengthOfLongestSubstring(s);
        System.out.println("result: " + result);

        int result2 = sol.getLengthOfLongestSubstringBetter(s);
        System.out.println("result2: " + result2);

        int result3 = sol.getLengthOfLongestSubstringBetter("abba");
        System.out.println("result3: " + result3);
    }
}
