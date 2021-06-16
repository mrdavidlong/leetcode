import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-anagrams/description/
 *
 * Given an array of strings, group anagrams together.

 Example:

 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note:

 All inputs will be in lowercase.
 The order of your output does not matter.
 */
public class Q0049_GroupAnagrams {
    // inspired by https://leetcode.com/problems/group-anagrams/solution/
//    public List<List<String>> groupAnagrams(String[] strs) {
//        if (strs == null || strs.length == 0) return new ArrayList();
//        Map<String, List> ans = new HashMap<>();
//        int[] count = new int[26];
//        for (String s : strs) {
//            // need to reset the count for each s
//            Arrays.fill(count, 0);
//            for (char c : s.toCharArray()) count[c - 'a']++;
//
//            StringBuilder sb = new StringBuilder("");
//            for (int i = 0; i < 26; i++) {
//                //sb.append('#');
//                if (count[i] > 0) {
//                    sb.append((char) ('a' + i));
//                    sb.append(count[i]);
//                }
//            }
//            String key = (sb.length() == 0) ?  "empty" : sb.toString();
//            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
//            ans.get(key).add(s);
//        }
//        return new ArrayList(ans.values());
//    }
//
//    public List<List<String>> groupAnagrams2(String[] strs) {
//        if (strs == null || strs.length == 0) return new ArrayList();
//        Map<String, List> ans = new HashMap<>();
//        for (String s : strs) {
//            char[] chars = s.toCharArray();
//            Arrays.sort(chars);
//            String sortedS = new String(chars);
//            if (!ans.containsKey(sortedS)) ans.put(sortedS, new ArrayList());
//            ans.get(sortedS).add(s);
//        }
//        return new ArrayList(ans.values());
//    }


    // time complexity: O(NK log K)
    // space complexity: O(NK)
    public List<List<String>> groupAnagramsOfficialSolution1(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    // time complexity: O(NK + NA)
    // space complexity: O(NK + NA)
    public List<List<String>> groupAnagramsOfficialSolution2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    public static void main(String[] args) {
        Q0049_GroupAnagrams sol = new Q0049_GroupAnagrams();

        List<List<String>> resultList = sol.groupAnagramsOfficialSolution1(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(resultList);
        List<List<String>> resultList2 = sol.groupAnagramsOfficialSolution2(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(resultList2);
    }
}
