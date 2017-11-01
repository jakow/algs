import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> visited;
    private int min;
    private int max;

    public static Graph fromFile(File f) {
        Graph graph = new Graph();
        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(nextLine);
                int start = lineScanner.nextInt();
                int end = lineScanner.nextInt();
                graph.putEdge(start, end);
            }
            return graph;
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to create the graph: file not available.");
        }
    }

    public static Graph fromFileReversed(File f) {
        Graph graph = new Graph();
        try {
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(nextLine);
                int start = lineScanner.nextInt();
                int end = lineScanner.nextInt();
                graph.putEdge(end, start);
            }
            return graph;
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to create the graph: file not available.");
        }
    }


    Graph() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        adjList = new HashMap<>();
        visited = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int v : adjList.keySet()) {
            s.append(String.format("%d -> %s", v, adjList.get(v).toString()));
            s.append('\n');
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Graph)) {
            return false;
        }
        Graph other = (Graph) obj;
        if (!this.vertices().equals(other.vertices())) {
            return false;
        }
        for (int vertex : vertices()) {
            Set<Integer> adj1 = this.getEdges(vertex).stream().collect(Collectors.toSet());
            Set<Integer> adj2 = other.getEdges(vertex).stream().collect(Collectors.toSet());
            if (!adj1.equals(adj2)) {
                return false;
            }
        }
        return true;
    }

    void putEdge(int from, int to) {
        putVertexIfDoesNotExists(from);
        putVertexIfDoesNotExists(to);
        List<Integer> adjacent = getEdges(from);
        adjacent.add(to);
    }

    List<Integer> getEdges(int vertex) {
        List<Integer> adj = adjList.get(vertex);
        if (adj == null) {
            throw new NoSuchElementException("Vertex " + vertex);
        }
        return adj;
    }

    Set<Integer> vertices() {
        return adjList.keySet();
    }

    int maxVertex() {
    return max;
    }

    int minVertex() {
        return min;
    }

    boolean hasVertex(int v) {
        return adjList.containsKey(v);
    }

    public void markVisited(int v) {
        visited.add(v);
    }

    public boolean wasVisited(int v) {
        return visited.contains(v);
    }

    public void clearVisited() {
        visited.clear();
    }

    Graph reverse() {
        Graph g = new Graph();
        for (int vertex : vertices()) {
            for (int adjacent : getEdges(vertex)) {
                g.putEdge(adjacent, vertex);
            }
        }
        return g;
    }

    private void putVertexIfDoesNotExists(int vertex) {
        if (!adjList.containsKey(vertex)) {
            // this may be a max or a min vertex
            max = Math.max(max, vertex);
            min = Math.min(min, vertex);
            adjList.put(vertex, new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        File file = new File("data/SCC.txt");
        Graph graph = Graph.fromFile(file);
        boolean equals = graph.reverse().equals(Graph.fromFileReversed(file));
        System.out.println(equals);
    }

    public int size() {
        return adjList.size();
    }
}
