import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by davidlong on 7/3/18.
 */
public class Q0040_CombinationSumUniqueWithDupes {

    //https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#backtracking
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(new ArrayList<>(), combinations, new boolean[candidates.length], 0, target, candidates);
        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
                              boolean[] hasVisited, int start, int target, final int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            System.out.print("Got a match: ");
            printList(tempCombination);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            System.out.println();
            System.out.println("start= " + start + ", i = " + i);
            if (i != 0 && candidates[i] == candidates[i - 1] && !hasVisited[i - 1]) {
                System.out.println("skip this: start = " + start + ", i = " + i + ", candidates[i] = " + candidates[i] + ", candidates[i-1] = " + candidates[i-1] + ", hasVisited[i - 1]=" + hasVisited[i - 1]);
                continue;
            }
            // no need to check higher numbers since we sorted them
            if (candidates[i] > target) {
                System.out.println("break this: start = " + start + ", i = " + i + ", candidates[i] = " + candidates[i]);
                break;
            }

            tempCombination.add(candidates[i]);
            printList(tempCombination);
            hasVisited[i] = true;
            printList(hasVisited);
            backtracking(tempCombination, combinations, hasVisited, i + 1, target - candidates[i], candidates);
            hasVisited[i] = false;
            printList(hasVisited);
            tempCombination.remove(tempCombination.size() - 1);
            printList(tempCombination);
        }
    }

//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(candidates);
//        combinationSumCore(result, new ArrayList<>(), candidates, target, 0);
//        return result;
//    }
//
//    private void combinationSumCore(List<List<Integer>> result, List<Integer> bufferList, int[] candidates, int target, int start) {
//        if (target == 0) {
//            result.add(new ArrayList<>(bufferList));
//            return;
//        }
//
//        for (int i = start; i < candidates.length; i++) {
//            if(i > start && candidates[i] == candidates[i-1]) {
//                continue; // skip duplicates
//            }
//
//            if (candidates[i] > target) {
//                break;  // since the candidates are sorted, there is no reason to try bigger numbers
//            }
//
//            bufferList.add(candidates[i]);
//            int remaining = target - candidates[i];
//            combinationSumCore(result, bufferList, candidates, remaining, i + 1);
//            bufferList.remove(bufferList.size() - 1);
//        }
//    }

//    public List<List<Integer>> combinationSum2(int[] nums, int target) {
//        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
//        backtrack(list, new ArrayList<>(), nums, target, 0);
//        return list;
//
//    }
//
//    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
//        if(remain < 0) return;
//        else if(remain == 0) list.add(new ArrayList<>(tempList));
//        else{
//            for(int i = start; i < nums.length; i++){
//                if(i > start && nums[i] == nums[i-1]) {
//                    continue; // skip duplicates
//                }
//                tempList.add(nums[i]);
//                backtrack(list, tempList, nums, remain - nums[i], i + 1);
//                tempList.remove(tempList.size() - 1);
//            }
//        }
//    }

    public static void main(String[] args) {
        Q0040_CombinationSumUniqueWithDupes sol = new Q0040_CombinationSumUniqueWithDupes();

        int[] nums1 = {10,1,2,7,6,1,5};
        List<List<Integer>> combSum1 = sol.combinationSum2(nums1, 8);
        printListOfList(combSum1);

//        [
//        [1, 7],
//        [1, 2, 5],
//        [2, 6],
//        [1, 1, 6]
//        ]

        System.out.println();

        int[] nums2 = {2,5,2,1,2};
        List<List<Integer>> combSum2 = sol.combinationSum2(nums2, 5);
        printListOfList(combSum2);
//
//        [
//        [1,2,2],
//        [5]
//        ]
    }

    private static void printList(boolean[] boolList) {
        for (boolean b : boolList) {
            System.out.print(Objects.toString(b) + " ");
        }
        System.out.println();
    }

    private static void printListOfList(List<List<Integer>> listOfList) {
        for (List<Integer> list : listOfList) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private static void printList(List<Integer> list) {
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
