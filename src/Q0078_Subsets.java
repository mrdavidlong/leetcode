import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0078_Subsets {
    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
//    public List<List<Integer>> subsets2(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        for (int size = 0; size <= nums.length; size++) {
//            backtracking(nums, size, 0, result, new ArrayList<>()); // all possible sizes
//        }
//        return result;
//    }
//
//    private void backtracking(final int[] nums, final int size, int start, List<List<Integer>> result, List<Integer> tempList) {
//        if (tempList.size() == size) {
//            result.add(new ArrayList<>(tempList));
//            return;
//        }
//
//        for (int i = start; i < nums.length; i++) {
//            tempList.add(nums[i]);
//            backtracking(nums, size, i + 1, result, tempList);
//            tempList.remove(tempList.size() - 1);
//        }
//    }

    public static void main(String[] args) {
        Q0078_Subsets sol = new Q0078_Subsets();

        int[] nums = {0,1,2};
        List<List<Integer>> subsets = sol.subsets(nums);
        for (List<Integer> list : subsets) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

//        1
//        2
//        3
//        1 2
//        1 3
//        2 3
//        1 2 3
    }
}
