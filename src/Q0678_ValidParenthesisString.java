public class Q0678_ValidParenthesisString {

/*

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
*/

/*
    Approach 1
    For each asterisk, let's try both possibilities.

    Complexity Analysis
    Time Complexity: O(N∗3 ^N) where N is the length of the string. For each asterisk we try 3 different values. Thus, we could be checking the validity of up to 3 ^ N strings. Then, each check of validity is O(N).
    Space Complexity: O(N), the space used by our character array.
*/
    boolean ans = false;

    public boolean checkValidString(String s) {
        solve(new StringBuilder(s), 0);
        return ans;
    }

    public void solve(StringBuilder sb, int i) {
        if (i == sb.length()) {
            ans |= valid(sb);
        } else if (sb.charAt(i) == '*') {
            for (char c: "() ".toCharArray()) {
                sb.setCharAt(i, c);
                solve(sb, i+1);
                if (ans) return;
            }
            sb.setCharAt(i, '*');
        } else
            solve(sb, i + 1);
    }

    public boolean valid(StringBuilder sb) {
        int bal = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') bal++;
            else if (c == ')') bal--;
            if (bal < 0) break;
        }
        return bal == 0;
    }

    public static void main(String[] args) {
        Q0678_ValidParenthesisString sol = new Q0678_ValidParenthesisString();
        boolean isValid1 = sol.checkValidString("()");
        boolean isValid2 = sol.checkValidString("(*)");
        boolean isValid3 = sol.checkValidString("(*))");
    }
}
