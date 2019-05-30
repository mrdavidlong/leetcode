/**
 * https://leetcode.com/problems/wildcard-matching/
 */
public class Q0044_WildcardMatching {
    // https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
    public boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    public static void main(String[] args) {
        Q0044_WildcardMatching sol = new Q0044_WildcardMatching();

        boolean compare = sol.comparison("abcdefg", "a*de?g"); // true
        boolean compare1_1 = sol.comparison("abcdefg", "a*d*g"); // true

        boolean compare1 = sol.comparison("aa", "a"); // false
        boolean compare2 = sol.comparison("aa", "*"); // true
        boolean compare3 = sol.comparison("cb", "?"); // false
        boolean compare4 = sol.comparison("adceb", "*a*b"); // true
        boolean compare5 = sol.comparison("acdcb", "a*c?b"); // false
    }
}
