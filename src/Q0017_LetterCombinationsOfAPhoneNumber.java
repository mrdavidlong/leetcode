import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidlong on 6/30/18.
 */
public class Q0017_LetterCombinationsOfAPhoneNumber {

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
    private static final String[] KEYS = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        doCombination(digits, result, new StringBuilder());
        return result;
    }

    private void doCombination(final String digits, List<String> result, StringBuilder tempList) {
        if (tempList.length() == digits.length()) {
            result.add(tempList.toString());
            return;
        }

        int curDigits = digits.charAt(tempList.length()) - '0';
        String letters = KEYS[curDigits];
        for (char c : letters.toCharArray()) {
            tempList.append(c); // add
            doCombination(digits, result, tempList);
            tempList.deleteCharAt(tempList.length() - 1); // remove
        }
    }

    public static void main(String[] args) {
        Q0017_LetterCombinationsOfAPhoneNumber q = new Q0017_LetterCombinationsOfAPhoneNumber();
        String input = "23";
        List<String> output = q.letterCombinations(input);
        output.forEach(s -> System.out.println(s));
    }
}
