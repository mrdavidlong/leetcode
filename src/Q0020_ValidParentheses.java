import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Created by davidlong on 6/30/18.
 */
public class Q0020_ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Q0020_ValidParentheses q = new Q0020_ValidParentheses();
        String input1 = "()[]{}";
        boolean valid1 = q.isValid(input1);
        System.out.println("valid1 = " + valid1);

        String input2 = "{[]}";
        boolean valid2 = q.isValid(input2);
        System.out.println("valid2 = " + valid2);

        String input3 = "{[()([])]}";
        boolean valid3 = q.isValid(input3);
        System.out.println("valid3 = " + valid3);

        String input4 = "]";
        boolean valid4 = q.isValid(input4);
        System.out.println("valid4 = " + valid4);

        String input5 = "[";
        boolean valid5 = q.isValid(input5);
        System.out.println("valid5 = " + valid5);
    }
}
