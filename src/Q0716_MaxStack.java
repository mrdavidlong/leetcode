import java.util.Stack;

/*
https://leetcode.com/problems/max-stack/
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:

MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:

-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */
public class Q0716_MaxStack {

    /**
     * Your MaxStack object will be instantiated and called as such:
     * MaxStack obj = new MaxStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.peekMax();
     * int param_5 = obj.popMax();
     */
    // https://leetcode.com/problems/max-stack/discuss/108938/Java-AC-solution
//    class MaxStack {
//        Stack<Integer> stack;
//        Stack<Integer> maxStack;
//
//        /**
//         * initialize your data structure here.
//         */
//        public MaxStack() {
//            stack = new Stack<>();
//            maxStack = new Stack<>();
//        }
//
//        public void push(int x) {
//            pushHelper(x);
//        }
//
//        public void pushHelper(int x) {
//            int tempMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
//            if (x > tempMax) {
//                tempMax = x;
//            }
//            stack.push(x);
//            maxStack.push(tempMax);
//        }
//
//        public int pop() {
//            maxStack.pop();
//            return stack.pop();
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int peekMax() {
//            return maxStack.peek();
//        }
//
//        public int popMax() {
//            int max = maxStack.peek();
//            Stack<Integer> temp = new Stack<>();
//
//            while (stack.peek() != max) {
//                temp.push(stack.pop());
//                maxStack.pop();
//            }
//            stack.pop();
//            maxStack.pop();
//            while (!temp.isEmpty()) {
//                int x = temp.pop();
//                pushHelper(x);
//            }
//            return max;
//        }
//    }

    // https://leetcode.com/problems/max-stack/solution/
    static class MaxStack {
        Stack<Integer> stack;
        Stack<Integer> maxStack;

        public MaxStack() {
            stack = new Stack();
            maxStack = new Stack();
        }

        public void push(int x) {
            int max = maxStack.isEmpty() ? x : maxStack.peek();
            maxStack.push(max > x ? max : x);
            stack.push(x);
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = peekMax();
            Stack<Integer> buffer = new Stack();
            while (top() != max) buffer.push(pop());
            pop();
            while (!buffer.isEmpty()) push(buffer.pop());
            return max;
        }
    }

    public static void main(String[] args) {
        int value = 0;
//        MaxStack stack = new MaxStack();
//        stack.push(5);
//        stack.push(1);
//        stack.push(5);
//        value = stack.top(); // 5
//        value = stack.popMax(); // 5
//        value = stack.top(); // 1
//        value = stack.peekMax(); // 5
//        value = stack.pop(); // 1
//        value = stack.top(); // 5

        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(6);
        stack.push(4);
        value = stack.popMax();
        value = stack.popMax();
        value = stack.popMax();
        value = stack.popMax();
    }
}
