/**
 * https://leetcode.com/problems/jump-game/description/
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 Example 1:

 Input: [2,3,1,1,4]
 Output: true
 Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 Example 2:

 Input: [3,2,1,0,4]
 Output: false
 Explanation: You will always arrive at index 3 no matter what. Its maximum
 jump length is 0, which makes it impossible to reach the last index.

 *
 */

enum Index {
    GOOD, BAD, UNKNOWN
}

public class Q0055_JumpGame {

    // https://leetcode.com/problems/jump-game/solution/
    // solution 1: recursion
    public boolean canJumpFromPositionSol1(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        // New
        //for (int nextPosition = furthestJump; nextPosition > position; nextPosition--)
        // old
        // for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
        for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
            if (canJumpFromPositionSol1(nextPosition, nums)) {
                return true;
            }
        }

        return false;
    }

    public boolean canJumpSol1(int[] nums) {
        return canJumpFromPositionSol1(0, nums);
    }

    // solution 2: top down memo
    Index[] memo;

    public boolean canJumpFromPositionSol2(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPositionSol2(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJumpSol2(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPositionSol2(0, nums);
    }

    // solution 3: bottom up dp
    public boolean canJumpSol3(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    // solution 4: greedy
    public boolean canJumpSol4(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {
        Q0055_JumpGame sol = new Q0055_JumpGame();
        //boolean canJump1 = sol.canJumpSol1(new int[]{1, 5, 2, 1, 0, 2, 0});
        boolean canJump1 = sol.canJumpSol1(new int[]{2, 4, 2, 1, 0, 2, 0});
        boolean canJump2 = sol.canJumpSol2(new int[]{2, 4, 2, 1, 0, 2, 0});
        boolean canJump3 = sol.canJumpSol3(new int[]{2, 4, 2, 1, 0, 2, 0});
        boolean canJump4 = sol.canJumpSol4(new int[]{2, 4, 2, 1, 0, 2, 0});

        boolean canJump4b = sol.canJumpSol4(new int[]{1, 0, 2, 1, 0, 2, 0});
    }
}
