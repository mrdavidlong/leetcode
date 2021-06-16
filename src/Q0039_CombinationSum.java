import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/description/
 */
public class Q0039_CombinationSum {

// https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#backtracking
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> combinations = new ArrayList<>();
//        backtracking(new ArrayList<>(), combinations, 0, target, candidates);
//        return combinations;
//    }
//
//    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
//                              int start, int target, final int[] candidates) {
//
//        if (target == 0) {
//            combinations.add(new ArrayList<>(tempCombination));
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            if (candidates[i] <= target) {
//                tempCombination.add(candidates[i]);
//                backtracking(tempCombination, combinations, i, target - candidates[i], candidates);
//                tempCombination.remove(tempCombination.size() - 1);
//            }
//        }
//    }

//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(candidates);
//        combinationSumCore(result, new ArrayList<>(), candidates, target, 0);
//        return result;
//    }
//
//    private void combinationSumCore(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int target, int start){
//        if (target == 0) {
//            result.add(new ArrayList<>(tempList));
//            return;
//        }
//
//        for (int i = start; i < candidates.length; i++) {
//            if (candidates[i] > target) break;  // since the candidates are sorted, there is no reason to try bigger numbers
//
//            tempList.add(candidates[i]);
//            int remaining = target - candidates[i];
//            combinationSumCore(result, tempList, candidates, remaining, i);
//            tempList.remove(tempList.size() - 1);
//        }
//    }

    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /////////  Official Solution
    protected void backtrack(
            int remain,
            LinkedList<Integer> comb,
            int start, int[] candidates,
            List<List<Integer>> results) {

        if (remain == 0) {
            // make a deep copy of the current combination
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (remain < 0) {
            // exceed the scope, stop exploration.
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            // add the number into the combination
            comb.add(candidates[i]);
            this.backtrack(remain - candidates[i], comb, i, candidates, results);
            // backtrack, remove the number from the combination
            comb.removeLast();
        }
    }

    public List<List<Integer>> combinationSumOfficialSolution(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        LinkedList<Integer> comb = new LinkedList<Integer>();

        this.backtrack(target, comb, 0, candidates, results);
        return results;
    }

    public static void main(String[] args) {
        Q0039_CombinationSum sol = new Q0039_CombinationSum();

        int[] nums1 = {2,3,6,7};
        List<List<Integer>> combSum1 = sol.combinationSum(nums1, 7);
        for (List<Integer> list : combSum1) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        System.out.println();

        int[] nums2 = {2,3,5};
        List<List<Integer>> combSum2 = sol.combinationSum(nums2, 8);
        for (List<Integer> list : combSum2) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
