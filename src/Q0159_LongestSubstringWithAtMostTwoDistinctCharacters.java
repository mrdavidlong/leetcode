import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

 Example 1:

 Input: "eceba"
 Output: 3
 Explanation: t is "ece" which its length is 3.
 Example 2:

 Input: "ccaabbb"
 Output: 5
 Explanation: t is "aabbb" which its length is 5.

 */
public class Q0159_LongestSubstringWithAtMostTwoDistinctCharacters {
    /*
    https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/49682/Simple-O(n)-java-solution-easily-extend-to-k-characters
    The main idea is to maintain a sliding window with 2 unique characters. The key is to store the last occurrence of each character as the value in the hashmap. This way, whenever the size of the hashmap exceeds 2, we can traverse through the map to find the character with the left most index, and remove 1 character from our map. Since the range of characters is constrained, we should be able to find the left most index in constant time.
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() < 1) return 0;
        HashMap<Character,Integer> index = new HashMap<Character,Integer>();
        int lo = 0;
        int hi = 0;
        int maxLength = 0;
        while(hi < s.length()) {
            if(index.size() <= 2) {
                char c = s.charAt(hi);
                index.put(c, hi);
                hi++;
            }
            if(index.size() > 2) {
                int leftMost = s.length();
                for(int i : index.values()) {
                    leftMost = Math.min(leftMost,i);
                }
                char c = s.charAt(leftMost);
                index.remove(c);
                lo = leftMost+1;
            }
            maxLength = Math.max(maxLength, hi-lo);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Q0159_LongestSubstringWithAtMostTwoDistinctCharacters sol = new Q0159_LongestSubstringWithAtMostTwoDistinctCharacters();
        int length = sol.lengthOfLongestSubstringTwoDistinct("eceba");
        int length2 = sol.lengthOfLongestSubstringTwoDistinct("ccaabbb");
    }
}
