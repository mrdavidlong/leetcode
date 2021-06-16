import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/description/
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 Example 1:

 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output: [1,2,3,6,9,8,7,4,5]
 Example 2:

 Input:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Q0054_SpiralMatrix {

    /*
    https://leetcode.com/problems/spiral-matrix/discuss/20599/Super-Simple-and-Easy-to-Understand-Solution
    This is a very simple and easy to understand solution. I traverse right and increment rowBegin, then traverse down and decrement colEnd, then I traverse left and decrement rowEnd, and finally I traverse up and increment colBegin.

    The only tricky part is that when I traverse left or up I have to check whether the row or col still exists to prevent duplicates. If anyone can do the same thing without that check, please let me know!

    Any comments greatly appreciated.
    */

//    public List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> res = new ArrayList<Integer>();
//        if (matrix.length == 0) {
//            return res;
//        }
//        int rowBegin = 0;
//        int rowEnd = matrix.length-1;
//        int colBegin = 0;
//        int colEnd = matrix[0].length - 1;
//
//        while (rowBegin <= rowEnd && colBegin <= colEnd) {
//            // Traverse Right
//            for (int i = colBegin; i <= colEnd; i++) {
//                res.add(matrix[rowBegin][i]);
//            }
//            rowBegin++;
//
//            // Traverse Down
//            for (int i = rowBegin; i <= rowEnd; i++) {
//                res.add(matrix[i][colEnd]);
//            }
//            colEnd--;
//
//            if (rowBegin <= rowEnd) {
//                // Traverse Left
//                for (int i = colEnd; i >= colBegin; i--) {
//                    res.add(matrix[rowEnd][i]);
//                }
//            }
//            rowEnd--;
//
//            if (colBegin <= colEnd) {
//                // Traver Up
//                for (int i = rowEnd; i >= rowBegin; i--) {
//                    res.add(matrix[i][colBegin]);
//                }
//            }
//            colBegin++;
//        }
//
//        return res;
//    }

    //https://leetcode.com/problems/spiral-matrix/solution/
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] visited = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            visited[r][c] = true;
            int nextR = r + dr[di];
            int nextC = c + dc[di];
            if (0 <= nextR && nextR < R && 0 <= nextC && nextC < C && !visited[nextR][nextC]){
                r = nextR;
                c = nextC;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0054_SpiralMatrix sol = new Q0054_SpiralMatrix();

        int[][] matrix = {
            { 1, 2, 3 },
            { 8, 9, 4 },
            { 7, 6, 5 }
        };
        List<Integer> spiralList = sol.spiralOrder(matrix);

        int[][] matrix2 = {
                { 1, 2, 3, 4 },
                {10,11,12,13 },
                { 9, 8, 7, 6 }
        };
        List<Integer> spiralList2 = sol.spiralOrder(matrix2);
    }

}
