/**
 * Created by davidlong on 7/5/18.
 */
//https://leetcode.com/problems/sum-of-square-numbers/description/
public class Q0633_SumOfSquareNumbers {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#双指针
    public boolean judgeSquareSum(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int powSum = i * i + j * j;
            if (powSum == c) {
                return true;
            } else if (powSum > c) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q0633_SumOfSquareNumbers sol = new Q0633_SumOfSquareNumbers();
//        Example 1:
//        Input: 5
//        Output: True
//        Explanation: 1 * 1 + 2 * 2 = 5
        boolean found1 = sol.judgeSquareSum(5);


//        Example 2:
//        Input: 3
//        Output: False
        boolean found2 = sol.judgeSquareSum(3);
    }
}
