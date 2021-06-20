/**
 * Created by davidlong on 7/5/18.
 */
public class Q0121_BestTimeToBuyAndSellStockWithOneTransaction {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
    public int maxProfitBF(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }

    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
//
//    public int maxProfit(int prices[]) {
//        int minPrice = Integer.MAX_VALUE;
//        int maxProfit = 0;
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < minPrice)
//                minPrice = prices[i];
//            else if (prices[i] - minPrice > maxProfit)
//                maxProfit = prices[i] - minPrice;
//        }
//        return maxProfit;
//    }
//
//    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#股票交易
//    public int maxProfit2(int[] prices) {
//        int n = prices.length;
//        if (n == 0) return 0;
//        int soFarMin = prices[0];
//        int max = 0;
//        for (int i = 1; i < n; i++) {
//            if (soFarMin > prices[i]) soFarMin = prices[i];
//            else max = Math.max(max, prices[i] - soFarMin);
//        }
//        return max;
//    }

    public static void main(String[] args) {
        Q0121_BestTimeToBuyAndSellStockWithOneTransaction sol = new Q0121_BestTimeToBuyAndSellStockWithOneTransaction();
//        Example 1:
//
//        Input: [7,1,5,3,6,4]
//        Output: 5
//        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//        Not 7-1 = 6, as selling price needs to be larger than buying price.
        int[] prices1 = {7,1,5,3,6,4};
        int profit1 = sol.maxProfit(prices1);


//                Example 2:
//
//        Input: [7,6,4,3,1]
//        Output: 0
//        Explanation: In this case, no transaction is done, i.e. max profit = 0.
        int[] prices2 = {7,6,4,3,1};
        int profit2 = sol.maxProfit(prices2);
    }
}
