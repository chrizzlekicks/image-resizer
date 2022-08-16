/**
 * A Class to represent a directed Edge
 */

public class DirectedEdge implements Comparable<DirectedEdge> {
    private int to;
    private int from;
    private double weight;
    
    public DirectedEdge(int v, int w, double weight) {
        this.from = v;
        this.to = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int either() {
        return from();
    }

    public int other(int v) {
        if (v == from())
            return to();
        else if (v == to())
            return from();
        else
            throw new RuntimeException("node is not part of this edge");
    }

    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight(), that.weight());
    }

    @Override
    public String toString() {
        return "(" + from + "->" + to + ")";
    }
}

