/**
 * Created by davidlong on 6/16/18.
 */
public class Q0005_LongestPalindromicSubstring {

    public static boolean isPalindrome(char[] a, int start, int end) {
        while (start <= end) {
            if (a[start] != a[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static String longestPalindrome(String s) {

        int maxLength = -1;
        int maxStart = -1;
        int maxEnd = -1;
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (isPalindrome(a, i, j)) {
                    int currentLength = j-i+1;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        maxStart = i;
                        maxEnd = j;
                    }
                }
            }
        }

        return s.substring(maxStart, maxEnd + 1);
    }

    public static String longestPalindrome_OfficialSolution(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start + 1)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        String input = "babad";
        String output = longestPalindrome(input);
        System.out.println("output = " + output);

        String outputBetter = longestPalindrome_OfficialSolution(input);
        System.out.println("outputBetter = " + outputBetter);
    }


}
