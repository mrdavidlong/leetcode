import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/remove-comments/

 */
public class Q0722_RemoveComments {
    // https://leetcode.com/problems/remove-comments/solution/
    /*
    Intuition and Algorithm

We need to parse the source line by line. Our state is that we either are in a block comment or not.

If we start a block comment and we aren't in a block, then we will skip over the next two characters and change our state to be in a block.

If we end a block comment and we are in a block, then we will skip over the next two characters and change our state to be not in a block.

If we start a line comment and we aren't in a block, then we will ignore the rest of the line.

If we aren't in a block comment (and it wasn't the start of a comment), we will record the character we are at.

At the end of each line, if we aren't in a block, we will record the line.
     */
    public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        List<String> ans = new ArrayList();
        for (String line: source) {
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) newline = new StringBuilder();
            while (i < line.length()) {
                if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock && i+1 < line.length() && chars[i] == '*' && chars[i+1] == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '/') {
                    break;
                } else if (!inBlock) {
                    newline.append(chars[i]);
                }
                i++;
            }
            if (!inBlock && newline.length() > 0) {
                ans.add(new String(newline));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0722_RemoveComments sol = new Q0722_RemoveComments();

        String[] source1 = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        List<String> result1 = sol.removeComments(source1);

        String[] source2 = {"a/*comment", "line", "more_comment*/b"};
        List<String> result2 = sol.removeComments(source2);
    }
}
