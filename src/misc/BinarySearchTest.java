package misc;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#二分查找
 */
public class BinarySearchTest {

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

    public int binarySearchLeftMost(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= key) {
                h = m;
            } else {
                l = m + 1;
            }
        }

        if (nums[l] != key) {
            return -1;
        } else {
            return l;
        }
    }

    public static void main(String[] args) {
        int[] a = {0,0,0,1,1,1,3,3,3};

        BinarySearchTest sol = new BinarySearchTest();
        int index = sol.binarySearchLeftMost(a, 2);
    }
}
