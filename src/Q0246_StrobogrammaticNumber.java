import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/strobogrammatic-number/
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
 */
public class Q0246_StrobogrammaticNumber {
    // https://leetcode.com/problems/strobogrammatic-number/discuss/67182/Accepted-Java-solution
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) return false;
            if (map.get(num.charAt(l)) != num.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }

    public static void main(String[] args) {
        Q0246_StrobogrammaticNumber sol = new Q0246_StrobogrammaticNumber();
        boolean isStrobo = sol.isStrobogrammatic("69");
        boolean isStrobo2 = sol.isStrobogrammatic("88");
        boolean isStrobo3 = sol.isStrobogrammatic("962");
    }
}

