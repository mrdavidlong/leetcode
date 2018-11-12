/**
 * https://leetcode.com/problems/target-sum/description/
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#0-1-背包
 */
public class Q0494_TargetSum_DFS_Best {
    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }

    private int findTargetSumWays(int[] nums, int start, int S) {
        if (start == nums.length) {
            return S == 0 ? 1 : 0;
        }
        return findTargetSumWays(nums, start + 1, S + nums[start])
                + findTargetSumWays(nums, start + 1, S - nums[start]);
    }

    public static void main(String[] args) {
        Q0494_TargetSum_DFS_Best sol = new Q0494_TargetSum_DFS_Best();
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        int ways = sol.findTargetSumWays(nums, S);
    }
}
