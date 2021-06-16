/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 *
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example 1:

 Given nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

 It doesn't matter what you leave beyond the returned length.
 Example 2:

 Given nums = [0,0,1,1,1,1,2,3,3],

 Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

 It doesn't matter what values are set beyond the returned length.
 Clarification:

 Confused why the returned value is an integer but your answer is an array?

 Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

 Internally you can think of this:

 // nums is passed in by reference. (i.e., without making a copy)
 int len = removeDuplicates(nums);

 // any modification to nums in your function would be known by the caller.
 // using the length returned by your function, it prints the first len elements.
 for (int i = 0; i < len; i++) {
 print(nums[i]);
 }
 */
public class Q0080_RemoveDuplicatesFromSortedArrayII {
//    public int removeDuplicates(int[] nums) {
//        if (nums == null) throw new IllegalArgumentException("nums cannot be null");
//        if (nums.length <= 2) return nums.length;
//
//        int j = 2;
//        boolean moreThanTwice = false;
//        for (int i = 2; i < nums.length; i++) {
//            if (nums[i] == nums[i-1] && nums[i-1] == nums[i-2]) {
//                moreThanTwice = true;
//            } else {
//                moreThanTwice = false;
//            }
//
//            if (j != i) {
//                nums[j] = nums[i];
//            }
//
//            if (!moreThanTwice) {
//                j++;
//            }
//        }
//        return j;
//    }
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27987/Short-and-Simple-Java-solution-(easy-to-understand)
    public int removeDuplicates1(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }

    public int removeDuplicatesNoDupes2(int[] nums) {
        int i = 0;
        for(int n : nums)
            if(i < 1 || n > nums[i - 1])
                nums[i++] = n;
        return i;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/solution/
    public int removeDuplicates(int[] nums) {
        // Initialize the counter and the second pointer.
        int j = 1, count = 1;
        // Start from the second element of the array and process
        // elements one by one.
        for (int i = 1; i < nums.length; i++) {
            // If the current element is a duplicate, increment the count.
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                // Reset the count since we encountered a different element
                // than the previous one.
                count = 1;
            }
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
        Q0080_RemoveDuplicatesFromSortedArrayII sol = new Q0080_RemoveDuplicatesFromSortedArrayII();
        int[] intArray1 = new int[] {1,1,1,2,2,3};
        int len = sol.removeDuplicates(intArray1);
        int[] intArray2 = new int[] {0,0,1,1,1,1,2,3,3};
        int len2 = sol.removeDuplicates(intArray2);

        int[] intArray3 = new int[] {1,1,1,2,2,3};
        int len3 = sol.removeDuplicates(intArray3);
        int[] intArray4 = new int[] {0,0,1,1,1,1,2,3,3};
        int len4 = sol.removeDuplicates(intArray4);

    }
}
