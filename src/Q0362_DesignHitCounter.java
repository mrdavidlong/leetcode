import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/design-hit-counter/
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
Follow up:
What if the number of hits per second could be very large? Does your design scale?
 */
public class Q0362_DesignHitCounter {
    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     *
     *
     */

//    class HitCounter {
//
//        /** Initialize your data structure here. */
//        public HitCounter() {
//
//        }
//
//        /** Record a hit.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public void hit(int timestamp) {
//
//        }
//
//        /** Return the number of hits in the past 5 minutes.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public int getHits(int timestamp) {
//
//        }
//    }

    /*
    https://leetcode.com/problems/design-hit-counter/discuss/83483/Super-easy-design-O(1)-hit()-O(s)-getHits()-no-fancy-data-structure-is-needed!
    O(s) s is total seconds in given time interval, in this case 300.
basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds. hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time. If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.
     */
//    public static class HitCounter {
//        private int[] times;
//        private int[] hits;
//        private final int period = 300; // 5 minutes
//        /** Initialize your data structure here. */
//        public HitCounter() {
//            times = new int[period];
//            hits = new int[period];
//        }
//
//        /** Record a hit.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public void hit(int timestamp) {
//            int index = timestamp % period;
//            if (times[index] != timestamp) {
//                times[index] = timestamp;
//                hits[index] = 1;
//            } else {
//                hits[index]++;
//            }
//        }
//
//        /** Return the number of hits in the past 5 minutes.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public int getHits(int timestamp) {
//            int total = 0;
//            for (int i = 0; i < period; i++) {
//                if (timestamp - times[i] < period) {
//                    total += hits[i];
//                }
//            }
//            return total;
//        }
//    }

    // https://leetcode.com/problems/design-hit-counter/solution/
    public static class HitCounter {
        private Queue<Integer> hits;

        /** Initialize your data structure here. */
        public HitCounter() {
            this.hits = new LinkedList<Integer>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            this.hits.add(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while (!this.hits.isEmpty()) {
                int diff = timestamp - this.hits.peek(); // peek the head of the queue, i.e. the oldest one
                if (diff >= 300) {
                    this.hits.remove(); // removes the head of the queue, i.e. the oldest one
                } else {
                    break;
                }
            }
            return this.hits.size();
        }
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

// hit at timestamp 1.
        counter.hit(1);

// hit at timestamp 2.
        counter.hit(2);

// hit at timestamp 3.
        counter.hit(3);

// get hits at timestamp 4, should return 3.
        int hits = counter.getHits(4);

// hit at timestamp 300.
        counter.hit(300);

// get hits at timestamp 300, should return 4.
        int hits2 = counter.getHits(300);

// get hits at timestamp 301, should return 3.
        int hits3 = counter.getHits(301);
    }
}
