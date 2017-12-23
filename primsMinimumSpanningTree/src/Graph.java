import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Graph {
    static List<Vertex> create(List<EdgeInput> edgeInputs) {
        // to lookup vertices
        Map<Integer, Vertex> vertices = new HashMap<>();
        for (EdgeInput e : edgeInputs) {
            Vertex head = vertices.getOrDefault(e.head, new Vertex(e.head));
            Vertex tail = vertices.getOrDefault(e.tail, new Vertex(e.tail));
            Edge edge = new Edge(head, tail, e.weight);
            head.edges.add(edge);
            tail.edges.add(edge);
            vertices.put(e.head, head);
            vertices.put(e.tail, tail);
        }
        return vertices.values()
                .stream()
                .sorted((v1, v2) -> v1.key - v2.key)
                .collect(Collectors.toList());
    }
}
