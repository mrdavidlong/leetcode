/*

https://leetcode.com/problems/find-the-closest-palindrome/

Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:

Input: "123"
Output: "121"
Note:

The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.

 */
public class Q0564_FindTheClosestPalindrome {
    public String nearestPalindromicBF(String n) {
        long num = Long.parseLong(n);
        for (long i = 1;; i++) {
            if (isPalindrome(num - i))
                return "" + (num - i);
            if (isPalindrome(num + i))
                return "" + (num + i);
        }
    }
    private boolean isPalindrome(long x) {
        long t = x, rev = 0;
        while (t > 0) {
            rev = 10 * rev + t % 10;
            t /= 10;
        }
        return rev == x;
    }

    public String mirroring(String s) {
        String x = s.substring(0, (s.length()) / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }
    public String nearestPalindromicUsingMath(String n) {
        if (n.equals("1"))
            return "0";

        String a = mirroring(n);
        long diff1 = Long.MAX_VALUE;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        if (diff1 == 0)
            diff1 = Long.MAX_VALUE;



        // change middle number, and look for smaller value
        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;

        // minus one to the first half
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i + 1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        } else {
            s.replace(i, i + 1, "" + (char) (s.charAt(i) - 1));
        }

        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));



        // change middle number, and look for larger value
        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;

        // add one to the first half
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }
        if (i < 0) {
            s.insert(0, "1");
        } else {
            s.replace(i, i + 1, "" + (char) (s.charAt(i) + 1));
        }

        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3)
            return b;
        if (diff1 <= diff3 && diff1 <= diff2)
            return a;
        else
            return c;
    }

    public static void main(String[] args) {
        Q0564_FindTheClosestPalindrome sol = new Q0564_FindTheClosestPalindrome();
        //boolean isPal = sol.isPalindrome(121);
        //String nearestPal = sol.nearestPalindromicBF("123");
        String nearestPal = sol.nearestPalindromicUsingMath("123");
        String nearestPal2 = sol.nearestPalindromicUsingMath("20001");
        String nearestPal2b = sol.nearestPalindromicUsingMath("10001");
        String nearestPal3 = sol.nearestPalindromicUsingMath("10987");

    }
}
