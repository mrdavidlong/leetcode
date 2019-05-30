/*

https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0336_PalindromePairsWithTrie {
    //
//    private void print(TrieNode trie, boolean root){
//        if(root)
//            System.out.printf("[%s] (%s) (%s)->",(char)(' '), trie.index, trie.list.forEach(i -> System.out.println(i + ",")));
//        for(int i=0;i<trie.next.length;i++){
//            TrieNode child = trie.next[i];
//            if(child!=null){
//                System.out.printf("[%s] (%s) (%s)->",(char)('a'+i),child.index,child.palIdx);
//                print(child,false);
//                if(root)
//                    System.out.println();
//            }
//        }
//    }

    // https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }

        return res;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';

            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Q0336_PalindromePairsWithTrie sol = new Q0336_PalindromePairsWithTrie();
        List<List<Integer>> result = sol.palindromePairs(new String[] {"ba","a","aaa"});
        //List<List<Integer>> result = sol.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"});
        //List<List<Integer>> result = sol.palindromePairs(new String[] {"abcd","dcba","sll","s","sssll"});
    }
}
