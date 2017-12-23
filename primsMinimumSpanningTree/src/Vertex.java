import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    final int key;
    final List<Edge> edges;
    Edge minEdge;
    long minWeight;

    Vertex(int key) {
        this.key = key;
        this.edges = new ArrayList<>();
        this.minEdge = null;
        this.minWeight = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Vertex that) {
        return Long.compare(this.minWeight, that.minWeight);
    }

    @Override
    public String toString() {
        return String.format("Vertex{%d}", key);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vertex)) return false;
        return ((Vertex) obj).key == this.key;
    }
}
