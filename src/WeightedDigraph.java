import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

public class WeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public WeightedDigraph(int V) {
        this.V = V;
        E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public WeightedDigraph(String filename) throws FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        V = in.nextInt();
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
        int nE = in.nextInt();
        for (int e = 0; e < nE; e++) {
            addEdge(in.nextInt(), in.nextInt(), in.nextDouble());
        }
    }

    // Be careful: Methods does not check, whether edge e exists already.
    // Do not add an edge more than once!
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        E++;
    }

    public void addEdge(int v, int w, double weight) {
        addEdge(new DirectedEdge(v, w, weight));
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> incident(int v) {
        return adj[v];
    }

    public Iterable<Integer> adj(int v) {
        Stack<Integer> adj = new Stack<>();
        for (DirectedEdge e : incident(v)) {
            adj.push(e.to());
        }
        return adj;
    }
}

