/**
 * https://leetcode.com/problems/ones-and-zeroes/description/
 *
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

 For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

 Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

 Note:
 The given numbers of 0s and 1s will both not exceed 100
 The size of given string array won't exceed 600.
 Example 1:
 Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 Output: 4

 Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 Example 2:
 Input: Array = {"10", "0", "1"}, m = 1, n = 1
 Output: 2

 Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#0-1-背包
 */
public class Q0474_OnesAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        // dp[i][j] is the max number of strings can be formed from strs when we have i number of zeros and j number of ones
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {    // 每个字符串只能用一次
            int ones = 0, zeros = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Q0474_OnesAndZeros sol = new Q0474_OnesAndZeros();
        //Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
        int maxWays1 = sol.findMaxForm(new String[] {"10", "0001", "111001", "1", "0"}, 5, 3);

        //Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
        int maxWays2 = sol.findMaxForm(new String[] {"10", "0", "1"}, 1, 1);
    }
}
