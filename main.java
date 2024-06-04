import java.util.*
public class Main {

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    static void union(int[] parent, int[] ranking, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            if (ranking[rootX] > ranking[rootY]) {
                parent[rootY] = rootX;
            } else if (ranking[rootX] < ranking[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                ranking[rootX]++;
            }
        }
    }

    static int kruskal(int n, List<Edge> edges) {
        Collection.sort(edges); 

        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int totalMst = 0;
        int edgesTerpakai = 0;

        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;

            int setU = find(parent, u);
            int setV = find(parent, v);

            if (setU != setV) {
                totalMst += edge.weight;
                union(parent, rank, setU, setV);
                edgesTerpakai++;
            }
            
            if (edgesTerpakai == n - 1) break;
        }
        return edgesTerpakai == n - 1 ? totalMst : -1;
    }

    //new code here
}

