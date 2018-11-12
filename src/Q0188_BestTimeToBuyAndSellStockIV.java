/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 *
 *
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Example 1:

 Input: [2,4,1], k = 2
 Output: 2
 Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 Example 2:

 Input: [3,2,6,5,0,3], k = 2
 Output: 7
 Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

 * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 * dp[0, j] = 0; 0 transactions makes 0 profit
 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.

 *
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#股票交易
 */
public class Q0188_BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) {   // 这种情况下该问题退化为普通的股票交易问题
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        int[][] maxProfit = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = maxProfit[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                maxProfit[i][j] = Math.max(maxProfit[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, maxProfit[i - 1][j] - prices[j]);
            }
        }
        return maxProfit[k][n - 1];
    }

    public static void main(String[] args) {
        Q0188_BestTimeToBuyAndSellStockIV sol = new Q0188_BestTimeToBuyAndSellStockIV();
        int maxProfit1 = sol.maxProfit(2, new int[] {2,4,1});
        int maxProfit2 = sol.maxProfit(2, new int[] {3,2,6,5,0,3});

    }
}
