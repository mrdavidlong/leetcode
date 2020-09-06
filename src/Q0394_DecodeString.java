import java.util.Stack;

/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
 */
public class Q0394_DecodeString {

//    public String decodeString(String s) {
//        StringBuilder sb = new StringBuilder();
//        char[] chars = s.toCharArray();
//        Stack<Character> stack = new Stack<>();
//        for  (char c : chars) {
//            if (c != ']') {
//                stack.push(c);
//            } else {
//                StringBuilder sbInner = new StringBuilder();
//                while (!stack.isEmpty() && stack.peek() != '[') {
//                    char sPopped = stack.pop();
//                    sbInner.insert(0, sPopped);
//                }
//                if (stack.peek() == '[') {
//                    stack.pop();
//                } else {
//                    throw new IllegalArgumentException();
//                }
//                sbInner.append(sb);
//                StringBuilder sbMultiplier = new StringBuilder();
//                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
//                    char sPopped = stack.pop();
//                    sbMultiplier.insert(0, sPopped);
//                }
//                if (sbMultiplier.length() == 0) {
//                    throw new IllegalArgumentException();
//                }
//
//                int multiplier = 0;
//                try {
//                    multiplier = Integer.parseInt(sbMultiplier.toString());
//                } catch (Exception e) {
//                    throw new IllegalArgumentException();
//                }
//
//                sb = new StringBuilder();
//                while (multiplier > 0) {
//                    sb.append(sbInner);
//                    multiplier--;
//                }
//                //sb.append(sbDecodeString);
//            }
//        }
//        while (!stack.isEmpty()) {
//            sb.insert(0,stack.pop());
//        }
//        return sb.toString();
//    }

    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q0394_DecodeString sol = new Q0394_DecodeString();
        //String s1 = "10[a]2[bc]";
        //String s1 = "abc";
        String s1 = "3[a2[c]]";
        String decodedString1 = sol.decodeString(s1);
        int i = 0;
    }
}
