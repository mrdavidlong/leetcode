import common.Interval;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * Given a collection of intervals, merge all overlapping intervals.

 Example 1:

 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 Example 2:

 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
 */
public class Q0056_MergeIntervals {
    // https://leetcode.com/problems/merge-intervals/solution/
//    private class IntervalComparator implements Comparator<Interval> {
//        @Override
//        public int compare(Interval a, Interval b) {
//            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
//        }
//    }
//
//    public List<Interval> merge(List<Interval> intervals) {
//        Collections.sort(intervals, new IntervalComparator());
//
//        LinkedList<Interval> merged = new LinkedList<Interval>();
//        for (Interval interval : intervals) {
//            // if the list of merged intervals is empty or if the current
//            // interval does not overlap with the previous, simply append it.
//            if (merged.isEmpty() || merged.getLast().end < interval.start) {
//                merged.add(interval);
//            }
//            // otherwise, there is overlap, so we merge the current and previous
//            // intervals.
//            else {
//                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
//            }
//        }
//
//        return merged;
//    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        Q0056_MergeIntervals sol = new Q0056_MergeIntervals();

//        List<Interval> intervals = new ArrayList<>();
//        intervals.add(new Interval(1,3));
//        intervals.add(new Interval(2,6));
//        intervals.add(new Interval(8,10));
//        intervals.add(new Interval(15,18));
//        List<Interval> merged1 = sol.merge(intervals);
//
//        List<Interval> intervals2 = new ArrayList<>();
//        intervals2.add(new Interval(1,4));
//        intervals2.add(new Interval(4,5));
//        List<Interval> merged2 = sol.merge(intervals2);

        int[][] intervals1 =
            {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 16}
            };
        int[][] merged1 = sol.merge(intervals1);

        int[][] intervals2 =
            {
                {1, 4},
                {4, 5}
            };
        int[][] merged2 = sol.merge(intervals2);
    }
}
