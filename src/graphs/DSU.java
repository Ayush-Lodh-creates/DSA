package graphs;

public class DSU {

    int[] parent;
    int[] size;

    DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findUParent(int node) {
        if(node == parent[node]) {
            return node;
        }
        int ulp = findUParent(parent[node]);
        parent[node] = ulp;
        return ulp;
    }

    public void unionBySize(int u, int v) {
        int ulpU = findUParent(u);
        int ulpV = findUParent(v);
        if(ulpU == ulpV) return;
        if(size[ulpU] > size[ulpV]) {
            parent[ulpV] = ulpU;
            size[ulpU] = size[ulpU] + size[ulpV];
        } else {
            parent[ulpU] = ulpV;
            size[ulpV] = size[ulpV] + size[ulpU];
        }
    }
}
