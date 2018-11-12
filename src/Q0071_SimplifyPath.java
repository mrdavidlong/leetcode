import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * https://leetcode.com/problems/simplify-path/description/
 *
 *Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 path = "/a/../../b/../c//.//", => "/c"
 path = "/a//b////c/d//././/..", => "/a/b/c"

 In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored in a simplified path. Additionally, a double period ("..") moves up a directory, so it cancels out whatever the last directory was. For more information, look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style

 Corner Cases:

 Did you consider the case where path = "/../"?
 In this case, you should return "/".
 Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class Q0071_SimplifyPath {
    // https://leetcode.com/problems/simplify-path/discuss/25686/Java-10-lines-solution-with-stack
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        for (String dir : stack) {
            res = "/" + dir + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    public static void main(String[] args) {
        Q0071_SimplifyPath sol = new Q0071_SimplifyPath();

        String path = "/home/"; //, => "/home"
        String simplifiedPath = sol.simplifyPath(path);
        String path2 = "/a/./b/../../c/"; //, => "/c"
        String simplifiedPath2 = sol.simplifyPath(path2);
        String path3 = "/a/../../b/../c//.//"; //, => "/c"
        String simplifiedPath3 = sol.simplifyPath(path3);
        String path4 = "/a//b////c/d//././/.."; //, => "/a/b/c"
        String simplifiedPath4 = sol.simplifyPath(path4);

    }
}
