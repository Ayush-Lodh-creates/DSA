package graphs;

import java.util.Map;

public class FloydWarshallAlgorithm {

    public static void main(String[] args) {
        int nodes = 6;
        int[][] edges = {{0, 1, 1}, {1, 2, 2}, {1, 3, -2}, {2, 5, 4}, {3, 4, -3}, {4, 5, 4}, {5, 3, 2}};
        int[][] shortestPath = floydWarshall(nodes, edges);
        for(int[] i : shortestPath) {
            for(int j : i)
                System.out.print(j + "\t");
            System.out.println();
        }
    }

    public static int[][] floydWarshall(int nodes, int[][] edges) {
        int[][] shortestPath = new int[nodes][nodes];
        for(int i=0; i<nodes; i++) {
            for(int j=0; j<nodes; j++) {
                if(i == j) {
                    shortestPath[i][j] = 0;
                } else {
                    shortestPath[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for(int[] edge : edges) {
            shortestPath[edge[0]][edge[1]] = edge[2];
        }
        for(int k=0; k<nodes; k++) {
            for(int i=0; i<nodes; i++) {
                for(int j=0; j<nodes; j++) {
                    if(shortestPath[i][k] != Integer.MAX_VALUE && shortestPath[k][j] != Integer.MAX_VALUE) {
                        shortestPath[i][j] = Math.min(shortestPath[i][j], shortestPath[i][k] + shortestPath[k][j]);
                    }
                }
            }
        }
        return shortestPath;
    }
}
