import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/
 */
public class Q0300_LongestIncreasingSubsequenceBest_DPBinarySearch {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            // when Arrays.binarySearch returns a negative result, the result equals (-(insertion point) - 1)
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            // now i equals the insertion point in dp[]
            dp[i] = num;

            // if we add a num to the end of dp[] (instead of overriding an existing value), we increase the length
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    private int binarySearch(int[] tails, int len, int key) {
        int l = 0, h = len;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (tails[mid] == key) {
                return mid;
            } else if (tails[mid] > key) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.printf("%3d", dp[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Q0300_LongestIncreasingSubsequenceBest_DPBinarySearch sol = new Q0300_LongestIncreasingSubsequenceBest_DPBinarySearch();
        int length1 = sol.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18});
        //int length1 = sol.lengthOfLIS(new int[] {0, 8, 4, 12, 2});
    }
}
