import common.Interval;

import java.util.Arrays;

/*
https://leetcode.com/problems/meeting-rooms/
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true

 */
public class Q0252_MeetingRooms {
    public boolean canAttendMeetingsByDavid(Interval[] intervals) {

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int startIndex = 1;
        int endIndex = 0;

        while (startIndex < start.length) {
            if (start[startIndex] < end[endIndex]) {
                return false;
            }

            startIndex++;
            endIndex++;
        }
        return true;
    }

    // https://leetcode.com/problems/meeting-rooms/solution/
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Q0252_MeetingRooms sol = new Q0252_MeetingRooms();
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        boolean canAttendAll = sol.canAttendMeetings(intervals);

        Interval[] intervals2 = new Interval[3];
        intervals2[0] = new Interval(15,20);
        intervals2[1] = new Interval(8,12);
        intervals2[2] = new Interval(2,6);
        boolean canAttendAll2 = sol.canAttendMeetings(intervals2);
    }
}
