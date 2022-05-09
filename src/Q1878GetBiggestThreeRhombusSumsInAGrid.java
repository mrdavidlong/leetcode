import java.util.*;

public class Q1878GetBiggestThreeRhombusSumsInAGrid {
    class MinHeapComparator implements Comparator<Integer>{
        // Comparator that sorts integers from lowest to highest
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) return 1;
            else if (o1 == o2)	return 0;
            else return -1;
        }
    }

    public int[] getBiggestThree(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                set.add(grid[i][j]);
                int maxSize = Math.min(m, n)/2;
                for (int size = 1; size <= maxSize; size++) {
                    final int sum = getRhombusSum(grid, size, i, j, m, n);
                    if (sum != -1) {
                        set.add(sum);
                    }
                }
            }
        }
        return set.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).toArray();
    }

//    private void addToMinHeap(int num, PriorityQueue<Integer> minHeap) {
//        if (minHeap.size() < 3) {
//            minHeap.add(num);
//        } else if (num > minHeap.peek()) {
//            minHeap.poll();
//            minHeap.add(num);
//        }
//    }
//
//    public int[] getBiggestThree(int[][] grid) {
//        final int m = grid.length;
//        final int n = grid[0].length;
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>(3);
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                addToMinHeap(grid[i][j], minHeap);
//                int maxSize = Math.min(m, n)/2;
//                for (int size = 1; size <= maxSize; size++) {
//                    final int sum = getRhombusSum(grid, size, i, j, m, n);
//                    if (sum != -1) {
//                        addToMinHeap(sum, minHeap);
//                    }
//                }
//            }
//        }
//        return minHeap.stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
//    }

    private static int getRhombusSum(int[][] g, int size, int i, int j, int m, int n) {
        if (i + size >= m || i - size < 0 || (j + 2 * size) >= n) {
            return -1;
        }
        int sum = 0;
        for (int k = 1; k < size; k++) {
            sum += g[i - k][j + k];
            sum += g[i + k][j + k];
            sum += g[i - k][j + 2 * size - k];
            sum += g[i + k][j + 2 * size - k];
        }
        sum += g[i][j];
        sum += g[i][j + 2 * size];
        sum += g[i + size][j + size];
        sum += g[i - size][j + size];
        return sum;
    }

    public static void main(String[] args) {
        Q1878GetBiggestThreeRhombusSumsInAGrid sol = new Q1878GetBiggestThreeRhombusSumsInAGrid();
        int[] biggestThree1 = sol.getBiggestThree(new int[][] {
                {  3, 4,  5,  1,  3},
                {  3, 3,  4,  2,  3},
                { 20,30,200, 40, 10},
                {  1, 5,  5,  4,  1},
                {  4, 3,  2,  2,  5}});
        int[] biggestThree2 = sol.getBiggestThree(new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}});
        int[] biggestThree3 = sol.getBiggestThree(new int[][] {{7,7,7}});


    }

}
