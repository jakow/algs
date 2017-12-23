import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 20/11/2017.
 */
public class Vertex implements Comparable<Vertex> {
    int key;
    int score;
    List<Edge> edges;

    Vertex(int key) {
        this.key = key;
        edges = new ArrayList<>();
    }

    @Override
    public int compareTo(Vertex that) {
        return this.score - that.score;
    }

    @Override
    public int hashCode() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vertex)) {
            return false;
        }
        return key == ((Vertex) obj).key;
    }
}
