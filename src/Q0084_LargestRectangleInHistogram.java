import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 The largest rectangle is shown in the shaded area, which has area = 10 unit.

 Example:

 Input: [2,1,5,6,2,3]
 Output: 10
 */
public class Q0084_LargestRectangleInHistogram {

    // https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28900/O(n)-stack-based-JAVA-solution
    public int largestRectangleAreaOrig(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }


    // inspired by https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28900/O(n)-stack-based-JAVA-solution
    public int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        int i = 0;
        while (i <= len) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i++);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
            }
        }
        return maxArea;
    }

    // https://leetcode.com/problems/maximal-rectangle/discuss/29064/A-O(n2)-solution-based-on-Largest-Rectangle-in-Histogram
    public int largestRectangleArea2(int[] height) {
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= height.length; i++) {
            int h = (i == height.length) ? 0 : height[i];
            if (s.isEmpty() || height[s.peek()] <= h)
                s.push(i);
            else {
                while (!s.isEmpty() && h < height[s.peek()]) {
                    int top = s.pop();
                    maxArea = Math.max(maxArea, height[top] * (s.isEmpty() ? i : (i - s.peek() - 1)));
                }
                s.push(i);
            }
        }
        return maxArea;
    }

    // https://www.youtube.com/watch?v=ZmnqCZp9bBs
    // https://github.com/mission-peace/interview/blob/master/src/com/interview/stackqueue/MaximumHistogram.java
    public int maxHistogram(int input[]){
        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for (i=0; i < input.length;) {
            if (stack.isEmpty() || input[stack.peek()] <= input[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                //if stack is empty means everything till i has to be
                //greater or equal to input[top] so get area by
                //input[top] * i;
                if(stack.isEmpty()){
                    area = input[top] * i;
                }
                //if stack is not empty then everythin from i-1 to input.peek() + 1
                //has to be greater or equal to input[top]
                //so area = input[top]*(i - stack.peek() - 1);
                else{
                    area = input[top] * (i - stack.peek() - 1);
                }
                if(area > maxArea){
                    maxArea = area;
                }
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            //if stack is empty means everything till i has to be
            //greater or equal to input[top] so get area by
            //input[top] * i;
            if (stack.isEmpty()) {
                area = input[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else {
                area = input[top] * (i - stack.peek() - 1);
            }

            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Q0084_LargestRectangleInHistogram sol = new Q0084_LargestRectangleInHistogram();
        int maxArea = sol.largestRectangleArea(new int[] {2,1,2});
        int maxArea2 = sol.largestRectangleArea2(new int[]{2, 1, 2});
        int maxArea3 = sol.largestRectangleArea(new int[] {2,1,5,6,2,3});
        int maxArea4 = sol.largestRectangleArea2(new int[]{2, 1,5,6,2,3});
        int maxHistArea = sol.maxHistogram(new int[] {1,2,3,1});
    }
}
