/*
https://leetcode.com/problems/first-unique-character-in-a-string/#_=_
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */

import java.util.HashMap;
import java.util.Map;

public class Q0387_FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q0387_FirstUniqueCharacterInAString sol = new Q0387_FirstUniqueCharacterInAString();
        int index1 = sol.firstUniqChar("leetcode"); // 0
        int index2 = sol.firstUniqChar("loveleetcode"); // 2
    }
}
