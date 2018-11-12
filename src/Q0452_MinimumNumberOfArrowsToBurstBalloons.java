import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by davidlong on 7/5/18.
 */
public class Q0452_MinimumNumberOfArrowsToBurstBalloons {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int cnt = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        return cnt;
    }

    public static void main(String[] args) {
        Q0452_MinimumNumberOfArrowsToBurstBalloons sol = new Q0452_MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {{10,16}, {2,8}, {1,6}, {7,12}};

        int minShot = sol.findMinArrowShots(points);
    }
}
