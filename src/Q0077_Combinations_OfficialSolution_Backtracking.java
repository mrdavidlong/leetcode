import java.util.LinkedList;
import java.util.List;

public class Q0077_Combinations_OfficialSolution_Backtracking {
    public void backtrack(List<List<Integer>> result, LinkedList<Integer> tempList, int first, int n, int k) {
        // if the combination is done
        if (tempList.size() == k) {
            result.add(new LinkedList(tempList));
            return;
        }

        for (int i = first; i <= n; i++) {
            if (tempList.size() < k) {
                // add i into the current combination
                tempList.add(i);
                // use next integers to complete the combination
                backtrack(result, tempList, i + 1, n, k);
                // backtrack
                tempList.removeLast();
            }
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList();
        backtrack(result, new LinkedList<>(), 1, n, k);
        return result;
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

        Q0077_Combinations_OfficialSolution_Backtracking sol = new Q0077_Combinations_OfficialSolution_Backtracking();
        List<List<Integer>> result = sol.combine(4,2);
        for (List<Integer> list : result) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
