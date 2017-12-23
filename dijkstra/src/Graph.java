import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private final List<Edge> edges;

    public static Graph fromFile(File f) {
        Graph graph = new Graph();
        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(nextLine).useDelimiter(",|\\s");
                int start = lineScanner.nextInt();
                while (lineScanner.hasNextInt()) {
                    int end = lineScanner.nextInt();
                    int weight = lineScanner.nextInt();
                    graph.putEdge(start, end, weight);
                }
            }
            return graph;
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to create the graph: file not available.");
        }
    }

    Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int v : vertices.keySet()) {
            s.append(String.format("%d -> %s", v, vertices.get(v).edges.toString()));
            s.append('\n');
        }
        return s.toString();
    }

    public Vertex getVertexForKey(int key) {
        return vertices.get(key);
    }

    void putEdge(int from, int to, int weight) {
        putVertexIfDoesNotExists(from);
        putVertexIfDoesNotExists(to);
        Vertex fv = vertices.get(from);
        Vertex tv = vertices.get(to);
        Edge e = new Edge(fv, tv, weight);

        fv.edges.add(e);
        edges.add(e);
    }

    Collection<Vertex> vertices() {
        return vertices.values();
    }

    Collection<Edge> edges() {
        return edges;
    }

    private void putVertexIfDoesNotExists(int vertexKey) {
        if (!vertices.containsKey(vertexKey)) {
            // this may be a max or a min vertex
            vertices.put(vertexKey, new Vertex(vertexKey));
        }
    }

    public int numberOfVertices() {
        return vertices.size();
    }
}
