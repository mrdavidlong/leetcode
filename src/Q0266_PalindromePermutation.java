import java.util.HashSet;
import java.util.Set;

/*
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true
 */
public class Q0266_PalindromePermutation {
    // https://leetcode.com/problems/palindrome-permutation/solution/
//    public boolean canPermutePalindrome(String s) {
//        Set<Character> set = new HashSet<>();
//        for (int i = 0; i < s.length(); i++) {
//            if (!set.add(s.charAt(i)))
//                set.remove(s.charAt(i));
//        }
//        return set.size() <= 1;
//    }

    // https://leetcode.com/problems/palindrome-permutation/solution/
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
}
