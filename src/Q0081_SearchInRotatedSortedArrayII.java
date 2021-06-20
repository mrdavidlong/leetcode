/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

 You are given a target value to search. If found in the array return true, otherwise return false.

 Example 1:

 Input: nums = [2,5,6,0,0,1,2], target = 0
 Output: true
 Example 2:

 Input: nums = [2,5,6,0,0,1,2], target = 3
 Output: false
 Follow up:

 This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 Would this affect the run-time complexity? How and why?
 */
public class Q0081_SearchInRotatedSortedArrayII {
    // https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28202/Neat-JAVA-solution-using-binary-search
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[start] < nums[mid] || nums[mid] > nums[end]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, here we just use end-- but start++ works too
            } else {
                end--;
            }
        }

        return false;
    }

    // too confusing:
    //https://leetcode.com/problems/search-in-rotated-sorted-array-ii/solution/
//    public boolean search(int[] nums, int target) {
//        int n = nums.length;
//        if (n == 0) return false;
//        int end = n - 1;
//        int start = 0;
//
//        while (start <= end) {
//            int mid = start + (end - start) / 2;
//
//            if (nums[mid] == target) {
//                return true;
//            }
//
//            if (!isBinarySearchHelpful(nums, start, nums[mid])) {
//                start++;
//                continue;
//            }
//            // which array does pivot belong to.
//            boolean pivotArray = existsInFirst(nums, start, nums[mid]);
//
//            // which array does target belong to.
//            boolean targetArray = existsInFirst(nums, start, target);
//            if (pivotArray ^ targetArray) { // If pivot and target exist in different sorted arrays, recall that xor is true when both operands are distinct
//                if (pivotArray) {
//                    start = mid + 1; // pivot in the first, target in the second
//                } else {
//                    end = mid - 1; // target in the first, pivot in the second
//                }
//            } else { // If pivot and target exist in same sorted array
//                if (nums[mid] < target) {
//                    start = mid + 1;
//                } else {
//                    end = mid - 1;
//                }
//            }
//        }
//        return false;
//    }
//
//    // returns true if we can reduce the search space in current binary search space
//    private boolean isBinarySearchHelpful(int[] arr, int start, int element) {
//        return arr[start] != element;
//    }
//
//    // returns true if element exists in first array, false if it exists in second
//    private boolean existsInFirst(int[] arr, int start, int element) {
//        return arr[start] <= element;
//    }

    public static void main(String[] args) {
        Q0081_SearchInRotatedSortedArrayII sol = new Q0081_SearchInRotatedSortedArrayII();
        boolean found = sol.search(new int[] {2,5,6,0,0,1,2}, 2);
        boolean found2 = sol.search(new int[] {2,5,6,0,0,1,2}, 5);
        boolean found3 = sol.search(new int[] {2,5,6,0,0,1,2}, 1);
        boolean found4 = sol.search(new int[] {2,5,6,0,0,1,2}, 3);
        boolean found5 = sol.search(new int[] {2,5,6,0,0,1,2}, 0);
    }
}
