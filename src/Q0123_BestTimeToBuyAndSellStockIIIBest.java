/**
 * https://rafal.io/posts/leetcode-123-best-time-to-buy-and-sell-stock-iii.html
 */
public class Q0123_BestTimeToBuyAndSellStockIIIBest {
    public int maxProfit(int[] prices) {
        int N = prices.length;
        if(N == 0) return 0;

        int[] M1 = new int[N];
        int[] M2 = new int[N];

        // No profit at the very beginning
        M1[0] = 0;
        M2[N-1] = 0;

        int minPrice = prices[0];
        int maxProfit1 = 0;

        for(int i = 1; i < N; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit1 = Math.max(maxProfit1, prices[i]-minPrice);
            M1[i] = maxProfit1;
        }

        int maxPrice = prices[N-1];
        int maxProfit2 = 0;

        for(int i = N-2; i >= 0; i--){
            maxPrice = Math.max(maxPrice, prices[i]);
            maxProfit2 = Math.max(maxProfit2, maxPrice-prices[i]);
            M2[i] = maxProfit2;
        }

        int best = 0;

        for(int i = 0; i < N; i++){
            best = Math.max(best, M1[i]+M2[i]);
        }

        return best;
    }

    public static void main(String[] args) {
        Q0123_BestTimeToBuyAndSellStockIIIBest sol = new Q0123_BestTimeToBuyAndSellStockIIIBest();
        int[] prices = {3,3,5,0,0,3,1,4};
        int maxProfit = sol.maxProfit(prices);
    }

}
