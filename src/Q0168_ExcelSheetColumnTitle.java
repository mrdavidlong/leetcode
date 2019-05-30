/**
 * https://leetcode.com/problems/excel-sheet-column-title/description/
 *
 *
 Given a positive integer, return its corresponding column title as appear in an Excel sheet.

 For example:

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB
 ...
 Example 1:

 Input: 1
 Output: "A"
 Example 2:

 Input: 28
 Output: "AB"
 Example 3:

 Input: 701
 Output: "ZY"
 */
public class Q0168_ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        if (n == 0) {
            return "";
        }
        // minus one, since Excel column starts with 1, Java number math starts with 0
        n--;
        return convertToTitle(n / 26) + (char) (n % 26 + 'A');
    }

    public static void main(String[] args) {
        Q0168_ExcelSheetColumnTitle sol = new Q0168_ExcelSheetColumnTitle();

        String title1 = sol.convertToTitle(1);
        String title2 = sol.convertToTitle(28);
        String title3 = sol.convertToTitle(701);
    }
}
