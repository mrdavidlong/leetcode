import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/description/
 *
 * Implement the following operations of a queue using stacks.

 push(x) -- Push element x to the back of queue.
 pop() -- Removes the element from in front of queue.
 peek() -- Get the front element.
 empty() -- Return whether the queue is empty.
 Example:

 MyQueue queue = new MyQueue();

 queue.push(1);
 queue.push(2);
 queue.peek();  // returns 1
 queue.pop();   // returns 1
 queue.empty(); // returns false
 Notes:

 You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

 */
public class Q0232_ImplementQueueUsingStacks {
    static class MyQueue {

        private Stack<Integer> in = new Stack<>();
        private Stack<Integer> out = new Stack<>();

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            in2out();
            return out.pop();
        }

        public int peek() {
            in2out();
            return out.peek();
        }

        private void in2out() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    public static void main(String[] args) {
        Q0232_ImplementQueueUsingStacks sol = new Q0232_ImplementQueueUsingStacks();
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        int val = q.pop();
        q.push(3);
        q.push(4);
        int val2 = q.pop();
        int val3 = q.pop();
        int val4 = q.pop();
        boolean isEmpty = q.empty();

    }
}
