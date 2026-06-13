package graphs;

import java.util.Arrays;

public class BellmanFordAlgorithm {

    public static void main(String[] args) {
        int nodes = 6;
        int[][] edges = {{0, 1, 1}, {1, 2, 2}, {1, 3, -2}, {2, 5, 4}, {3, 4, -3}, {4, 5, 4}, {5, 3, 2}};
        int[] shortestPath = bellmanFord(nodes, edges, 0);
        for(int i : shortestPath) {
            System.out.print(i + "\t");
        }
    }

    public static int[] bellmanFord(int nodes, int[][] edges, int source) {
        int[] shortestPath = new int[nodes];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;
        for(int i=0; i<nodes-1; i++) {
            for(int[] edge : edges) {
                int node = edge[0];
                int next = edge[1];
                int weight = edge[2];
                if(shortestPath[node] != Integer.MAX_VALUE && shortestPath[node] + weight < shortestPath[next]) {
                    shortestPath[next] = shortestPath[node] + weight;
                }
            }
        }
        return shortestPath;
    }
}
