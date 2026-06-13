package graphs;

import java.util.*;

public class CycleDetectionUndirectedBFS {

    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {4, 5}};
        int nodes = 6;
        boolean detectCycle = detectCycle(nodes, matrix);
        System.out.println("Does cycle exist : " + detectCycle);
    }

    public static boolean detectCycle(int nodes, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k-> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[nodes];
        for(int i=0; i<nodes; i++) {
            if(!visited[i]) {
                if(bfs(i, adjList, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bfs(int node, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(node, -1));
        visited[node] = true;
        while (!queue.isEmpty()) {
            Pair currentNode = queue.poll();
            int curr = currentNode.first;
            int parent = currentNode.second;
            List<Integer> adjNodes = adjList.getOrDefault(curr, new ArrayList<>());
            for(Integer next : adjNodes) {
                if(next != parent) {
                    if(visited[next]) {
                        return true;
                    }
                    queue.add(new Pair(next, curr));
                    visited[next] = true;
                }
            }
        }
        return false;
    }
}
