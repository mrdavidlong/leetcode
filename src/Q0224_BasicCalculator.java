import java.util.Stack;

/*
https://leetcode.com/problems/basic-calculator/
224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class Q0224_BasicCalculator {
    // wrong answer, because it doesn't take the paren order
//    public int calculate(String s) {
//        String newS = s.replaceAll("\\s|\\(|\\)", "");
//        int i = 0;
//
//        int total = 0;
//        int sign = 1;
//        while (i < newS.length()) {
//            StringBuilder numberSb = new StringBuilder();
//            while (i < newS.length() && Character.isDigit(newS.charAt(i))) {
//                numberSb.append(newS.charAt(i));
//                i++;
//            }
//
//            total += (sign * Integer.parseInt(numberSb.toString()));
//
//            if (i == newS.length()) break;
//
//            if (newS.charAt(i) == '-') {
//                sign = -1;
//            } else {
//                sign = 1;
//            }
//            i++;
//        }
//        return total;
//    }

    // https://leetcode.com/problems/basic-calculator/discuss/62362/JAVA-Easy-Version-To-Understand!!!!!
    public static int calculate(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+')
                sign = 1;
            else if (s.charAt(i) == '-')
                sign = -1;
            else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Q0224_BasicCalculator sol = new Q0224_BasicCalculator();
        int result = sol.calculate("(10+(4+5+2)-3)-(6-8)");
    }
}
