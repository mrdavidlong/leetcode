import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/description/
 *
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

 Note:
 The number of people is less than 1,100.


 Example

 Input:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 Output:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 */
public class Q0406_QueueReconstructionByHeight {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        // [[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]
        List<int[]> peopleInOrder = new ArrayList<>();
        /*
        [[7,0]]
        [[7,0], [7,1]]
        [[7,0], [6,1], [7,1]]
        [[5,0], [7,0], [6,1], [7,1]]
        [[5,0], [7,0], [5,2], [6,1], [7,1]]
        [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
         */
        for (int[] p : people) {
            peopleInOrder.add(p[1], p);
        }
        return peopleInOrder.toArray(new int[peopleInOrder.size()][]);
    }

    public static void main(String[] args) {
        Q0406_QueueReconstructionByHeight sol = new Q0406_QueueReconstructionByHeight();

        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};

        int[][] peopleInOrder = sol.reconstructQueue(people);
    }
}
