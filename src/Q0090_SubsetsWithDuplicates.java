import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by davidlong on 7/4/18.
 */
public class Q0090_SubsetsWithDuplicates {

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        boolean[] used = new boolean[nums.length];
//        Arrays.sort(nums);
//        for (int size = 0; size <= nums.length; size++) {
//            backtracking(nums, size, 0, result, new ArrayList<>(), used); // all possible sizes
//        }
//        return result;
//    }
//
//    private void backtracking(final int[] nums, final int size, int start, List<List<Integer>> result, List<Integer> tempList, boolean[] used) {
//
//        if (tempList.size() == size) {
//            result.add(new ArrayList<>(tempList));
//            return;
//        }
//
//        for (int i = start; i < nums.length; i++) {
//            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
//
//            used[i] = true;
//            tempList.add(nums[i]);
//
//            backtracking(nums, size, i + 1, result, tempList, used);
//
//            used[i] = false;
//            tempList.remove(tempList.size() - 1);
//        }
//    }

//    // inspired by https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        boolean[] used = new boolean[nums.length];
//        Arrays.sort(nums);
//        backtracking(nums,0, result, new ArrayList<>(), used); // all possible sizes
//        return result;
//    }
//
//    private void backtracking(final int[] nums, int start, List<List<Integer>> result, List<Integer> tempList, boolean[] used) {
//        result.add(new ArrayList<>(tempList));
//        for (int i = start; i < nums.length; i++) {
//            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
//
//            used[i] = true;
//            tempList.add(nums[i]);
//            backtracking(nums,i + 1, result, tempList, used);
//            used[i] = false;
//            tempList.remove(tempList.size() - 1);
//        }
//    }

    // https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)#_=_
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
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
