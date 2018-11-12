package misc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20题解.md#bfs
 */
public class MinPathLength {
    private static class PointNode {
        int r, c, length;
        PointNode prev;

        public PointNode(int r, int c, int length, PointNode prev) {
            this.r = r;
            this.c = c;
            this.length = length;
            this.prev = prev;
        }
//
//        public Point getPoint() {
//            return new Point(r,c);
//        }

        public void printPath() {
            Stack<PointNode> stack = new Stack<>();
            PointNode cur = this;
            while (cur != null) {
                stack.push(cur);
                cur = cur.prev;
            }

            while (!stack.isEmpty()) {
                PointNode n = stack.pop();
                System.out.println("(" + n.r + "," + n.c + ")");
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof PointNode))
                return false;
            if (obj == this)
                return true;
            return this.r == ((PointNode) obj).r && this.c == ((PointNode) obj).c;
        }

        @Override
        public int hashCode() {
            //return Objects.hash(r, c);
            int prime = 31;
            int result = 1;
            result = prime * result + Objects.hash(r);
            result = prime * result + Objects.hash(c);
            return result;
        }
    }

    private static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int minPathLengthV1(int[][] grid, int tr, int tc) {
        final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        final int m = grid.length, n = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        Set<Point> set = new HashSet<>();
        Point cur = new Point(0, 0);
        queue.add(cur);
        set.add(cur);
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                cur = queue.poll();
                for (int[] d : direction) {
                    int nr = cur.r + d[0], nc = cur.c + d[1];
                    Point next = new Point(nr, nc);
                    if (next.r < 0 || next.r >= m
                            || next.c < 0 || next.c >= n
                            || grid[next.r][next.c] != 0
                            || set.contains(next)) {
                        continue;
                    }
                    if (next.r == tr && next.c == tc) {
                        return pathLength;
                    }
                    queue.add(next);
                    set.add(cur);
                }
            }
        }
        return -1;
    }

    public int minPathLength(int[][] grid, int tr, int tc) {
        final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        final int m = grid.length, n = grid[0].length;
        Queue<PointNode> queue = new LinkedList<>();
        Set<PointNode> set = new HashSet<>();
        PointNode cur = new PointNode(0, 0, 1, null);
        queue.add(cur);
        set.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int[] d : direction) {
                int nr = cur.r + d[0], nc = cur.c + d[1];
                PointNode next = new PointNode(nr, nc, cur.length + 1, cur);
                if (next.r < 0 || next.r >= m
                        || next.c < 0 || next.c >= n
                        || grid[next.r][next.c] == 0
                        || set.contains(next)) {
                    continue;
                }
                if (next.r == tr && next.c == tc) {
                    next.printPath();
                    return next.length;
                }
                queue.add(next);
                set.add(next);
            }
        }
        return -1;
    }

    public static int minPathLength2(int[][] grids, int tr, int tc) {
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grids.length, n = grids[0].length;
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, 1));
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                Position nextPos = new Position(pos.r + direction[i][0], pos.c + direction[i][1], pos.length + 1);
                if (nextPos.r < 0 || nextPos.r >= m || nextPos.c < 0 || nextPos.c >= n) continue;
                if (grids[nextPos.r][nextPos.c] != 1) continue;
                grids[nextPos.r][nextPos.c] = 0;
                if (nextPos.r == tr && nextPos.c == tc) return nextPos.length;
                queue.add(nextPos);
            }
        }
        return -1;
    }

    private static class Position {
        int r, c, length;
        public Position(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        PointNode n1 = new PointNode(0,0,1,null);
        PointNode n2 = new PointNode(0,0,2,null);
        PointNode n3 = new PointNode(1,0,2,null);
        int h1 = n1.hashCode();
        int h2 = n2.hashCode();
        int h3 = n3.hashCode();
//        boolean b1 = n1.equals(n2);
//        boolean b2 = n1.equals(n3);
//        HashSet<PointNode> set = new HashSet<>();
//        set.add(n1);
//        boolean c1 = set.contains(n1);
//        set.add(n2);
//        boolean c2 = set.contains(n2);
//        set.add(n3);
//        boolean c3 = set.contains(n3);
        int h1a = Objects.hash(0,0);
        int h2a = Objects.hash(1,0);
        int h3a = Objects.hash(null);




        MinPathLength sol = new MinPathLength();

        int[][] grid = {
            {1,1,0,1},
            {1,0,1,0},
            {1,1,1,1},
            {1,0,1,1}
        };
        int minPathLength = sol.minPathLength(grid, 3,3);

    }
}
