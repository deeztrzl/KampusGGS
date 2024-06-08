import java.util.*;
public class Main {

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        @Override
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
        Collections.sort(edges); 

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int weight = scanner.nextInt();
                edges.add(new Edge(u, v, weight));
            }

            int result = kruskal(n, edges);

            if (result == -1) {
                System.out.println("TIDAK MEMUNGKINKAN");
            } else {
                System.out.println(result);
            }
        }
        scanner.close();
    }

}

