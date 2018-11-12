import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidlong on 6/30/18.
 */
public class Q0022_GenerateParentheses {
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
        if (close < open)
            backtrack(ans, cur + ")", open, close + 1, max);
    }

    public static void main(String[] args) {
        Q0022_GenerateParentheses q = new Q0022_GenerateParentheses();
        List<String> list = q.generateParenthesis(3);
        list.forEach(s -> System.out.println(s));
    }
}
