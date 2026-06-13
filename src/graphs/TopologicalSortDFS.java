package graphs;

import java.util.*;

public class TopologicalSortDFS {

    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {0, 2}, {1, 4}, {1, 3}, {2, 5}, {3, 6}, {4, 6}, {5, 6}};
        int nodes = 7;
        int[] sortedNodes = topologicalSort(nodes, matrix);
        for(int node : sortedNodes) {
            System.out.print(node + "\t");
        }
    }

    public static int[] topologicalSort(int nodes, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[nodes];
        for(int i=0; i<nodes; i++) {
            if(!visited[i]) {
                dfs(i, stack, visited, adjList);
            }
        }
        int[] ansNodes = new int[stack.size()];
        int count = 0;
        while(!stack.isEmpty()) {
            ansNodes[count++] = stack.pop();
        }
        return ansNodes;
    }

    public static void dfs(int node, Stack<Integer> st, boolean[] visited, Map<Integer, List<Integer>> adjList) {
        visited[node] = true;
        List<Integer> adjNodes = adjList.getOrDefault(node, new ArrayList<>());
        for(Integer next : adjNodes) {
            if(!visited[next]) {
                dfs(next, st, visited, adjList);
            }
        }
        st.push(node);
    }
}
