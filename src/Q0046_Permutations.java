import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Q0046_Permutations {
    public List<List<Integer>> permuteByDavid(int[] nums) {
        List<List<Integer>> perms = new LinkedList<>();
        List<Integer> bufferNumList = new LinkedList<>();

        permuteCore(nums, bufferNumList, perms);

        return perms;
    }

    private void permuteCore(int[] nums, List<Integer> bufferNumList, List<List<Integer>> perms) {
        if (bufferNumList.size() == nums.length) {
            perms.add(new LinkedList<>(bufferNumList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            if(bufferNumList.contains(num)) continue;

            bufferNumList.add(num);
            permuteCore(nums, bufferNumList, perms);
            bufferNumList.remove(bufferNumList.size() - 1);
        }
    }


    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, result, new ArrayList<>());
        return result;
    }

    private void backtrack(final int [] nums, List<List<Integer>> result, List<Integer> tempList){
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(nums, result, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    private void backtracking(final int[] nums, List<List<Integer>> result, List<Integer> tempList, boolean[] used) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // clone a list
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            tempList.add(nums[i]);

            backtracking(nums, result, tempList, used);

            tempList.remove(tempList.size() - 1);
            used[i] = false;
        }
    }

    public void backtrack(int n,
                          ArrayList<Integer> nums,
                          List<List<Integer>> output,
                          int first) {
        // if all integers are used up
        if (first == n)
            output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permuteOfficialSolution(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }


    public static void main(String[] args) {
        Q0046_Permutations sol = new Q0046_Permutations();

//        int[] nums = {1,2,3};
//        1 2 3
//        1 3 2
//        2 1 3
//        2 3 1
//        3 1 2
//        3 2 1

        int[] nums = {1,2,3};
        List<List<Integer>> perms = sol.permute(nums);
        List<List<Integer>> perms2 = sol.permuteOfficialSolution(nums);
        for (List<Integer> list : perms) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
