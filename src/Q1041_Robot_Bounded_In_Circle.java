public class Q1041_Robot_Bounded_In_Circle {
    /*
    Complexity Analysis
Time complexity: O(N), where N is a number of instructions to parse.
Space complexity: O(1) because the array directions contains only 4 elements.
     */
    public boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial position is in the center
        int x = 0, y = 0;
        // facing north
        int idx = 0;

        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }

        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }

    public static void main(String[] args) {
        Q1041_Robot_Bounded_In_Circle sol = new Q1041_Robot_Bounded_In_Circle();
        boolean isInCircle1 = sol.isRobotBounded("GRGL"); // no
        boolean isInCircle2 = sol.isRobotBounded("GGGLGLGLGG"); // yes
        boolean isInCircle3 = sol.isRobotBounded("GGGLGLGLGGL"); // no
        boolean isInCircle4 = sol.isRobotBounded("GRGGLL"); // yes

    }
}
