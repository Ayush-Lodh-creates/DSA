package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CycleDetectionDirectedDFS {

    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {0, 2}, {1, 4}, {3, 1}, {2, 5}, {6, 3}, {4, 6}, {5, 6}};
        int nodes = 7;
        boolean containsCycle = detectCycle(nodes, matrix);
        System.out.println("Does contains cycle : " + containsCycle);
    }

    public static boolean detectCycle(int nodes, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        boolean[] visited = new boolean[nodes], pathVisited = new boolean[nodes];
        for(int i=0; i<nodes; i++) {
            if(!visited[i]) {
                if(dfs(i, adjList, visited, pathVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int node, Map<Integer, List<Integer>> adjList, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true;
        List<Integer> adjNodes = adjList.getOrDefault(node, new ArrayList<>());
        for(Integer next : adjNodes) {
            if(visited[next]) {
                return pathVisited[next];
            } else {
                if(dfs(next, adjList, visited, pathVisited)) {
                    return true;
                }
            }
        }
        pathVisited[node] = false;
        return false;
    }
}
