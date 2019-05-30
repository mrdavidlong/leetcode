import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
https://leetcode.com/problems/flatten-2d-vector/

Implement an iterator to flatten a 2d vector.

Example:

Input: 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
Output: [1,2,3,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,2,3,4,5,6].
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.


 */
public class Q0251_Flatten2DVector {

    public static class Vector2D implements Iterator<Integer> {
        /*
        https://leetcode.com/problems/flatten-2d-vector/discuss/67652/7-9-lines-added-Java-and-C%2B%2B-O(1)-space.
    Since the OJ does while (i.hasNext()) cout << i.next();, i.e., always calls hasNext before next, I don't really have to call it myself so I could save that line in next. But I think that would be bad, we shouldn't rely on that.
         */
        private Iterator<List<Integer>> i;
        private Iterator<Integer> j;

        public Vector2D(List<List<Integer>> vec2d) {
            i = vec2d.iterator();
        }

        @Override
        public Integer next() {
            hasNext();
            return j.next();
        }

        @Override
        public boolean hasNext() {
            while ((j == null || !j.hasNext()) && i.hasNext())
                j = i.next().iterator();
            return j != null && j.hasNext();
        }
    }


    public static void main(String[] args) {
        Q0251_Flatten2DVector sol = new Q0251_Flatten2DVector();
        List<List<Integer>> vec2d = new ArrayList<>();
        vec2d.add(Arrays.asList(new Integer[] {1,2}));
        vec2d.add(Arrays.asList(new Integer[] {3}));
        vec2d.add(Arrays.asList(new Integer[] {4,5,6}));

        Integer[] v = new Integer[6];
        int index = 0;
        Vector2D i = new Vector2D(vec2d);
        while (i.hasNext()) v[index++] = i.next();
        for (int j = 0; j < v.length; j++) {
            System.out.println(v[j]);
        }
    }


}
