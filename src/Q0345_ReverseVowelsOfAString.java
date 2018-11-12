import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by davidlong on 7/5/18.
 */
public class Q0345_ReverseVowelsOfAString {

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#双指针
    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                result[j--] = cj;
            } else {
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        Q0345_ReverseVowelsOfAString sol = new Q0345_ReverseVowelsOfAString();
//        Example 1:
//        Given s = "hello", return "holle".
        String s1 = "hello";
        String reversedS1 = sol.reverseVowels(s1);

//                Example 2:
//        Given s = "leetcode", return "leotcede".
        String s2 = "misc";
        String reversedS2 = sol.reverseVowels(s2);

    }
}
