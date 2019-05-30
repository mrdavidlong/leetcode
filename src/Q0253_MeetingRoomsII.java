import common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/description/
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1


 * Definition for an interval. public class Interval { int start; int end; Interval() { start = 0;
 * end = 0; } Interval(int s, int e) { start = s; end = e; } }

 */
public class Q0253_MeetingRoomsII {
    //https://leetcode.com/problems/meeting-rooms-ii/solution/
    public int minMeetingRoomsPQ(Interval[] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
//        PriorityQueue<Integer> allocator =
//                new PriorityQueue<Integer>(
//                        intervals.length,
//                        new Comparator<Integer>() {
//                            public int compare(Integer a, Integer b) {
//                                return a - b;
//                            }
//                        });

        PriorityQueue<Integer> allocator =
                new PriorityQueue<>(
                        intervals.length,
                        (a, b) -> a - b);

        // Sort the intervals by start time
//        Arrays.sort(
//                intervals,
//                new Comparator<Interval>() {
//                    public int compare(Interval a, Interval b) {
//                        return a.start - b.start;
//                    }
//                });
        Arrays.sort(
                intervals,
                (a, b) -> a.start - b.start);


        // Add the first meeting
        allocator.add(intervals[0].end);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i].start >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i].end);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }


    public int minMeetingRooms(Interval[] intervals) {
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }

        // Sort the intervals by start time
        Arrays.sort(start);

        // Sort the intervals by end time
        Arrays.sort(end);

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;

        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;

        }

        return usedRooms;
    }

    public static void main(String[] args) {
        Q0253_MeetingRoomsII sol = new Q0253_MeetingRoomsII();

        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0, 30);
        intervals[1] = new Interval(5, 10);
        intervals[2] = new Interval(15, 20);
        int minMeetingRooms = sol.minMeetingRooms(intervals);


        Interval[] intervals2 = new Interval[6];
        intervals2[0] = new Interval(1, 10);
        intervals2[1] = new Interval(2, 7);
        intervals2[2] = new Interval(3, 19);
        intervals2[3] = new Interval(8, 12);
        intervals2[4] = new Interval(10, 20);
        intervals2[5] = new Interval(11, 30);
        int minMeetingRooms2 = sol.minMeetingRooms(intervals2);
    }

}
