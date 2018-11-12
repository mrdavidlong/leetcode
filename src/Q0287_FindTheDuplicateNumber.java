/**
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Example 1:

 Input: [1,3,4,2,2]
 Output: 2
 Example 2:

 Input: [3,1,3,4,2]
 Output: 3
 Note:

 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class Q0287_FindTheDuplicateNumber {

    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public int findDuplicate(int[] nums) {
        int l = 1, h = nums.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) cnt++;
            }
            if (cnt > mid) h = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public int findDuplicate2(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        Q0287_FindTheDuplicateNumber sol = new Q0287_FindTheDuplicateNumber();
        int dup = sol.findDuplicate(new int[] {1,3,4,2,2});
        int dup2 = sol.findDuplicate(new int[] {3,1,3,4,2});

        int dup3 = sol.findDuplicate2(new int[] {1,3,4,2,2});
        int dup4 = sol.findDuplicate2(new int[] {3,1,3,4,2});
    }
}
