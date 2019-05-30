/*
https://leetcode.com/problems/minimize-max-distance-to-gas-station/
On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.
 */
public class Q0774_MinimizeMaxDistanceToGasStation {
    /*
    https://leetcode.com/problems/minimize-max-distance-to-gas-station/solution/
    Approach #4: Binary Search [Accepted]

Intuition

Let's ask possible(D): with K (or less) gas stations, can we make every adjacent distance between gas stations at most D? This function is monotone, so we can apply a binary search to find
D
*
D
*
 .

Algorithm

More specifically, there exists some D* (the answer) for which possible(d) = False when d < D* and possible(d) = True when d > D*. Binary searching a monotone function is a typical technique, so let's focus on the function possible(D).

When we have some interval like X = stations[i+1] - stations[i], we'll need to use
⌊
X
D
⌋
⌊
D
X
​
 ⌋ gas stations to ensure every subinterval has size less than D. This is independent of other intervals, so in total we'll need to use
∑
i
⌊
X
i
D
⌋
∑
i
​
 ⌊
D
X
i
​

​
 ⌋ gas stations. If this is at most K, then it is possible to make every adjacent distance between gas stations at most D.

 Complexity Analysis

Time Complexity:
O
(
N
log
⁡
W
)
O(NlogW), where
N
N is the length of stations, and
W
=
1
0
14
W=10
14
  is the range of possible answers (
1
0
8
10
8
 ), divided by the acceptable level of precision (
1
0
−
6
10
−6
 ).

Space Complexity:
O
(
1
)
O(1) in additional space complexity.
     */
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0, hi = 1e8;
        while (hi - lo > 1e-6) {
            double mi = (lo + hi) / 2.0;
            if (possible(mi, stations, K))
                hi = mi;
            else
                lo = mi;
        }
        return lo;
    }

    public boolean possible(double D, int[] stations, int K) {
        int used = 0;
        for (int i = 0; i < stations.length - 1; ++i)
            used += (int) ((stations[i+1] - stations[i]) / D);
        return used <= K;
    }
}
