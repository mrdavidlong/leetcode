public class Q0221_Maximal_Square {
    // Time: O((mn)^2)
    // Space: O(1)
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int maxSqLen = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int sqLen = 1;
                    boolean flag = true;
                    while (sqLen + i < rows && sqLen + j < cols && flag) {
                        for (int k = j; k <= sqLen + j; k++) {
                            if (matrix[i + sqLen][k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            for (int k = i; k <= sqLen + i; k++) {
                                if (matrix[k][j + sqLen] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        if (flag)
                            sqLen++;
                    }
                    if (sqLen > maxSqLen) {
                        maxSqLen = sqLen;
                    }
                }
            }
        }
        return maxSqLen * maxSqLen;
    }

    public static void main(String[] args) {
        Q0221_Maximal_Square sol = new Q0221_Maximal_Square();
        char[][] rect1 = new char[][]
            {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
            };
        int maxSquare1 = sol.maximalSquare(rect1);
        int maxSquare2 = sol.maximalSquare(new char[][] {{'0','1'},{'1','0'}});
        int maxSquare3 = sol.maximalSquare(new char[][] {{'0'}});
    }
}
