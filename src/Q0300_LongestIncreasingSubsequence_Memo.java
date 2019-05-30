import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/
 *
 *
 */
public class Q0300_LongestIncreasingSubsequence_Memo {
    public int lengthOfLIS(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        int length = lengthOfLIS(nums, -1, 0, memo);
        print(memo);
        return length;
    }
    public int lengthOfLIS(int[] nums, int prevIndex, int curPos, int[][] memo) {
        if (curPos == nums.length) {
            return 0;
        }
        if (memo[prevIndex + 1][curPos] >= 0) {
            return memo[prevIndex + 1][curPos];
        }
        int taken = 0;
        if (prevIndex < 0 || nums[curPos] > nums[prevIndex]) {
            taken = 1 + lengthOfLIS(nums, curPos, curPos + 1, memo);
        }

        int nottaken = lengthOfLIS(nums, prevIndex, curPos + 1, memo);
        memo[prevIndex + 1][curPos] = Math.max(taken, nottaken);
        return memo[prevIndex + 1][curPos];
    }

    private static void print(int[][] memo) {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                System.out.printf("%3d", memo[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Q0300_LongestIncreasingSubsequence_Memo sol = new Q0300_LongestIncreasingSubsequence_Memo();
        int length1 = sol.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18});
    }
}
