/**
 * https://leetcode.com/problems/length-of-last-word/description/
 *
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 Example:

 Input: "Hello World"
 Output: 5
 */
public class Q0058_LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }

    public int lengthOfLastWord2(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int sizeOfLastWord = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ')
                sizeOfLastWord++;
            else if (sizeOfLastWord == 0)
                continue;
            else
                break;
        }
        return sizeOfLastWord;
    }

    public static void main(String[] args) {
        Q0058_LengthOfLastWord sol = new Q0058_LengthOfLastWord();

        int lengthOfLastWord = sol.lengthOfLastWord("Hello World");
        int lengthOfLastWord2 = sol.lengthOfLastWord("a");
    }
}
