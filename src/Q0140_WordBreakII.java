/*
https://leetcode.com/problems/word-break-ii/

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Q0140_WordBreakII {
    /*
    Summary

Given a string
s
s and a dictionary of words
d
i
c
t
dict, add spaces in
s
s to construct every possible sentence such that each word is valid as per the given dictionary. Return all such possible sentences.

Solution

Approach 1: Brute Force

Algorithm

The naive approach to solve this problem is to use recursion. For finding the solution, we check every possible prefix of that string (
s
s) in the dictionary of words, if it is found in the dictionary (say
s
1
s1), then the recursive function is called for the remaining portion of that string. This function returns the prefix
s
1
s1 appended by the result of the recursive call using the remaining portion of the string (
s
−
s
1
s−s1), if the remaining portion is a substring which can lead to the formation of a valid sentence as per the dictionary. Otherwise, empty list is returned.

     */

    public List<String> wordBreakBF(String s, Set<String> wordDict) {
        return word_BreakBF(s, wordDict, 0);
    }

    private List<String> word_BreakBF(String s, Set<String> wordDict, int start) {
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordDict.contains(word)) {
                List<String> list = word_BreakBF(s, wordDict, end);
                for (String l : list) {
                    res.add(word + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        return res;
    }

//
//    public List<String> wordBreakMemo(String s, Set<String> wordDict) {
//        return word_BreakMemo(s, wordDict, 0);
//    }
//    HashMap<Integer, List<String>> map = new HashMap<>();
//
//    public List<String> word_BreakMemo(String s, Set<String> wordDict, int start) {
//        if (map.containsKey(start)) {
//            return map.get(start);
//        }
//        LinkedList<String> res = new LinkedList<>();
//        if (start == s.length()) {
//            res.add("");
//        }
//        for (int end = start + 1; end <= s.length(); end++) {
//            if (wordDict.contains(s.substring(start, end))) {
//                List<String> list = word_BreakMemo(s, wordDict, end);
//                for (String l : list) {
//                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
//                }
//            }
//        }
//        map.put(start, res);
//        return res;
//    }

    public List<String> wordBreakMemo(String s, Set<String> wordDict) {
        return word_BreakMemo(s, wordDict, 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_BreakMemo(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            return res;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_BreakMemo(s, wordDict, end);
                if (list.isEmpty()) {
                    res.add(s.substring(start, end));
                } else {
                    for (String l : list) {
                        res.add(s.substring(start, end) + " " + l);
                    }
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public List<String> wordBreakDP(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        Q0140_WordBreakII sol = new Q0140_WordBreakII();
        //List<String> result1 = sol.wordBreakBF("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
        List<String> result2 = sol.wordBreakMemo("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
        List<String> result3 = sol.wordBreakDP("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog")));

    }
}


