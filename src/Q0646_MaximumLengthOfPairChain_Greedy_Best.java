import java.util.Arrays;

/**
 *  https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
 */

public class Q0646_MaximumLengthOfPairChain_Greedy_Best {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair: pairs) {
            if (pair[0] > cur) {
                cur = pair[1];
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0646_MaximumLengthOfPairChain_Greedy_Best sol = new Q0646_MaximumLengthOfPairChain_Greedy_Best();
        int[][] pairs1 = {{1,2}, {3,4}, {2,3}};
        int maxLength1 = sol.findLongestChain(pairs1);

        int[][] pairs2 = {{1,2}, {2,3}, {6,7}, {1,3}, {3,4}};
        int maxLength2 = sol.findLongestChain(pairs2);
    }
}
