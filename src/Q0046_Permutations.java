import java.util.ArrayList;
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
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, result, new ArrayList<>());
        return result;
    }

    private void backtrack(final int [] nums, List<List<Integer>> result, List<Integer> tempList){
        if(tempList.size() == nums.length){
            result.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(nums, result, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
    public List<List<Integer>> permuteEasyToUnderstand(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    private void backtracking(final int[] nums, List<List<Integer>> result, List<Integer> tempList, boolean[] used) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // clone a list
            return;
        }

        for (int i = 0; i < used.length; i++) {
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
        for (List<Integer> list : perms) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
