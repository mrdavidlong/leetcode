/**
 * Created by davidlong on 7/5/18.
 */
public class Q0392_IsSubsequence {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            // index + 1 to force the check to move forward down the t character set
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        s = "abc", t = "ahbgdc"
//        Return true.
        Q0392_IsSubsequence sol = new Q0392_IsSubsequence();

        String s = "abc";
        //String t = "ahbgdc";
        String t = "bdeahgdc";
        boolean isSubseq1 = sol.isSubsequence(s,t);
    }
}
