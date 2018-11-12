/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#素数
 */
public class Q0204_CountPrimes {
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrimes[i]) {
                continue;
            }
            count++;
            // 从 i * i 开始，因为如果 k < i，那么 k * i 在之前就已经被去除过了
            for (long j = (long) (i) * i; j < n; j += i) {
                notPrimes[(int) j] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Q0204_CountPrimes sol = new Q0204_CountPrimes();
        int count = sol.countPrimes(10);
    }
}
