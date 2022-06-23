import java.util.ArrayList;

/*
557. Reverse Words in a String III
Easy

2820

169

Add to List

Share
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.



Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Example 2:

Input: s = "God Ding"
Output: "doG gniD"
 */
public class Q0557_ReverseWordsInAStringIII {
    public String reverseWords1(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word: words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
    }
///////////////
    public String reverseWords2(String s) {
        String words[] = split(s);
        StringBuilder res = new StringBuilder();
        for (String word: words)
            res.append(reverse(word) + " ");
        return res.toString().trim();
    }
    public String[] split(String s) {
        ArrayList< String > words = new ArrayList < > ();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(word.toString());
                word = new StringBuilder();
            } else
                word.append( s.charAt(i));
        }
        words.add(word.toString());
        return words.toArray(new String[words.size()]);
    }
    public String reverse(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            res.insert(0,s.charAt(i));
        return res.toString();
    }

    ////////

    public String reverseWords3(String input) {
        final StringBuilder result = new StringBuilder();
        final StringBuilder word = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                word.append(input.charAt(i));
            } else {
                result.append(word.reverse());
                result.append(" ");
                word.setLength(0);
            }
        }
        result.append(word.reverse());
        return result.toString();
    }

}
