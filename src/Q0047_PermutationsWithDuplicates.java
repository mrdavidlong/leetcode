import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0047_PermutationsWithDuplicates {
    ////https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    private void backtrack(final int [] nums, List<List<Integer>> result, List<Integer> tempList, boolean [] used){
        if(tempList.size() == nums.length){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            // this would work too:
            // if(used[i] || (i > 0 && nums[i] == nums[i-1] && used[i - 1])) {
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) {
                continue;
            }

            used[i] = true;
            tempList.add(nums[i]);
            backtrack(nums, result, tempList, used);
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q0047_PermutationsWithDuplicates sol = new Q0047_PermutationsWithDuplicates();

        int[] nums = {1,1,1, 2};
        List<List<Integer>> perms = sol.permuteUnique(nums);
        for (List<Integer> list : perms) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
