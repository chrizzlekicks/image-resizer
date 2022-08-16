import java.util.Stack;

public class TopologicalWD {
    private WeightedDigraph G;
    private boolean[] marked;
    private int[] parent;
    private boolean[] onStack;
    private Stack<Integer> cycle;
    private Stack<Integer> reversePost;

    public TopologicalWD(WeightedDigraph G) {
        this.G = G;
        marked = new boolean[G.V()];
        parent = new int[G.V()];
        onStack = new boolean[G.V()];
        reversePost = new Stack<>();
    }

    // This method determines the reverse-postorder for a DAG, when
    // a source node v is given. Nodes that are not reachable from v
    // are not included in that order!
    // The method also checks for cycles that are reachble from start node v.
    public void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : G.incident(v)) {
            int w = e.to();
            if (hasCycle())
                return;
            else if (!marked[w]) {
                parent[w] = v;
                dfs(w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                cycle.push(w);
                for (int u = v; u != w; u = parent[u])
                    cycle.push(u);
                cycle.push(w);
                return;
            }
        }
        onStack[v] = false;
        reversePost.push(v);
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public Stack<Integer> order() {
        return reversePost;
    }
}

