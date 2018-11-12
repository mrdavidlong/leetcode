/**
 * Created by davidlong on 7/1/18.
 */
public class Q0028_ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) return 0;

        if (needle.length() > haystack.length()) return -1;

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 0;
                int k = i;
                for (; j < needle.length() && k < haystack.length(); j++) {
                    if (needle.charAt(j) == haystack.charAt(k)) {
                        k++;
                    } else {
                        break;
                    }
                }
                if (j == needle.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q0028_ImplementStrStr sol = new Q0028_ImplementStrStr();
        
        String haystack1 = "hello";
        String needle1 = "ll";
        int index1 = sol.strStr(haystack1, needle1);
        System.out.println("index1 = " + index1);


        String haystack2 = "aaa";
        String needle2 = "aaaa";
        int index2 = sol.strStr(haystack2, needle2);
        System.out.println("index2 = " + index2);

        String haystack3 = "mississippi";
        String needle3 = "mississippi";
        int index3 = sol.strStr(haystack3, needle3);
        System.out.println("index3 = " + index3);
        
    }
}
