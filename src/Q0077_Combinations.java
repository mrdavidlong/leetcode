import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/description/
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 Example:

 Input: n = 4, k = 2
 Output:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class Q0077_Combinations {

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#backtracking
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(n, 1, k, result, new ArrayList<>());
        return result;
    }

    private void backtracking(final int n, int start, int k, List<List<Integer>> result, List<Integer> tempList) {
        if (k == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i <= n - k + 1; i++) {  // cut out the branches
        //for (int i = start; i <= n ; i++) {  // cut out the branches
            tempList.add(i);
            backtracking(n, i + 1, k - 1, result, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {

//    If n = 4 and k = 1, a solution is:
//        [
//        1,
//        2,
//        3,
//        4
//        ]

//    If n = 4 and k = 2, a solution is:
//            [
//            [1,2],
//            [1,3],
//            [1,4],
//            [2,3],
//            [2,4],
//            [3,4]
//            ]

//    If n = 4 and k = 3, a solution is:
//        1 2 3
//        1 2 4
//        1 3 4
//        2 3 4

//    If n = 4 and k = 4, a solution is:
//        1 2 3 4

//    If n = 5 and k = 3, a solution is:
//        1 2 3
//        1 2 4
//        1 2 5
//        1 3 4
//        1 3 5
//        1 4 5
//        2 3 4
//        2 3 5
//        2 4 5
//        3 4 5

        Q0077_Combinations sol = new Q0077_Combinations();
        List<List<Integer>> result = sol.combine(4,2);
        for (List<Integer> list : result) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }


}
