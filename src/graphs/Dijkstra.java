package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public int[] dijkstra(int v, List<List<List<Integer>>> adj, int s) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.first - b.first);
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        pq.add(new Pair(0, s));
        while(!pq.isEmpty()) {
            int dis = pq.peek().first;
            int node = pq.peek().second;
            pq.poll();
            for(int i=0; i<adj.get(node).size(); i++) {
                int edgeWeight = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);

                if(dis + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = dis + edgeWeight;
                    pq.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }
}
