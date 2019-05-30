import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]
 */
public class Q0448_FindAllNumbersDisappearedInAnArray {
    /*
    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
    The basic idea is that we iterate through the input array and mark elements as negative using nums[nums[i] -1] = -nums[nums[i]-1].
    In this way all the numbers that we have seen will be marked as negative. In the second iteration, if a value is not marked as negative,
    it implies we have never seen that index before, so just add it to the return list.
*/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }

    /*
        https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/93007/Simple-Java-In-place-sort-solution
        The idea is simple, if nums[i] != i + 1 and nums[i] != nums[nums[i] - 1], then we swap nums[i] with nums[nums[i] - 1], for example, nums[0] = 4 and nums[3] = 7, then we swap nums[0] with nums[3]. So In the end the array will be sorted and if nums[i] != i + 1, then i + 1 is missing.
        The example run as follows

                [4,3,2,7,8,2,3,1]
                [7,3,2,4,8,2,3,1]
                [3,3,2,4,8,2,7,1]
                [2,3,3,4,8,2,7,1]
                [3,2,3,4,8,2,7,1]
                [3,2,3,4,1,2,7,8]
                [1,2,3,4,3,2,7,8]
        Since every swap we put at least one number to its correct position, the time is O(n)
    */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] -1);
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        Q0448_FindAllNumbersDisappearedInAnArray sol = new Q0448_FindAllNumbersDisappearedInAnArray();
        List<Integer> disappearedNumbers = sol.findDisappearedNumbers(new int[] {4,3,2,7,8,2,3,1});
        List<Integer> disappearedNumbers2 = sol.findDisappearedNumbers2(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
    }
}
