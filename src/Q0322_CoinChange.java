import java.util.Arrays;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#0-1-背包
 * https://leetcode.com/problems/coin-change/description/
 *
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:

 Input: coins = [1, 2, 5], amount = 11
 Output: 3
 Explanation: 11 = 5 + 5 + 1
 Example 2:

 Input: coins = [2], amount = 3
 Output: -1
 */
public class Q0322_CoinChange {
    public int coinChangeBF(int[] coins, int amount) {
        return coinChangeBF(0, coins, amount);
    }

    private int coinChangeBF(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount/coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChangeBF(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
    }


    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        // minimum[i], the minimum number of coins needed to form the sum i, which is the target amount
        int[] minimum = new int[amount + 1];
        // fill all the values with one above the target amount, so that we return -1 at the end if value is not changed,
        // i.e. return  minimum[amount] > amount ? -1 : minimum[amount];
        Arrays.fill(minimum, amount + 1);
        minimum[0] = 0;
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length && coins[j] <= i; j++) {
                minimum[i] = Math.min(minimum[i], minimum[i - coins[j]] + 1);
            }
        }
        return minimum[amount] > amount ? -1 : minimum[amount];
    }

    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        // minimum[i], the minimum number of coins needed to form the sum i, which is the target amount
        int[] minimum = new int[amount + 1];
        // fill all the values with (Integer.MAX_VALUE - 1), so that we return -1 at the end if value is not changed,
        // i.e. return  minimum[amount] == (Integer.MAX_VALUE - 1) ? -1 : minimum[amount];
        // we use Integer.MAX_VALUE - 1, because we want to avoid overflow when doing minimum[i - coin] + 1
        Arrays.fill(minimum, Integer.MAX_VALUE - 1);
        minimum[0] = 0;
        Arrays.sort(coins);
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                minimum[i] = Math.min(minimum[i], minimum[i - coin] + 1);
            }
        }
        return minimum[amount] == (Integer.MAX_VALUE - 1) ? -1 : minimum[amount];
    }

    public static void main(String[] args) {
        Q0322_CoinChange sol = new Q0322_CoinChange();
        int[] a = {1,2,5};
        int numOfCoins = sol.coinChange(a, 11);
        int numOfCoinsb = sol.coinChange2(a, 11);
        int[] a2 = {2};
        int numOfCoins2 = sol.coinChange(a2, 3);
        int numOfCoins2b = sol.coinChange2(a2, 3);
        int[] a3 = {1,2};
        int numOfCoins3 = sol.coinChange(a3, 5);
        int numOfCoins3b = sol.coinChange2(a3, 5);

        int[] a4 = {1,2};
        int numOfCoins4 = sol.coinChangeBF(a4, 5);
    }
}
