import common.Interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/description/
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:

 Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 Output: [[1,5],[6,9]]
 Example 2:

 Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 Output: [[1,2],[3,10],[12,16]]
 Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */
public class Q0057_InsertInterval {

    // https://leetcode.com/problems/insert-interval/discuss/21602/Short-and-straight-forward-Java-solution
//    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
//        List<Interval> result = new LinkedList<>();
//        int i = 0;
//        // add all the intervals ending before newInterval starts
//        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
//            result.add(intervals.get(i++));
//        // merge all overlapping intervals to one considering newInterval
//        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
//            newInterval = new Interval( // we could mutate newInterval here also
//                    Math.min(newInterval.start, intervals.get(i).start),
//                    Math.max(newInterval.end, intervals.get(i).end));
//            i++;
//        }
//        result.add(newInterval); // add the union of intervals we got
//        // add all the rest
//        while (i < intervals.size()) result.add(intervals.get(i++));
//        return result;
//    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // init data
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<>();

        // add all intervals starting before newInterval
        while (idx < n && intervals[idx][0] < newInterval[0])
            output.add(intervals[idx++]);

        // add newInterval
        // if there is no overlap, just add the interval
        if (output.isEmpty() || output.getLast()[1] < newInterval[0]) {
            output.add(newInterval);
        } else { // if there is an overlap, merge with the last interval
            output.getLast()[1] = Math.max(output.getLast()[1], newInterval[1]);
        }

        // add next intervals, merge with newInterval if needed
        int[] interval;
        while (idx < n) {
            interval = intervals[idx++];
            // if there is no overlap, just add an interval
            if (output.getLast()[1] < interval[0]) {
                output.add(interval);
            } else { // if there is an overlap, merge with the last interval
                output.getLast()[1] = Math.max(output.getLast()[1], interval[1]);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }

    public static void main(String[] args) {
        Q0057_InsertInterval sol = new Q0057_InsertInterval();

//        List<Interval> intervals = new ArrayList<>();
//        intervals.add(new Interval(1,3));
//        intervals.add(new Interval(6,9));
//        Interval newInterfal = new Interval(2,5);
//        List<Interval> merged1 = sol.insert(intervals, newInterfal);

//        List<Interval> intervals2 = new ArrayList<>();
//        intervals2.add(new Interval(1,2));
//        intervals2.add(new Interval(3,5));
//        intervals2.add(new Interval(6,7));
//        intervals2.add(new Interval(8,10));
//        intervals2.add(new Interval(12,16));
//        Interval newInterfal2 = new Interval(4,8);
//
//        List<Interval> merged2 = sol.insert(intervals2, newInterfal2);

        int[][] intervals1 =
            {
                {1, 3},
                {6, 9}
            };
        int[] newInterval1 = {2,5};
        int[][] merged1 = sol.insert(intervals1, newInterval1);

        int[][] intervals2 =
                {
                        {1, 2},
                        {3, 5},
                        {6, 7},
                        {8, 10},
                        {12, 16}
                };
        int[] newInterval2 = {4, 8};
        int[][] merged2 = sol.insert(intervals2, newInterval2);
    }

}
