/**
 * Created by davidlong on 7/2/18.
 */
public class Q0042_TrappingRainWater {
public int trap(int[] heights) {
    int[] leftMaxes = new int[heights.length];
    int leftMax = 0;
    for (int i = 0; i < heights.length; i++) {
        if (heights[i] > leftMax) {
            leftMax = heights[i];
        }
        leftMaxes[i] = leftMax;
    }

    int totalVol = 0;
    int rightMax = 0;
    int minLeftRightMax = 0;
    int delta = 0;
    for (int j = heights.length - 1; j >= 0; j--) {
        if (heights[j] > rightMax) {
            rightMax = heights[j];
        }
        minLeftRightMax = Math.min(rightMax, leftMaxes[j]);
        delta = minLeftRightMax - heights[j];
        if (delta > 0) {
            totalVol += delta;
        }
    }
    return totalVol;
}

    public static void main(String[] args) {
        Q0042_TrappingRainWater sol = new Q0042_TrappingRainWater();

        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int vol = sol.trap(heights);
    }
}
