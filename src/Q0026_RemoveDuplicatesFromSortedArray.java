/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class Q0026_RemoveDuplicatesFromSortedArray {
    public int removeDuplicatesByDavid(int[] nums) {
        // return 0 when it's empty;
        if (nums == null || nums.length == 0) return 0;

        int lastUniqueNum = nums[0];
        int lastUniquePos = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != lastUniqueNum) {
                lastUniqueNum = nums[i];
                nums[lastUniquePos++] = nums[i];
            }
        }
        return lastUniquePos+1;
    }

    public int removeDuplicates(int[] nums) {
        // return 0 when it's empty;
        if (nums == null || nums.length == 0) return 0;

        int lastUniqueNum = nums[0];
        int nextUniquePos = 1;
        for (int n : nums) {
            if (n > lastUniqueNum) {
                lastUniqueNum = n;
                nums[nextUniquePos++] = n;
            }
        }
        return nextUniquePos;
    }

//    public int removeDuplicates(int[] nums) {
//        int i = nums.length > 0 ? 1 : 0;
//        for (int n : nums)
//            if (n > nums[i-1])
//                nums[i++] = n;
//        return i;
//    }

    private static void print(int[] nums) {
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        Q0026_RemoveDuplicatesFromSortedArray q = new Q0026_RemoveDuplicatesFromSortedArray();
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        int len = q.removeDuplicates(nums);
        print(nums);
//        for (int i = 0; i < len; i++) {
//            print(nums[i]);
//        }
    }

}
