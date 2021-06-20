import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/task-scheduler/description/
 *
 *
 Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.



 Example:

 Input: tasks = ['A','A','A','B','B','B'], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


 Note:

 The number of tasks is in the range [1, 10000].
 The integer n is in the range [0, 100].
 */
public class Q0621_TaskScheduler {
//    public int leastInterval(char[] tasks, int n) {
//        int[] map = new int[26];
//        for (char c: tasks)
//            map[c - 'A']++;
//        Arrays.sort(map);
//        int time = 0;
//        while (map[25] > 0) {
//            int i = 0;
//            while (i <= n) {
//                if (map[25] == 0)
//                    break;
//                if (i < 26 && map[25 - i] > 0)
//                    map[25 - i]--;
//                time++;
//                i++;
//            }
//            Arrays.sort(map);
//        }
//        return time;
//    }
//
//    public int leastIntervalPQ(char[] tasks, int n) {
//        int[] map = new int[26];
//        for (char c: tasks)
//            map[c - 'A']++;
//        PriorityQueue<Integer> queue = new PriorityQueue <> (26, Collections.reverseOrder());
//        for (int f: map) {
//            if (f > 0)
//                queue.add(f);
//        }
//        int time = 0;
//        while (!queue.isEmpty()) {
//            int i = 0;
//            List<Integer> temp = new ArrayList< >();
//            while (i <= n) {
//                if (!queue.isEmpty()) {
//                    if (queue.peek() > 1)
//                        temp.add(queue.poll() - 1);
//                    else
//                        queue.poll();
//                }
//                time++;
//                if (queue.isEmpty() && temp.size() == 0)
//                    break;
//                i++;
//            }
//            for (int l: temp)
//                queue.add(l);
//        }
//        return time;
//    }

    public int leastInterval(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }

        // max frequency
        int f_max = 0;
        for (int f : frequencies) {
            f_max = Math.max(f_max, f);
        }

        // count the most frequent tasks
        int n_max = 0;
        for (int f : frequencies) {
            if (f == f_max) n_max++;
        }

        return Math.max(tasks.length, (f_max - 1) * (n + 1) + n_max);
    }

    public static void main(String[] args) {
        Q0621_TaskScheduler sol = new Q0621_TaskScheduler();
        
        int leastInterval = sol.leastInterval(new char[] {'A','A','A','B','B','B'}, 2);
        int leastInterval2 = sol.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
        int leastInterval3 = sol.leastInterval(new char[] {'A','A','A','A','A','A','B','C','D','E'}, 2);
    }
}
