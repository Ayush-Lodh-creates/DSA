package dynamic_programming;

import java.util.Arrays;

public class JumpGameV {

    public int getMinCost(int i, int n, int[] arr, int[] cost, boolean[] visited) {
        if(i >= n) {
            return 0;
        }
        if(cost[i] != -1) {
            return cost[i];
        }
        visited[i] = true;
        if(i == 0) {
            return cost[i] = arr[i] + getMinCost(i+2, n, arr, cost, visited);
        }
        int front = getMinCost(i+2, n, arr, cost, visited);
        int back = Integer.MAX_VALUE;
        if(!visited[i-1]) {
            back = getMinCost(i-1, n, arr, cost, visited);
        }
        return cost[i] = arr[i] + Math.min(front, back);
    }

    public int minCostToCrossArray(int[] arr, int n) {
        int[] cost = new int[n];
        Arrays.fill(cost, -1);
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        return getMinCost(0, n, arr, cost, visited);
    }
}
