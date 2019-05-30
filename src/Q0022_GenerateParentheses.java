import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Q0022_GenerateParentheses {

    // https://leetcode.com/problems/generate-parentheses/solution/
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur + "(", open + 1, close, max);

        if (close < open) // close has the be less than or equal to open
            backtrack(ans, cur + ")", open, close + 1, max);
    }

    public static void main(String[] args) {
        Q0022_GenerateParentheses q = new Q0022_GenerateParentheses();
        List<String> list = q.generateParenthesis(3);
        list.forEach(s -> System.out.println(s));
    }
}
