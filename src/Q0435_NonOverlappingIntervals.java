import common.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by davidlong on 7/5/18.
 */
public class Q0435_NonOverlappingIntervals {

    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0 ) {
            return 0;
        }
        //Arrays.sort(intervals, Comparator.comparingInt(i -> i.end));
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        int cnt = 1;
        int end = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < end) {
                continue;
            }
            end = intervals[i].end;
            cnt++;
        }
        return intervals.length - cnt;
    }

    public static void main(String[] args) {
        Q0435_NonOverlappingIntervals sol = new Q0435_NonOverlappingIntervals();

        Interval i1_1 = new Interval(1,2);
        Interval i1_2 = new Interval(2,3);
        Interval i1_3 = new Interval(3,4);
        Interval i1_4 = new Interval(1,3);
        Interval[] iList1 = {i1_1,i1_2,i1_3,i1_4};

        int erase1 = sol.eraseOverlapIntervals(iList1);

        Interval i2_1 = new Interval(1,2);
        Interval i2_2 = new Interval(1,2);
        Interval i2_3 = new Interval(1,2);
        Interval[] iList2 = {i2_1,i2_2,i2_3};

        int erase2 = sol.eraseOverlapIntervals(iList2);

        Interval i3_1 = new Interval(1,2);
        Interval i3_2 = new Interval(2,3);
        Interval[] iList3 = {i3_1,i3_2};

        int erase3 = sol.eraseOverlapIntervals(iList3);

    }
}
