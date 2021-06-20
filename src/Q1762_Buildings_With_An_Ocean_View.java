import java.util.ArrayList;
import java.util.List;

/*
1762. Buildings With an Ocean View
Medium

148

24

Add to List

Share
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.



Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
Example 2:

Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
Example 3:

Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
Example 4:

Input: heights = [2,2,2,2]
Output: [3]
Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
 */
public class Q1762_Buildings_With_An_Ocean_View {
    public int[] findBuildings(int[] heights) {
        List<Integer> ls = new ArrayList<>();
        int last = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > last) {
                ls.add(i);
                last = heights[i];
            }
        }

        int index = 0;
        int[] res = new int[ls.size()];
        for (int i = ls.size() - 1; i >= 0; i--)
            res[index++] = ls.get(i);

        return res;
    }

    public static void main(String[] args) {
        Q1762_Buildings_With_An_Ocean_View sol = new Q1762_Buildings_With_An_Ocean_View();
        int[] views = sol.findBuildings(new int[] {4,2,3,1});
        int[] views2 = sol.findBuildings(new int[] {4,3,2,1});
        int[] views3 = sol.findBuildings(new int[] {1,3,2,4});
        int[] views4 = sol.findBuildings(new int[] {2,2,2,2});
        int i = 0;
    }
}
