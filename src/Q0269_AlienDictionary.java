import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
https://leetcode.com/problems/alien-dictionary/

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class Q0269_AlienDictionary {
    // https://leetcode.com/problems/alien-dictionary/discuss/70119/Java-AC-solution-using-BFS
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>(); // map stores a Set of characters come after the key character
        Map<Character, Integer> degree = new HashMap<>(); // degree stores the number of characters come before the key character
        String result = "";
        if (words == null || words.length == 0) return result;
        for (String s: words) {
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = (map.containsKey(c1)) ? map.get(c1) : new HashSet<>();
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        for (char c: degree.keySet()) {
            if (degree.get(c) == 0) q.add(c);
        }

        while (!q.isEmpty()) {
            char c = q.remove();
            result += c;
            if (map.containsKey(c)) {
                for (char c2: map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0) q.add(c2);
                }
            }
        }
        if (result.length() != degree.size()) return "";
        return result;
    }

    public static void main(String[] args) {
        Q0269_AlienDictionary sol = new Q0269_AlienDictionary();
        String order = sol.alienOrder(new String[] {"wrt", "wrf", "er", "ett", "rftt"});
    }
}
