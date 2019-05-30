import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-anagram/description/
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:

 Input: s = "anagram", t = "nagaram"
 Output: true
 Example 2:

 Input: s = "rat", t = "car"
 Output: false
 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?


 */
public class Q0242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();

        for (Character c : s.toCharArray()) {
          map.put(c, map.getOrDefault(c,0) + 1);
        }

        for (Character c : t.toCharArray()) {
            if (map.get(c) == null) return false;

            if (map.get(c) - 1 == 0) {
                map.remove(c);
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return map.isEmpty();
    }

    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#字符串
    public boolean isAnagram2(String s, String t) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q0242_ValidAnagram sol = new Q0242_ValidAnagram();
        boolean isAnagram = sol.isAnagram("anagram", "nagaram");
        boolean isAnagram2 = sol.isAnagram("rat", "cat");

    }
}
