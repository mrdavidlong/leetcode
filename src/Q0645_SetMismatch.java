/**
 * https://leetcode.com/problems/set-mismatch/description/
 *
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

 Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

 Example 1:

 Input: nums = [1,2,2,4]
 Output: [2,3]
 Note:

 The given array size will in the range [2, 10000].
 The given array's numbers won't have any order.
 */
public class Q0645_SetMismatch {
    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public int[] findErrorNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

//    public int[] findErrorNums2(int[] nums) {
//        for(int i = 0; i < nums.length; i++) {
//            int index = Math.abs(nums[i]) - 1;
//            if(nums[index] > 0) {
//                nums[index] = -nums[index];
//            }
//        }
//
//        for(int i = 0; i < nums.length; i++) {
//            if(nums[i] > 0) {
//                return new int[]{nums[i]-1, i + 1};
//            }
//        }
//        return null;
//    }

    public static int[] findErrorNums2(int[] nums) {
        int[] res = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) res[0] = Math.abs(i);
            else nums[Math.abs(i) - 1] *= -1;
        }
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) res[1] = i+1;
        }
        return res;
    }

    public static void main(String[] args) {
        Q0645_SetMismatch sol = new Q0645_SetMismatch();

        int[] errorNums = sol.findErrorNums(new int[] {1,2,2,4});
        int[] errorNums2 = sol.findErrorNums(new int[] {3,2,2});

        int[] errorNums3 = sol.findErrorNums2(new int[]{1, 2, 2, 4});
        int[] errorNums4 = sol.findErrorNums2(new int[]{3, 2, 2});
    }
}
