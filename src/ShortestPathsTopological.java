import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        /* TODO */
        this.s = s;
        this.parent = new int[G.V()];
        this.dist = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            this.dist[i] = Double.POSITIVE_INFINITY;
        }
        this.dist[s] = 0.0;
        TopologicalWD t = new TopologicalWD(G);
        t.dfs(s);
        while (!t.order().isEmpty()) {
            int node = t.order().pop();
            for (DirectedEdge e : G.incident(node)) {
                relax(e);
            }
        }
    }

    public void relax(DirectedEdge e) {
        /* TODO */
        if (this.dist[e.to()] > this.dist[e.from()] + e.weight()) {
            this.dist[e.to()] = this.dist[e.from()] + e.weight();
            this.parent[e.to()] = e.from();
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

