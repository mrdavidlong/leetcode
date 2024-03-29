/**
 * https://leetcode.com/problems/search-insert-position/description/
 *
 *
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Example 1:

 Input: [1,3,5,6], 5
 Output: 2
 Example 2:

 Input: [1,3,5,6], 2
 Output: 1
 Example 3:

 Input: [1,3,5,6], 7
 Output: 4
 Example 4:

 Input: [1,3,5,6], 0
 Output: 0

 */
public class Q0035_SearchInsertPosition {
    //https://leetcode.com/problems/search-insert-position/solution/
    public int searchInsert(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) return pivot;
            if (target < nums[pivot]) right = pivot - 1;
            else left = pivot + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        Q0035_SearchInsertPosition sol = new Q0035_SearchInsertPosition();
        int insertPos = sol.searchInsert(new int[] {1,3,5,6}, 5);
        int insertPos2 = sol.searchInsert(new int[] {1,3,5,6}, 2);
        int insertPos3 = sol.searchInsert(new int[] {1,3,5,6}, 7);
        int insertPos4 = sol.searchInsert(new int[] {1,3,5,6}, 0);

    }
}
