/**
 * https://leetcode.com/problems/first-bad-version/description/
 */
public class Q0278_FirstBadVersion {
    private boolean isBadVersion(int n) {
        if (n == 4) {
            return true;
        } else {
            return false;
        }
    }

    public int firstBadVersion(int n) {
        int l = 1, h = n;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (isBadVersion(mid)) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Q0278_FirstBadVersion sol = new Q0278_FirstBadVersion();
        int firstBadVersion = sol.firstBadVersion(5);
    }
}
