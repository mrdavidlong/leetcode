import java.util.LinkedList;
import java.util.List;

/**
 * Created by davidlong on 6/30/18.
 */
public class Q0017_LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length() != digits.length()) {
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length())-'0'];
            for(char c: map.toCharArray()){
                ans.addLast(remove+c);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Q0017_LetterCombinationsOfAPhoneNumber q = new Q0017_LetterCombinationsOfAPhoneNumber();
        String input = "23";
        List<String> output = q.letterCombinations(input);
        output.forEach(s -> System.out.println(s));
//        for (String s : output) {
//            System.out.println(s);
//        }
    }
}
