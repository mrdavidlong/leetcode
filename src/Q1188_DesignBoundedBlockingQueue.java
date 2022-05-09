import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;


import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Q1188_DesignBoundedBlockingQueue {
    class BoundedBlockingQueue {
        private Semaphore enq;
        private Semaphore deq;
        ConcurrentLinkedDeque<Integer> q;

        public BoundedBlockingQueue(int capacity) {
            q =  new ConcurrentLinkedDeque<>();
            enq = new Semaphore(capacity);
            deq = new Semaphore(0);
        }

        public void enqueue(int element) throws InterruptedException {
            enq.acquire();
            q.add(element);
            deq.release();
        }

        public int dequeue() throws InterruptedException {
            deq.acquire();
            int val = q.poll();
            enq.release();
            return val;
        }

        public int size() {
            return q.size();
        }
    }

    class BoundedBlockingQueue2 {
        private ReentrantLock lock = new ReentrantLock();
        private Condition full = lock.newCondition();
        private Condition empty = lock.newCondition();
        private int[] queue;
        private int tail = 0;
        private int head = 0;
        private int size = 0;
        public BoundedBlockingQueue2(int capacity) {
            queue = new int[capacity];
        }

        public void enqueue(int element) throws InterruptedException {
            lock.lock();
            try {
                while (size == queue.length) {
                    full.await();
                }
                queue[tail++] = element;
                tail %= queue.length;
                size++;
                empty.signal();
            } finally {
                lock.unlock();
            }
        }

        public int dequeue() throws InterruptedException {
            lock.lock();
            try {
                while (size == 0) {
                    empty.await();
                }
                int res = queue[head++];
                head %= queue.length;
                size--;
                full.signal();
                return res;
            } finally {
                lock.unlock();
            }
        }

        public int size() throws InterruptedException {
            lock.lock();
            try {
                return this.size;
            } finally {
                lock.unlock();
            }
        }
    }
}
