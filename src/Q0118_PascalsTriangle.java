import java.util.ArrayList;
import java.util.List;

public class Q0118_PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            res.add(new ArrayList<>());
        }

        for (int i = 0; i<numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if ( j == 0 || i == j) {
                    res.get(i).add(1);
                }
                else {
                    int sum = res.get(i-1).get(j-1) + res.get(i-1).get(j);
                    res.get(i).add(sum);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Q0118_PascalsTriangle sol = new Q0118_PascalsTriangle();
        List<List<Integer>> res1 = sol.generate(1);
        List<List<Integer>> res2 = sol.generate(2);
        List<List<Integer>> res3 = sol.generate(3);
        List<List<Integer>> res4 = sol.generate(4);
    }
}
