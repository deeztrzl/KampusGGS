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
//new code here
}
