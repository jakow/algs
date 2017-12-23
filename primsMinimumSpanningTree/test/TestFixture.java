import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TestFixture {
    static List<Vertex> graph() {
        List<Vertex> expectedGraph = new ArrayList<>();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Edge e1 = new Edge(v1, v2, 1);
        v1.edges.add(e1);
        v2.edges.add(e1);
        Edge e2 = new Edge(v2, v3, 2);
        v2.edges.add(e2);
        v3.edges.add(e2);
        Edge e3 = new Edge(v3, v4, 3);
        v3.edges.add(e3);
        v4.edges.add(e3);
        Edge e4 = new Edge(v4, v1, 4);
        v4.edges.add(e4);
        v1.edges.add(e4);
        Edge e5 = new Edge(v4, v2, 5);
        v4.edges.add(e5);
        v2.edges.add(e5);
        expectedGraph.add(v1);
        expectedGraph.add(v2);
        expectedGraph.add(v3);
        expectedGraph.add(v4);
        return expectedGraph;
    }

    /*
      1
   1-----2
 4 | 5/  | 2
   4-----3
      3
 */
    static List<EdgeInput> input() {
        List<EdgeInput> input = new ArrayList<>();
        EdgeInput ei1 = new EdgeInput(1, 2, 1);
        EdgeInput ei2 = new EdgeInput(2, 3, 2);
        EdgeInput ei3 = new EdgeInput(3, 4, 3);
        EdgeInput ei4 = new EdgeInput(4, 1, 4);
        EdgeInput ei5 = new EdgeInput(4, 2, 5);
        input.add(ei1);
        input.add(ei2);
        input.add(ei3);
        input.add(ei4);
        input.add(ei5);
        return input;
    }

    static List<Edge> mst() {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Edge e1 = new Edge(v1, v2, 1);
        Edge e2 = new Edge(v2, v3, 2);
        Edge e3 = new Edge(v3, v4, 3);
        return Stream.of(e1, e2, e3).sorted(sortComparator()).collect(Collectors.toList());
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
