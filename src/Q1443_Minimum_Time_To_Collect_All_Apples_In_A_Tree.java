import java.util.*;

public class Q1443_Minimum_Time_To_Collect_All_Apples_In_A_Tree {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        buildTree(edges, map);
        Set<Integer> visited = new HashSet<>();
        return helper(0, map, hasApple, visited);
    }

    private int helper(int node, Map<Integer, List<Integer>> map, List<Boolean> hasApple, Set<Integer> visited) {

        visited.add(node);

        int res = 0;

        for (int child : map.getOrDefault(node, new LinkedList<>())) {
            if (visited.contains(child)) continue;
            res += helper(child, map, hasApple, visited);
        }

        if ((res > 0 || hasApple.get(node)) && node != 0) res += 2;

        return res;
    }

    private void buildTree(int[][] edges, Map<Integer, List<Integer>> map) {

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            map.putIfAbsent(a, new LinkedList<>());
            map.putIfAbsent(b, new LinkedList<>());
            map.get(a).add(b);
            map.get(b).add(a);
        }

    }

    public static void main(String[] args) {
        Q1443_Minimum_Time_To_Collect_All_Apples_In_A_Tree sol = new Q1443_Minimum_Time_To_Collect_All_Apples_In_A_Tree();
        int minTime = sol.minTime(4, new int[][] {{0,2}, {0,3}, {1,2}}, Arrays.asList(false,true,false,false));
    }
}
