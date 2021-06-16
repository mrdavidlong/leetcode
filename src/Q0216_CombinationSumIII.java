import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/description/
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

 Note:

 All numbers will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: k = 3, n = 7
 Output: [[1,2,4]]
 Example 2:

 Input: k = 3, n = 9
 Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Q0216_CombinationSumIII {
    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#backtracking
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumCore(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void combinationSumCore(List<List<Integer>> result, List<Integer> tempList, int n, int k, int start) {
        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        if (k == 0 || n <= 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            combinationSumCore(result, tempList, n - i, k - 1, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //https://leetcode.com/problems/combination-sum-iii/
    protected void backtrack(int remain, int k,
                             LinkedList<Integer> comb, int next_start,
                             List<List<Integer>> results) {

        if (remain == 0 && comb.size() == k) {
            // Note: it's important to make a deep copy here,
            // Otherwise the combination would be reverted in other branch of backtracking.
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (remain < 0 || comb.size() == k) {
            // Exceed the scope, no need to explore further.
            return;
        }

        // Iterate through the reduced list of candidates.
        for (int i = next_start; i < 9; ++i) {
            comb.add(i + 1);
            this.backtrack(remain - i - 1, k, comb, i + 1, results);
            comb.removeLast();
        }
    }

    public List<List<Integer>> combinationSum3OfficialSolution(int k, int n) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        LinkedList<Integer> comb = new LinkedList<Integer>();

        this.backtrack(n, k, comb, 0, results);
        return results;
    }

    public static void main(String[] args) {
        Q0216_CombinationSumIII sol = new Q0216_CombinationSumIII();

        //    Input: k = 3, n = 9
        //
        //    Output:
        //
        //            [[1,2,6], [1,3,5], [2,3,4]]
        List<List<Integer>> combSum1 = sol.combinationSum3(3, 9);
        for (List<Integer> list : combSum1) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
