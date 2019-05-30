/**
 * Created by davidlong on 7/2/18.
 */
public class Q0033_SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;

        while (left <= right) {
            mid = left + (right - left)/2;
            if (target == nums[mid]) return mid;
            if (left == right) return -1;

            // left side is in normal order
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] <= nums[right]) { // right side is in normal order
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                throw new IllegalArgumentException("The array is not rotated sorted or there are duplicates");
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q0033_SearchInRotatedSortedArray sol = new Q0033_SearchInRotatedSortedArray();

        int[] nums1 = new int[] {4,5,6,7,0,1,2};
        int target1 = 0;
        int index1 = sol.search(nums1, target1);

        int[] nums2 = new int[] {3,1};
        int target2 = 1;
        int index2 = sol.search(nums2, target2);


        int[] nums3 = new int[] {5,1,3,};
        int target3 = 5;
        int index3 = sol.search(nums3, target3);
    }
}
