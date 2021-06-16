import java.util.ArrayList;
import java.util.List;

public class Q0051_NQueensBestFromCtCI {

    /* Check if (row1, column1) is a valid spot for a queen by checking if there
     * is a queen in the same column or diagonal. We don't need to check it for queens
     * in the same row because the calling placeQueen only attempts to place one queen at
     * a time. We know this row is empty.
     */
    public static boolean checkValid(Integer[] columns, int row1, int column1) {
        for (int row2 = 0; row2 < row1; row2++) {
            int column2 = columns[row2];
            /* Check if (row2, column2) invalidates (row1, column1) as a queen spot. */

            /* Check if rows have a queen in the same column */
            if (column1 == column2) {
                return false;
            }

            /* Check diagonals: if the distance between the columns equals the distance
             * between the rows, then they’re in the same diagonal. */
            int columnDistance = Math.abs(column2 - column1);
            int rowDistance = row1 - row2; // row1 > row2, so no need to use absolute value
            if (columnDistance == rowDistance) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> solveNQueens(int boardSize) {
        List<List<String>> results = new ArrayList<>();
        Integer[] columns = new Integer[boardSize];
        clear(columns, boardSize);
        placeQueens(0, columns, results, boardSize);
        return results;
    }

    private void placeQueens(int row, Integer[] columns, List<List<String>> results, int boardSize) {
        if (row == boardSize) { // Found valid placement
            results.add(createBoardSol(columns, boardSize));
        } else {
            for (int col = 0; col < boardSize; col++) {
                if (checkValid(columns, row, col)) {
                    columns[row] = col;	// Place queen, backtracking with overriding the values
                    placeQueens(row + 1, columns, results, boardSize);
                }
            }
        }
    }

    private void clear(Integer[] columns, int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            columns[i] = -1;
        }
    }

    private List<String> createBoardSol(Integer[] columns, int boardSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < boardSize; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < boardSize; j++){
                if (columns[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        return list;
    }

    public static void main(String[] args) {
        Q0051_NQueensBestFromCtCI sol = new Q0051_NQueensBestFromCtCI();
        List<List<String>> result = sol.solveNQueens(4);
        for (List<String> list : result) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
            System.out.println();
        }
    }
}
