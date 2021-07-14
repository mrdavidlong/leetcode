import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Medium

357

144

Add to List

Share
Given two positive integers n and k.

A factor of an integer n is defined as an integer i where n % i == 0.

Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.

Example 1:

Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
Example 2:

Input: n = 7, k = 2
Output: 7
Explanation: Factors list is [1, 7], the 2nd factor is 7.
Example 3:

Input: n = 4, k = 4
Output: -1
Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.
Example 4:

Input: n = 1, k = 1
Output: 1
Explanation: Factors list is [1], the 1st factor is 1.
Example 5:

Input: n = 1000, k = 3
Output: 4
Explanation: Factors list is [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000].


Constraints:

1 <= k <= n <= 1000
Accepted
49,300
Submissions
78,389
 */
public class Q1492_The_Kth_Factor_Of_N {
    public int kthFactorBF(int n, int k) {
        for (int x = 1; x < n / 2 + 1; ++x) {
            if (n % x == 0) {
                --k;
                if (k == 0) {
                    return x;
                }
            }
        }

        return k == 1 ? n : -1;
    }


    // max heap -> to keep max element always on top
    Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);

    // push into heap and limit size of heap to k
    public void heapPushK(int x, int k) {
        heap.add(x);
        if (heap.size() > k) {
            heap.remove();
        }
    }

    public int kthFactor(int n, int k) {
        int sqrtN = (int) Math.sqrt(n);
        for (int x = 1; x <= sqrtN; x++) {
            if (n % x == 0) {
                heapPushK(x, k);
                if (x != n / x) {
                    heapPushK(n / x, k);
                }
            }
        }

        return k == heap.size() ? heap.poll() : -1;
    }




    public int kthFactorMath(int n, int k) {
        List<Integer> divisors = new ArrayList();
        int sqrtN = (int) Math.sqrt(n);
        for (int x = 1; x < sqrtN + 1; ++x) {
            if (n % x == 0) {
                --k;
                divisors.add(x);
                if (k == 0) {
                    return x;
                }
            }
        }

        // If n is a perfect square
        // we have to skip the duplicate
        // in the divisor list
        if (sqrtN * sqrtN == n) {
            ++k;
        }

        int nDiv = divisors.size();
        return (k <= nDiv) ? n / divisors.get(nDiv - k) : -1;
    }

    public static void main(String[] args) {
        Q1492_The_Kth_Factor_Of_N sol = new Q1492_The_Kth_Factor_Of_N();
        int kthFactor = sol.kthFactor(15, 2);
        int kthFactor1 = sol.kthFactor(12, 3);
        int kthFactor2 = sol.kthFactor(7, 2);
        int kthFactor3 = sol.kthFactor(4, 4);
        int kthFactor4 = sol.kthFactor(1, 1);
        int kthFactor5 = sol.kthFactor(1000, 3);


        int kthFactorMath = sol.kthFactorMath(15, 2);
        int kthFactorMatha = sol.kthFactorMath(15, 3);
        int kthFactorMathb = sol.kthFactorMath(36, 6);
        int kthFactorMath1 = sol.kthFactorMath(12, 3);
        int kthFactorMath2 = sol.kthFactorMath(7, 2);
        int kthFactorMath3 = sol.kthFactorMath(4, 4);
        int kthFactorMath4 = sol.kthFactorMath(1, 1);
        int kthFactorMath5 = sol.kthFactorMath(1000, 3);
    }
}
