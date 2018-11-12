/**
 * Created by davidlong on 10/25/18.
 * Write a function that takes a string as input and returns the string reversed.

 Example 1:

 Input: "hello"
 Output: "olleh"
 Example 2:

 Input: "A man, a plan, a canal: Panama"
 Output: "amanaP :lanac a ,nalp a ,nam A"

 */
public class Q0344_ReverseString {
    public String reverseString(String s) {
        if (s == null || s.isEmpty()) return s;

        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Q0344_ReverseString sol = new Q0344_ReverseString();
        sol.reverseString("abcde");
    }

}
