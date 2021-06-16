/**
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 *
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 Example:

 Input: [2,3,1,1,4]
 Output: 2
 Explanation: The minimum number of jumps to reach the last index is 2.
 Jump 1 step from index 0 to 1, then 3 steps to the last index.
 Note:

 You can assume that you can always reach the last index.
 */
public class Q0045_JumpGameII {
    //https://leetcode.com/problems/jump-game-ii/solution/
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        Q0045_JumpGameII sol = new Q0045_JumpGameII();

        int steps = sol.jump(new int[] {2,3,1,1,4});
        int stepsNoEnd = sol.jump(new int[] {1, 0, 2, 1, 0, 2, 0});

    }
}
