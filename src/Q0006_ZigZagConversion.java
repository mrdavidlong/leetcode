import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/description/
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"

 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string s, int numRows);
 Example 1:

 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"
 Example 2:

 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:

 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 */
public class Q0006_ZigZagConversion {
    private static String toHorizontal(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] != 0) {
                    sb.append(matrix[r][c]);
                }
            }
        }
        return sb.toString();
    }


    public static String convertByDavid(String s, int numRows) {
        char[][] matrix = new char[numRows][s.length()];
        if (s.isEmpty()) return "";

        matrix[0][0] = s.charAt(0);

        int i = 1;
        int row = 1;
        int col = 0;
        char c;
        while (i < s.length()) {

            // go down
            while (row < numRows && i < s.length()) {
                c = s.charAt(i);
                matrix[row][col] = c;
                i++;
                row++;
            }

            //rewind
            row = row - 2;
            col++;

            // go up right;
            while (row >= 0 && i < s.length()) {
                c = s.charAt(i);
                matrix[row][col] = c;
                i++;
                row--;
                col++;
            }

            //rewind
            row = row + 2;
            col--;
        }


        return toHorizontal(matrix);
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    public static void main(String[] args) {
        String input = "PAYPALISHIRING";
        String outputByDavid = convertByDavid(input, 4);
        System.out.println("convertByDavid = " + outputByDavid);

        String output = convert(input, 4);
        System.out.println("convert = " + output);
    }
}
