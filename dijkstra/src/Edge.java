/**
 * Created by jakub on 20/11/2017.
 */
public class Edge {
    Vertex from;
    Vertex to;
    int weight;

    Edge(Vertex from, Vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%d -{%d}-> %d", from.key, weight, to.key);
    }
}
