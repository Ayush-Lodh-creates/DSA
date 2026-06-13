package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CycleDetectionUndirectedDFS {

    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {4, 5}, {3, 5}};
        int nodes = 6;
        boolean detectCycle = detectCycle(nodes, matrix);
        System.out.println("Does cycle exist : " + detectCycle);
    }

    public static boolean detectCycle(int nodes, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[nodes];
        for(int i=0; i<nodes; i++) {
            if(!visited[i]) {
                if(dfs(i, -1, adjList, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int node, int parent, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        List<Integer> adjNodes = adjList.getOrDefault(node, new ArrayList<>());
        for(Integer next : adjNodes) {
            if(next != parent) {
                if(visited[next]) {
                    return true;
                }
                if(dfs(next, node, adjList, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
