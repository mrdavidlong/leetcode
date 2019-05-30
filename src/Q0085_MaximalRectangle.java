import java.util.Stack;

/**
 * https://leetcode.com/problems/maximal-rectangle/description/
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 Example:

 Input:
 [
 ["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]
 ]
 Output: 6
 */
public class Q0085_MaximalRectangle {
    // https://leetcode.com/problems/maximal-rectangle/discuss/29064/A-O(n2)-solution-based-on-Largest-Rectangle-in-Histogram
    public int maximalRectangle(char[][] matrix) {
        if (matrix==null||matrix.length==0||matrix[0].length==0)
            return 0;
        int cLen = matrix[0].length;    // column length
        int rLen = matrix.length;       // row length
        // height array
        int[] h = new int[cLen+1];
        h[cLen]=0;
        int max = 0;

        for (int row=0;row<rLen;row++) {
            Stack<Integer> s = new Stack<>();
            for (int i = 0; i < cLen + 1; i++) {
                if (i<cLen)
                    if (matrix[row][i] == '1')
                        h[i] += 1;
                    else h[i] = 0;

                if (s.isEmpty() || h[s.peek()] <= h[i])
                    s.push(i);
                else {
                    while (!s.isEmpty() && h[i] < h[s.peek()]) {
                        int top = s.pop();
                        int area = h[top] * (s.isEmpty() ? i : (i - s.peek() - 1));
                        if (area>max)
                            max = area;
                    }
                    s.push(i);
                }
            }
        }
        return max;
    }

    // BEST
    // https://www.youtube.com/watch?v=g8bSdXCG-lA
    // https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MaximumRectangularSubmatrixOf1s.java
//    public int maximum(int input[][]){
//        int temp[] = new int[input[0].length];
//        MaximumHistogram mh = new MaximumHistogram();
//        int maxArea = 0;
//        int area = 0;
//        for(int i=0; i < input.length; i++){
//            for(int j=0; j < temp.length; j++){
//                if(input[i][j] == 0){
//                    temp[j] = 0;
//                }else{
//                    temp[j] += input[i][j];
//                }
//            }
//            area = mh.maxHistogram(temp);
//            if(area > maxArea){
//                maxArea = area;
//            }
//        }
//        return maxArea;
//    }
    public static void main(String args[]){
        char input[][] = new char[][]
        {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };

        Q0085_MaximalRectangle sol = new Q0085_MaximalRectangle();
        int maxRectangle = sol.maximalRectangle(input);
        //System.out.println("Max rectangle is of size " + maxRectangle);
        assert maxRectangle == 6;
    }
}
