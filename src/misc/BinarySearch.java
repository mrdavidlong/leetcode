package misc;

/**
 * Created by davidlong on 7/8/18.
 */
public class BinarySearch {

    public int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    // returning l, which is the left most instance
    public int binarySearch2(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= key) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        BinarySearch sol = new BinarySearch();

        int[] nums = {1,1,2,2,2,3,3};
        int key = 2;

        int foundIndex = sol.binarySearch2(nums, key);

    }
}
