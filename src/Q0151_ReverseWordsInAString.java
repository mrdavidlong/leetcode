/*
https://leetcode.com/problems/reverse-words-in-a-string/
Given an input string, reverse the string word by word.

Example 1:

Input: "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.

 */
public class Q0151_ReverseWordsInAString {

    public String reverseWords(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces, i < j check so that we can get pass the previous word
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces, j < i check so that we can get pass the previous word
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
//    String cleanSpaces(char[] a, int n) {
//        int i = 0, j = 0;
//
//        while (j < n) {
//            while (j < n && a[j] == ' ') j++;             // skip spaces
//            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
//            while (j < n && a[j] == ' ') j++;             // skip spaces
//            if (j < n) a[i++] = ' ';                      // keep only one space
//        }
//
//        return new String(a).substring(0, i);
//    }
//
    String cleanSpaces(char[] a) {
        String s = new String(a).trim();
        return s.replaceAll("\\s+", " ");
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    public static void main(String[] args) {
        String s = "  aaa  bbbb   ";
        String a = s.trim();
        String b = a.replaceAll("\\s+", " ");
        Q0151_ReverseWordsInAString sol = new Q0151_ReverseWordsInAString();
        String reversedWords = sol.reverseWords("the sky is blue");
        String reversedWords2 = sol.reverseWords("  hello world!  ");
        String reversedWords3 = sol.reverseWords("a good   example");
    }
}
