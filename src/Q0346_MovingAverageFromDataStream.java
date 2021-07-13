/*
https://leetcode.com/problems/moving-average-from-data-stream/
 */
public class Q0346_MovingAverageFromDataStream {
    public static class MovingAverage {
        private int [] window;
        private int n, insertIndex;
        private long sum;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            window = new int[size];
            insertIndex = 0;
            sum = 0;
        }

        public double next(int val) {
            if (n < window.length) n++;
            sum -= window[insertIndex];
            sum += val;
            window[insertIndex] = val;
            insertIndex = (insertIndex + 1) % window.length;

            return (double)sum / n;
        }
    }

    class MovingAverage2 {
        int size, head = 0, windowSum = 0, count = 0;
        int[] queue;
        public MovingAverage2(int size) {
            this.size = size;
            queue = new int[size];
        }

        public double next(int val) {
            ++count;
            // calculate the new sum by shifting the window
            int tail = (head + 1) % size;
            windowSum = windowSum - queue[tail] + val;
            // move on to the next head
            head = (head + 1) % size;
            queue[head] = val;
            return windowSum * 1.0 / Math.min(size, count);
        }
    }

    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        double avg1 = m.next(1); // 1
        double avg2 = m.next(10); // = (1 + 10) / 2
        double avg3 = m.next(3); // = (1 + 10 + 3) / 3
        double avg4 = m.next(5); // = (10 + 3 + 5) / 3
    }
}
