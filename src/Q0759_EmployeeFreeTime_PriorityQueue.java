import common.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/employee-free-time/
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.
 */
public class Q0759_EmployeeFreeTime_PriorityQueue {
    /*
    https://leetcode.com/problems/employee-free-time/solution/

Approach #1: Events (Line Sweep) [Accepted]

Intuition

If some interval overlaps any interval (for any employee), then it won't be included in the answer. So we could reduce our problem to the following: given a set of intervals, find all places where there are no intervals.

To do this, we can use an "events" approach present in other interval problems. For each interval [s, e], we can think of this as two events: balance++ when time = s, and balance-- when time = e. We want to know the regions where balance == 0.

Algorithm

For each interval, create two events as described above, and sort the events. Now for each event occuring at time t, if the balance is 0, then the preceding segment [prev, t] did not have any intervals present, where prev is the previous value of t.

    class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee: avails)
            for (Interval iv: employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
        for (int[] event: events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0)
                ans.add(new Interval(prev, event[0]));
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
}


    Approach #2: Priority Queue [Accepted]

Intuition

Say we are at some time where no employee is working. That work-free period will last until the next time some employee has to work.

So let's maintain a heap of the next time an employee has to work, and it's associated job. When we process the next time from the heap, we can add the next job for that employee.

Algorithm

Keep track of the latest time anchor that we don't know of a job overlapping that time.

When we process the earliest occurring job not yet processed, it occurs at time t, by employee e_id, and it was that employee's e_jx'th job. If anchor < t, then there was a free interval Interval(anchor, t).
     */


    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> ans = new ArrayList();
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) ->
                avails.get(a.eid).get(a.index).start -
                        avails.get(b.eid).get(b.index).start);
        int ei = 0, anchor = Integer.MAX_VALUE;

        for (List<Interval> employee: avails) {
            pq.offer(new Job(ei++, 0));
            anchor = Math.min(anchor, employee.get(0).start);
        }

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval iv = avails.get(job.eid).get(job.index);
            if (anchor < iv.start)
                ans.add(new Interval(anchor, iv.start));
            anchor = Math.max(anchor, iv.end);
            if (++job.index < avails.get(job.eid).size())
                pq.offer(job);
        }

        return ans;
    }

    class Job {
        int eid, index;
        Job(int e, int i) {
            eid = e;
            index = i;
        }
    }

    public static void main(String[] args) {
        Q0759_EmployeeFreeTime_PriorityQueue sol = new Q0759_EmployeeFreeTime_PriorityQueue();
        List<List<Interval>> avails1 = new ArrayList<>();
        avails1.add(Arrays.asList(new Interval[] { new Interval(1, 2), new Interval(5, 6)}));
        avails1.add(Arrays.asList(new Interval[] { new Interval(1, 3)}));
        avails1.add(Arrays.asList(new Interval[] { new Interval(4, 10)}));
        List<Interval> freeTime1 = sol.employeeFreeTime(avails1); // [3,4]

        List<List<Interval>> avails2 = new ArrayList<>();
        avails2.add(Arrays.asList(new Interval[] { new Interval(1, 3), new Interval(6, 7)}));
        avails2.add(Arrays.asList(new Interval[] { new Interval(2, 4)}));
        avails2.add(Arrays.asList(new Interval[] { new Interval(2, 5), new Interval(9, 12)}));
        List<Interval> freeTime2 = sol.employeeFreeTime(avails2); //Output: [[5,6],[7,9]]

    }
}
