package graphs;

import java.util.*;

public class KahnsAlgorithm {

    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {0, 2}, {1, 4}, {3, 1}, {2, 5}, {6, 3}, {4, 6}, {5, 6}};
        int nodes = 7;
        List<Integer> sortedNodes = topologicalSort(nodes, matrix);
        for(Integer node : sortedNodes) {
            System.out.print(node + "\t");
        }
    }

    public static List<Integer> topologicalSort(int nodes, int[][] edges) {
        int[] indegree = new int[nodes];
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            indegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            ans.add(curr);
            List<Integer> adjNodes = adjList.getOrDefault(curr, new ArrayList<>());
            for(Integer adjNode : adjNodes) {
                indegree[adjNode]--;
                if(indegree[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }
        return ans;
    }
}
