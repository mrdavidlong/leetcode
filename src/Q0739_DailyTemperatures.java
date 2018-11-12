import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/daily-temperatures/description/
 *
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

 For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

 Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].


 */
public class Q0739_DailyTemperatures {
//    public int[] dailyTemperatures(int[] temperatures) {
//        int n = temperatures.length;
//        int[] dist = new int[n];
//        Stack<Integer> indices = new Stack<>();
//        for (int curIndex = 0; curIndex < n; curIndex++) {
//            while (!indices.isEmpty() && temperatures[curIndex] > temperatures[indices.peek()]) {
//                int preIndex = indices.pop();
//                dist[preIndex] = curIndex - preIndex;
//            }
//            indices.add(curIndex);
//        }
//        return dist;
//    }

    public int[] dailyTemperatures1(int[] T) {
        int[] ans = new int[T.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) {
            int warmer_index = Integer.MAX_VALUE;
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmer_index)
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i;
            next[T[i]] = i;
        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            // remove all numbers on the stack that are smaller or equal to the current temp.
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0739_DailyTemperatures sol = new Q0739_DailyTemperatures();

        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] warmDays1 = sol.dailyTemperatures1(temperatures);
        int[] warmDays2 = sol.dailyTemperatures2(temperatures);
    }
}
