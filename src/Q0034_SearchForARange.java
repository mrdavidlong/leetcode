/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 */
public class Q0034_SearchForARange {

    private int[] expandSameNumber(int[] nums, int target, int index) {
        if (nums[index] != target) throw new IllegalArgumentException("index is not pointing the the target in nums");

        int left = index;
        int right = index;

        while (nums[left] == target && left > 0) left--;
        if (nums[left] != target) left++;

        while (nums[right] == target && right < nums.length - 1) right++;
        if (nums[right] != target) right--;

        return new int[] {left, right};
    }

    public int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) {
                return expandSameNumber(nums, target, mid);
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }

        return new int[] {-1, -1};
    }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo+hi)/2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange_OfficialSolution(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

    public int[] searchRange2(int[] nums, int target) {
        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }

    // find the left most
    private int binarySearch(int[] nums, int target) {
        int l = 0, h = nums.length; // 注意 h 的初始值
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Q0034_SearchForARange sol = new Q0034_SearchForARange();
        
        int[] nums1 = new int[] {5,7,7,8,8,10};
        int target1 = 8;
        int[] ranges1 = sol.searchRange(nums1, target1);

        int[] nums2 = new int[] {5,7,7,8,8,10};
        int target2 = 5;
        int[] ranges2 = sol.searchRange(nums2, target2);
    }

}
