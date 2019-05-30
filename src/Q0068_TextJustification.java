import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/description/
 *
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 Note:

 A word is defined as a character sequence consisting of non-space characters only.
 Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 The input array words contains at least one word.
 Example 1:

 Input:
 words = ["This", "is", "an", "example", "of", "text", "justification."]
 maxWidth = 16
 Output:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Example 2:

 Input:
 words = ["What","must","be","acknowledgment","shall","be"]
 maxWidth = 16
 Output:
 [
 "What   must   be",
 "acknowledgment  ",
 "shall be        "
 ]
 Explanation: Note that the last line is "shall be    " instead of "shall     be",
 because the last line must be left-justified instead of fully-justified.
 Note that the second line is also left-justified becase it contains only one word.
 Example 3:

 Input:
 words = ["Science","is","what","we","understand","well","enough","to","explain",
 "to","a","computer.","Art","is","everything","else","we","do"]
 maxWidth = 20
 Output:
 [
 "Science  is  what we",
 "understand      well",
 "enough to explain to",
 "a  computer.  Art is",
 "everything  else  we",
 "do                  "
 ]
 */
public class Q0068_TextJustification {
    // https://leetcode.com/problems/text-justification/discuss/24876/Simple-Java-Solution
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();

        int startWord = 0;
        while (startWord < words.length) {
            int count = words[startWord].length();
            int lastWord = startWord + 1;
            while (lastWord < words.length) {
                if (words[lastWord].length() + count + 1 > maxWidth) break;
                count += words[lastWord].length() + 1;
                lastWord++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = lastWord - startWord - 1;
            // if last line or number of words in the line is 1, left-justified
            if (lastWord == words.length || diff == 0) {
                for (int i = startWord; i < lastWord; i++) {
                    builder.append(words[i] + " ");
                }
                // remove the last space
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int extraSpaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = startWord; i < lastWord; i++) {
                    builder.append(words[i]);
                    if (i < lastWord - 1) {
                        for (int j = 0; j <= (extraSpaces + ((i - startWord) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            startWord = lastWord;
        }

        return lines;
    }

    public static void main(String[] args) {
        Q0068_TextJustification sol = new Q0068_TextJustification();

        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> result1 = sol.fullJustify(words1, 16);

        String[] words2 = {"What","must","be","acknowledgment","shall","be"};
        List<String> result2 = sol.fullJustify(words2, 16);

        String[] words3 = {"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"};
        List<String> result3 = sol.fullJustify(words3, 20);
    }
}
