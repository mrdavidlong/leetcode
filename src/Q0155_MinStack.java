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

//    static class MinStack {
//
//        private Stack<Integer> dataStack;
//        private Stack<Integer> minStack;
//        private int min;
//
//        public MinStack() {
//            dataStack = new Stack<>();
//            minStack = new Stack<>();
//            min = Integer.MAX_VALUE;
//        }
//
//        public void push(int x) {
//            dataStack.add(x);
//            min = Math.min(min, x);
//            minStack.add(min);
//        }
//
//        public void pop() {
//            dataStack.pop();
//            minStack.pop();
//            min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
//        }
//
//        public int top() {
//            return dataStack.peek();
//        }
//
//        public int getMin() {
//            return minStack.peek();
//        }
//    }

    static class MinStack {
        Stack<Integer> stack = new Stack<>();
        int min=Integer.MAX_VALUE;

        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        public void pop() {
            if (stack.peek() == min) {
                stack.pop();
                min = stack.pop();
            } else {
                stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(5);
        int min1 = minStack.getMin();
        minStack.push(1);
        int min2 = minStack.getMin();
        minStack.push(3);
        int min3 = minStack.getMin();
        minStack.push(7);
        int min4 = minStack.getMin();

        minStack.pop();
        int min5 = minStack.getMin();
        minStack.pop();
        int min6 = minStack.getMin();
        minStack.pop();
        int min7 = minStack.getMin();
        minStack.pop();
        int min8 = minStack.getMin();
        minStack.pop();
        int min9 = minStack.getMin();


    }
}
