import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
 *
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

 You may assume the array's length is at most 10,000.

 Example:

 Input:
 [1,2,3]

 Output:
 2

 Explanation:
 Only two moves are needed (remember each move increments or decrements one element):

 [1,2,3]  =>  [2,2,3]  =>  [2,2,2]

 https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#相遇问题
 */
public class Q0462_MinimumMovesToEqualArrayElementsII_Sort {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int move = 0;
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            move += nums[h] - nums[l];
            l++;
            h--;
        }
        return move;
    }

    public static void main(String[] args) {
        Q0462_MinimumMovesToEqualArrayElementsII_Sort sol = new Q0462_MinimumMovesToEqualArrayElementsII_Sort();
        int moves = sol.minMoves2(new int[] {1,2,3});
    }
}
