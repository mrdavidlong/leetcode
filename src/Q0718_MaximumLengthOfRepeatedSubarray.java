import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Q0718_MaximumLengthOfRepeatedSubarray {

    public int findLengthByDavid(int[] A, int[] B) {
        int maxLen = 0;
        for (int i = 0; i < A.length ; i++) {
            for (int j= 0; j < B.length ; j++) {
                int len = 0;
                for (int k = 0; i + k < A.length && j + k < B.length; k++) {
                    if (A[i+k] == B[j+k]) {
                        len++;
                    } else {
                        break;
                    }
                }
                if (len > maxLen) {
                    maxLen = len;
                }
            }
        }
        return maxLen;
    }

    public int findLengthUsingMap(int[] A, int[] B) {
        int ans = 0;
        Map<Integer, ArrayList<Integer>> Bstarts = new HashMap();
        for (int j = 0; j < B.length; j++) {
            Bstarts.computeIfAbsent(B[j],  x -> new ArrayList()).add(j); // x is B[j]
        }

        for (int i = 0; i < A.length; i++) if (Bstarts.containsKey(A[i])) {
            for (int j: Bstarts.get(A[i])) {
                int k = 0;
                while (i+k < A.length && j+k < B.length && A[i+k] == B[j+k]) {
                    k++;
                }
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }

    public int findLengthDP(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i+1][j+1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0718_MaximumLengthOfRepeatedSubarray sol = new Q0718_MaximumLengthOfRepeatedSubarray();
        int[] a = {1,2,3,2,1};
        int[] b = {3,2,1,4,7};
        int len1 = sol.findLengthByDavid(a,b);
        int len2 = sol.findLengthUsingMap(a,b);
        int len3 = sol.findLengthDP(a,b);
    }
}
