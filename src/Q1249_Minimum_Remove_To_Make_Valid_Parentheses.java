import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Q1249_Minimum_Remove_To_Make_Valid_Parentheses {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Q1249_Minimum_Remove_To_Make_Valid_Parentheses sol = new Q1249_Minimum_Remove_To_Make_Valid_Parentheses();
        String validString = sol.minRemoveToMakeValid("l(e)))et((co)d(e"); //Output: l(e)et(co)de"
        String validString1 = sol.minRemoveToMakeValid("lee(t(c)o)de)"); //Output: "lee(t(c)o)de"
        String validString2 = sol.minRemoveToMakeValid("a)b(c)d"); //Output: "ab(c)d"
        String validString3 = sol.minRemoveToMakeValid("))(("); //Output: ""
        String validString4 = sol.minRemoveToMakeValid("(a(b(c)d)"); //Output: "a(b(c)d)"
    }

}
