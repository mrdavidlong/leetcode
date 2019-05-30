import java.util.Arrays;

/**
 * Created by davidlong on 7/5/18.
 */
public class Q0455_AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;

        }
        return i;
    }

    public static void main(String[] args) {
        Q0455_AssignCookies sol = new Q0455_AssignCookies();

        int[] g1 = {1,2,3}; // children's greed factors
        int[] s1 = {1,1}; // cookies
        int numSatisfied1 = sol.findContentChildren(g1, s1);

        int[] g2 = {1,2}; // children's greed factors
        int[] s2 = {1,2,3}; // cookies
        int numSatisfied2 = sol.findContentChildren(g2, s2);
    }
}
