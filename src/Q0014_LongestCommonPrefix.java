/**
 * Created by davidlong on 6/29/18.
 */
public class Q0014_LongestCommonPrefix {

    private boolean sameCharAtPos(String[] strs, char c, int pos) {
        // start with i = 1, since c is from strs[0]
        for (int i = 1; i < strs.length; i++) {
            if (pos >= strs[i].length() || strs[i].charAt(pos) != c) return false;
        }
        return true;
    }

    public String longestCommonPrefixByDavid(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        int i = 0;
        for (; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            if (sameCharAtPos(strs, c, i) == false) break;
        }
        return strs[0].substring(0, i);
    }

    public String longestCommonPrefixHorizontalScan(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        Q0014_LongestCommonPrefix q = new Q0014_LongestCommonPrefix();
        String[] strings = new String[] {"flower","flow","flight"};
        String longestPrefix = q.longestCommonPrefixByDavid(strings);
        System.out.println("longestPrefix = " + longestPrefix);

    }
}
