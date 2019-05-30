import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/strobogrammatic-number-ii/
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 */
public class Q0247_StrobogrammaticNumberII {
    // https://leetcode.com/problems/strobogrammatic-number-ii/discuss/67280/AC-clean-Java-solution
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

        List<String> list = helper(n - 2, m);

        List<String> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }

    public static void main(String[] args) {
        Q0247_StrobogrammaticNumberII sol = new Q0247_StrobogrammaticNumberII();
        List<String> list2 = sol.findStrobogrammatic(2);
        List<String> list3 = sol.findStrobogrammatic(3);
        List<String> list4 = sol.findStrobogrammatic(4);
    }
}
