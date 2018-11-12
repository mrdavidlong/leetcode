import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/description/
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#分治
 *
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

 Example 1:

 Input: "2-1-1"
 Output: [0, 2]
 Explanation:
 ((2-1)-1) = 0
 (2-(1-1)) = 2
 Example 2:

 Input: "2*3-4*5"
 Output: [-34, -14, -10, -10, 10]
 Explanation:
 (2*(3-(4*5))) = -34
 ((2*3)-(4*5)) = -14
 ((2*(3-4))*5) = -10
 (2*((3-4)*5)) = -10
 (((2*3)-4)*5) = 10

 */
class Q0241_DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    public static void main(String[] args) {
        Q0241_DifferentWaysToAddParentheses sol = new Q0241_DifferentWaysToAddParentheses();

//        Example 1:
//        Input: "2-1-1"
//        Output: [0, 2]
//        Explanation:
//        ((2-1)-1) = 0
//        (2-(1-1)) = 2
        String input1 = "2-1-1";
        List<Integer> result1 = sol.diffWaysToCompute(input1);

//        Example 2:
//        Input: "2*3-4*5"
//        Output: [-34, -14, -10, -10, 10]
//        Explanation:
//        (2*(3-(4*5))) = -34
//        ((2*3)-(4*5)) = -14
//        ((2*(3-4))*5) = -10
//        (2*((3-4)*5)) = -10
//        (((2*3)-4)*5) = 10

        String input2 = "2*3-4*5";
        List<Integer> result2 = sol.diffWaysToCompute(input2);

    }
}