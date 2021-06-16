import java.util.*;

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
//    public String simplifyPath(String path) {
//        Deque<String> stack = new LinkedList<>();
//        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
//        for (String dir : path.split("/")) {
//            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
//            else if (!skip.contains(dir)) stack.push(dir);
//        }
//        String res = "";
//        for (String dir : stack) {
//            res = "/" + dir + res;
//        }
//        return res.isEmpty() ? "/" : res;
//    }

    //https://leetcode.com/problems/simplify-path/solution/
    public String simplifyPath(String path) {

        // Initialize a stack
        Stack<String> stack = new Stack<String>();
        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {

            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..")) {

                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {

                // Finally, a legitimate directory name, so we add it
                // to our stack
                stack.add(directory);
            }
        }

        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/" ;
    }

    public static void main(String[] args) {
        Q0071_SimplifyPath sol = new Q0071_SimplifyPath();

        String path = "/home/"; //, => "/home"
        String simplifiedPath = sol.simplifyPath(path);
        String path1 = "/a/b/c/d"; //, => "/home"
        String simplifiedPath1 = sol.simplifyPath(path1);
        String path2 = "/a/./b/../../c/"; //, => "/c"
        String simplifiedPath2 = sol.simplifyPath(path2);
        String path3 = "/a/../../b/../c//.//"; //, => "/c"
        String simplifiedPath3 = sol.simplifyPath(path3);
        String path4 = "/a//b////c/d//././/.."; //, => "/a/b/c"
        String simplifiedPath4 = sol.simplifyPath(path4);

    }
}
