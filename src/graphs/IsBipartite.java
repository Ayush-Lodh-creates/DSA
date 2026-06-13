package graphs;

import java.util.*;

public class IsBipartite {
    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {4, 5}};
        int nodes = 6;
        boolean detectCycle = isBipartite(nodes, matrix);
        System.out.println("Is graph Bipartite : " + detectCycle);
    }

    public static boolean isBipartite(int nodes, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k-> new ArrayList<>()).add(edge[0]);
        }

        int[] color = new int[nodes];
        Arrays.fill(color, -1);
        for(int i=0; i<nodes; i++) {
            if(color[i] == -1) {
                if(!dfs(i, adjList, color, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(int node, Map<Integer, List<Integer>> adjList, int[] color, int currColor) {
        color[node] = currColor;
        List<Integer> adjNodes = adjList.getOrDefault(node, new ArrayList<>());
        for(Integer next : adjNodes) {
            int nextColor = currColor ^ 1;
            if(color[next] == -1) {
                if(!dfs(next, adjList, color, nextColor)) {
                    return false;
                }
            } else if(color[next] == color[node]) {
                return false;
            }
        }
        return true;
    }
}
