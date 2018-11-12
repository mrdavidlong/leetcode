/**
 * Created by davidlong on 7/2/18.
 */
public class Q0041_FirstMissingPositive {

    // int[] nums2 = {3,4,-1,1};
    // int[] nums2 = {-1,4,3,1}; int i = 0;
    // int[] nums2 = {-1,1,3,4}; int i = 1;
    // int[] nums2 = {1,-1,3,4}; int i = 1;
    // int[] nums2 = {1,-1,3,4}; int i = 2;
    // int[] nums2 = {1,-1,3,4}; int i = 4;
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length ) {
            if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length) {
                i++;
            } else if (nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i]-1); // continuously place nums[i] at the correct index
            } else {
                i++;
            }
        }

        i = 0;
        while (i < nums.length && nums[i] == i + 1) {
            i++;
        }
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Q0041_FirstMissingPositive sol = new Q0041_FirstMissingPositive();

        int[] nums1 = {1,2,0};
        int output1 = sol.firstMissingPositive(nums1); // 3

        int[] nums2 = {3,4,-1,1};
        int output2 = sol.firstMissingPositive(nums2); // 2

        int[] nums3 = {7,8,9,11,12};
        int output3 = sol.firstMissingPositive(nums3); // 1

    }
}
