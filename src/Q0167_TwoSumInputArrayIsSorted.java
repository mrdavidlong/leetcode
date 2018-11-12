/**
 * Created by davidlong on 7/5/18.
 */
public class Q0167_TwoSumInputArrayIsSorted {


    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#双指针
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Q0167_TwoSumInputArrayIsSorted sol = new Q0167_TwoSumInputArrayIsSorted();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] twoNums = sol.twoSum(nums, target);
    }
}
