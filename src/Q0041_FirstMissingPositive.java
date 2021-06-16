/**
 * https://leetcode.com/problems/first-missing-positive/
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

    // OfficialSolution
    public int firstMissingPositiveOfficialSolution(int[] nums) {
        int n = nums.length;

        // Base case.
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }

        if (contains == 0)
            return 1;

        // Replace negative numbers, zeros,
        // and numbers larger than n by 1s.
        // After this conversion nums will contain
        // only positive numbers.
        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n))
                nums[i] = 1;

        // Use index as a hash key and number sign as a presence detector.
        // For example, if nums[1] is negative that means that number `1`
        // is present in the array.
        // If nums[2] is positive - number 2 is missing.
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // If you meet number a in the array - change the sign of a-th element.
            // Be careful with duplicates : do it only once.
            if (a == n)
                nums[0] = - Math.abs(nums[0]);
            else
                nums[a] = - Math.abs(nums[a]);
        }

        // Now the index of the first positive number
        // is equal to first missing positive.
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }

    public static void main(String[] args) {
        Q0041_FirstMissingPositive sol = new Q0041_FirstMissingPositive();

        int[] nums1 = {1,2,0};
        int output1 = sol.firstMissingPositiveOfficialSolution(nums1); // 3

        int[] nums2 = {3,4,-1,1};
        int output2 = sol.firstMissingPositiveOfficialSolution(nums2); // 2

        int[] nums3 = {7,8,9,11,12};
        int output3 = sol.firstMissingPositiveOfficialSolution(nums3); // 1

    }
}
