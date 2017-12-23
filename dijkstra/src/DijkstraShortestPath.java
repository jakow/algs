import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Compute shortest paths to each vertex using optimised Dijkstra's algorithm
 * using a heap
 */
public class DijkstraShortestPath {
    public static void main(String[] args) {
        Graph g = Graph.fromFile(new File("./data/graph.txt"));

        Map<Integer, Integer> shortestPaths = findShortestPath(g, 1);
        System.out.println(shortestPaths);

        int[] exampleVertices = new int[] {
            7, 37, 59, 82, 99, 115, 133, 165, 188, 197
        };
        for (int v : exampleVertices) {
            System.out.println(String.format("%d distance: %d", v, shortestPaths.get(v)));
        }
    }

    private static Map<Integer, Integer> findShortestPath(Graph g, int start) {
        TrackingHeap<Vertex> heap = new TrackingHeap<>();
        Map<Integer, Integer> shortestPaths = new HashMap<>();
        Vertex startVertex = g.getVertexForKey(start);
        startVertex.score = 0;
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVertex);
        int size = g.numberOfVertices();
        /*
         * 0. pre-processing:
         * Compute the initial scores of each vertex with tail in non-visited sets.
         * The Dijkstra's greedy score (DGS) is the following:
         *
         * DGS = the minimum distance of all possible paths from start vertex to the head vertex
         *
         * For vertices not-edges to the start vertex, the value is infinite (max int in our case)
         */
        // all vertices get max int value first
        for (Vertex v : g.vertices()) {
            if (v != startVertex) {
                v.score = Integer.MAX_VALUE;
            }
        }
        // edges from the starting point get simply their edge value
        for (Edge e : startVertex.edges) {
            e.to.score = e.weight;
        }
        // and then we insert all the unvisited items into the heap
        for (Vertex v : g.vertices()) {
            if (v != startVertex) {
                heap.insert(v);
            }
        }

        while (visited.size() < size) {
            // 1. Pick a vertex with a minimum dijkstra score
            Vertex v = heap.delMin();
            // 2. Mark as visited
            visited.add(v);
            // 2. Its score is the minimum distance
            shortestPaths.put(v.key, v.score);
            // 3. Update score of unvisited vertices adjacent to that vertex
            for (Edge e : v.edges) {
                Vertex to = e.to;
                if (visited.contains(to)) {
                    continue;
                }
                // 3.1 Reduce score if smaller
                to.score = Math.min(v.score + e.weight, to.score);
                // 3.2 reheapify that vertex
                heap.reheapify(to);
            }
        }
        // aaaand we're done!
        return shortestPaths;
    }


}
