/**
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
 Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

 Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

 Examples:
 Input:
 letters = ["c", "f", "j"]
 target = "a"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "c"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "d"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "g"
 Output: "j"

 Input:
 letters = ["c", "f", "j"]
 target = "j"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "k"
 Output: "c"
 */
public class Q0744_FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = n - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (letters[m] <= target) { // This is an equal sign, because if the character is equal, we want to find the next char, and thus we are increasing the lower bound
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }

    public static void main(String[] args) {
        Q0744_FindSmallestLetterGreaterThanTarget sol = new Q0744_FindSmallestLetterGreaterThanTarget();

        char[] chars1 = { 'c', 'f', 'j' };
        char nextCh1 = sol.nextGreatestLetter(chars1, 'a'); // 'c'

        char[] chars2 = { 'c', 'f', 'j' };
        char nextCh2 = sol.nextGreatestLetter(chars2, 'c'); // 'f'
        
    }
}
