import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
 * 
 Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 Example 1:
 Input:
 s = "abpcplea", d = ["ale","apple","monkey","plea"]

 Output:
 "apple"
 Example 2:
 Input:
 s = "abpcplea", d = ["a","b","c"]

 Output:
 "a"
 */
public class Q0524_LongestWordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int longestWordLen = longestWord.length(), targetLen = target.length();
            if (longestWordLen > targetLen || (longestWordLen == targetLen && longestWord.compareTo(target) < 0)) {
                continue;
            }

            if (isValid(s, target)) {
                longestWord = target;
            }
        }
        return longestWord;
    }

    private boolean isValid(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }

    public static void main(String[] args) {
        Q0524_LongestWordInDictionaryThroughDeleting sol = new Q0524_LongestWordInDictionaryThroughDeleting();
        
        String s1 = "abpcplea";
        List<String> d1 = Arrays.asList("ale","apple","monkey","plea");
        
        String longestWord1 = sol.findLongestWord(s1, d1);

        String s2 = "abpcplea";
        List<String> d2 = Arrays.asList("a", "b", "c");

        String longestWord2 = sol.findLongestWord(s2, d2);
    }
}
