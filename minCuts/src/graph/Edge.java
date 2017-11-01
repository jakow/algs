package graph;

public class Edge {
    public int v1;
    public int v2;

    Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int other(int v) {
        if (v == v1) {
            return v2;
        } else if (v == v2) {
            return v1;
        } else {
            throw new IllegalArgumentException("Edge does not connect a given vertex v");
        }
    }

    @Override
    public String toString() {
        return String.format("(%d -> %d)", v1, v2);
    }


}
