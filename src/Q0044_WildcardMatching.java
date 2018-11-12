/**
 * Created by davidlong on 7/3/18.
 */
public class Q0044_WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        boolean firstMatch = (s.length() > 0) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?');

        boolean match = false;
        if (p.charAt(0) == '*') {
            if (p.length() > 1 && p.charAt(1) == '*') return isMatch(s, p.substring(1));
            
            match = ((p.length() > 0) && isMatch(s, p.substring(1)))
                    || ((s.length() > 0) && isMatch(s.substring(1), p));
        } else {
            match = firstMatch && isMatch(s.substring(1), p.substring(1));
        }

        return match;
    }

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
//        boolean match1 = sol.isMatch("aa", "a"); // false
//        boolean match2 = sol.isMatch("aa", "*"); // true
//        boolean match3 = sol.isMatch("cb", "?"); // false
//        boolean match4 = sol.isMatch("adceb", "*a*b"); // true
//        boolean match5 = sol.isMatch("acdcb", "a*c?b"); // false

        boolean compare = sol.comparison("abcdefg", "a*de?g"); // true

        boolean compare1 = sol.comparison("aa", "a"); // false
        boolean compare2 = sol.comparison("aa", "*"); // true
        boolean compare3 = sol.comparison("cb", "?"); // false
        boolean compare4 = sol.comparison("adceb", "*a*b"); // true
        boolean compare5 = sol.comparison("acdcb", "a*c?b"); // false
    }
}
