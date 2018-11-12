/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#股票交易
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 Example:

 Input: [1,2,3,0,2]
 Output: 3
 Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class Q0309_BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] s1 = new int[N]; // rest period after buy
        int[] sell = new int[N];
        int[] s2 = new int[N]; // rest period after sell
        s1[0] = buy[0] = -prices[0];
        sell[0] = s2[0] = 0;
        for (int i = 1; i < N; i++) {
            // you need to skip a day before you buy after a sale
            buy[i] = s2[i - 1] - prices[i];
            s1[i] = Math.max(buy[i - 1], s1[i - 1]);
            // you can sell the next day after you buy
            sell[i] = Math.max(buy[i - 1], s1[i - 1]) + prices[i];
            s2[i] = Math.max(s2[i - 1], sell[i - 1]);
        }
        return Math.max(sell[N - 1], s2[N - 1]);
    }

    public int maxProfit2(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }

    public static void main(String[] args) {
//        Input: [1,2,3,0,2]
//        Output: 3
//        Explanation: transactions = [buy, sell, cooldown, buy, sell]
        Q0309_BestTimeToBuyAndSellStockWithCooldown sol = new Q0309_BestTimeToBuyAndSellStockWithCooldown();

        int[] prices = {1,2,3,0,2};
        int maxProfit = sol.maxProfit(prices);

    }
}
