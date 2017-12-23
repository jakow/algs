import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            List<Edge> mst;
            File f = new File("data/edges.txt");
            List<EdgeInput> edges = EdgeInput.fromScanner(new Scanner(f));
            List<Vertex> graph = Graph.create(edges);
            mst = PrimsMinimumSpanningTree.computeSlow(graph);
            System.out.println(PrimsMinimumSpanningTree.weight(mst));
            mst = PrimsMinimumSpanningTree.compute(graph);
            System.out.println(PrimsMinimumSpanningTree.weight(mst));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Comparator<Edge> sortComparator() {
        return (a, b) -> {
            // sort by weight
            if (a.weight - b.weight != 0) return Long.compare(a.weight, b.weight);
            // then break ties by head
            if (a.head.key - b.head.key != 0) return Long.compare(a.head.key, b.head.key);
            // then by tail
            return Long.compare(a.tail.key, b.tail.key);
        };
    }
}
