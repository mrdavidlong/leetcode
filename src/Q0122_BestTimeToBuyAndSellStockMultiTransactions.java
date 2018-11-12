/**
 * Created by davidlong on 7/5/18.
 */
public class Q0122_BestTimeToBuyAndSellStockMultiTransactions {

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }
//    Example 1:
//
//    Input: [7,1,5,3,6,4]
//    Output: 7
//    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//    Example 2:
//
//    Input: [1,2,3,4,5]
//    Output: 4
//    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//    Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
//    engaging multiple transactions at the same time. You must sell before buying again.

//

    public static void main(String[] args) {
        Q0122_BestTimeToBuyAndSellStockMultiTransactions sol = new Q0122_BestTimeToBuyAndSellStockMultiTransactions();

        //    Input: [7,1,5,3,6,4]
        //    Output: 7
        //    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
        //    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
        int[] prices1 = {7,1,5,3,6,4};
        int profit1 = sol.maxProfit(prices1);

        //    Input: [1,2,3,4,5]
        //    Output: 4
        //    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
        //    Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
        //    engaging multiple transactions at the same time. You must sell before buying again.
        int[] prices2 = {1,2,3,4,5};
        int profit2 = sol.maxProfit(prices2);

        //    Input: [7,6,4,3,1]
        //    Output: 0
        //    Explanation: In this case, no transaction is done, i.e. max profit = 0.
        int[] prices3 = {7,6,4,3,1};
        int profit3 = sol.maxProfit(prices3); // 0
    }
}
