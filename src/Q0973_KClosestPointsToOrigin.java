import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/*
https://leetcode.com/problems/k-closest-points-to-origin/
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)



 */
public class Q0973_KClosestPointsToOrigin {
    /*
    https://leetcode.com/problems/k-closest-points-to-origin/solution/
    Approach 1: Sort

Intuition

Sort the points by distance, then take the closest K points.

Algorithm

There are two variants.

In Java, we find the K-th distance by creating an array of distances and then sorting them. After, we select all the points with distance less than or equal to this K-th distance.

In Python, we sort by a custom key function - namely, the distance to the origin. Afterwards, we return the first K elements of the list.
     */
    // Approach #1
    public int[][] kClosestSol1(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }

    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }



    // Approach #2
    public int[][] kClosest(int[][] points, int K) {
        sort(0, points.length - 1, K, points);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K, int[][] points) {
        if (i >= j) return;
        int k = ThreadLocalRandom.current().nextInt(i, j + 1);
        swap(i, k, points);

        int mid = partition(i, j, points);
        int leftLength = mid - i + 1;
        if (K < leftLength)
            sort(i, mid - 1, K, points);
        else if (K > leftLength)
            sort(mid + 1, j, K - leftLength, points);
    }

    public int partition(int i, int j, int[][] points) {
        int oi = i;
        int pivot = dist(i, points);
        i++;

        while (true) {
            while (i < j && dist(i, points) < pivot)
                i++;
            while (i <= j && dist(j, points) > pivot)
                j--;
            if (i >= j) break;
            swap(i, j, points);
        }
        swap(oi, j, points);
        return j;
    }

    public int dist(int i, int[][] points) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j, int[][] points) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }

    public static void main(String[] args) {
        Q0973_KClosestPointsToOrigin sol = new Q0973_KClosestPointsToOrigin();
        int[][] points = sol.kClosest(new int[][] {{3,3},{5,-1},{-2,4}}, 2);
    }
}
