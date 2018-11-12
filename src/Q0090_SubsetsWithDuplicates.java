import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by davidlong on 7/4/18.
 */
public class Q0090_SubsetsWithDuplicates {

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        for (int size = 0; size <= nums.length; size++) {
            backtracking(nums, size, 0, result, new ArrayList<>(), used); // all possible sizes
        }
        return result;
    }

    private void backtracking(final int[] nums, final int size, int start, List<List<Integer>> result, List<Integer> tempList, boolean[] used) {

        if (tempList.size() == size) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;

            used[i] = true;
            tempList.add(nums[i]);

            backtracking(nums, size, i + 1, result, tempList, used);

            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q0090_SubsetsWithDuplicates sol = new Q0090_SubsetsWithDuplicates();

//        For example,
//        If nums = [1,2,2], a solution is:
//
//        {}
//        1
//        2
//        1 2
//        2 2
//        1 2 2

        int[] nums = {1,2,2};
        List<List<Integer>> subsets = sol.subsetsWithDup(nums);
        for (List<Integer> list : subsets) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
