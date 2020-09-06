import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/description/
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:

 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.
 */
public class Q0155_MinStack {

    static class MinStack {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            if (x <= getMin()) {
                minStack.push(x);
            }
            dataStack.add(x);
        }

        public void pop() {
            if (top() == getMin()) {
                minStack.pop();
            }
            dataStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) return Integer.MAX_VALUE;
            else return minStack.peek();
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        int min1 = minStack.getMin();
        minStack.push(1);
        int min2 = minStack.getMin();
        minStack.push(0);
        int min3 = minStack.getMin();
        minStack.pop();
        int min5 = minStack.getMin();


//        MinStack minStack = new MinStack();
//        minStack.push(5);
//        int min1 = minStack.getMin();
//        minStack.push(1);
//        int min2 = minStack.getMin();
//        minStack.push(3);
//        int min3 = minStack.getMin();
//        minStack.push(7);
//        int min4 = minStack.getMin();
//
//        minStack.pop();
//        int min5 = minStack.getMin();
//        minStack.pop();
//        int min6 = minStack.getMin();
//        minStack.pop();
//        int min7 = minStack.getMin();
//        minStack.pop();
//        int min8 = minStack.getMin();
//        minStack.pop();
//        int min9 = minStack.getMin();


    }
}
