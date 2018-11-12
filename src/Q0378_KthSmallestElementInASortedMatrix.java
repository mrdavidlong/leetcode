import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 *
 *Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class Q0378_KthSmallestElementInASortedMatrix {
    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public int kthSmallestBST(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
                    cnt++;
                }
            }
            if (cnt < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        Q0378_KthSmallestElementInASortedMatrix sol = new Q0378_KthSmallestElementInASortedMatrix();

        int[][] matrix =
            {
                { 1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
            };
        int kthSmallest = sol.kthSmallestBST(matrix, 8);
        int kthSmallest2 = sol.kthSmallestMinHeap(matrix, 8);
        int kthSmallest3 = sol.kthSmallestMinHeap(matrix, 2);
        
    }

    // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#数组与矩阵
    public int kthSmallestMinHeap(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j < n; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k - 1; i++) { // 小根堆，去掉 k - 1 个堆顶元素，此时堆顶元素就是第 k 的数
            Tuple t = pq.poll();
            if(t.x == m - 1) continue;
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple(int x, int y, int val) {
            this.x = x; this.y = y; this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }
}
