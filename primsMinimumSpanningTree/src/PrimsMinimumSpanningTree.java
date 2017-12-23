import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PrimsMinimumSpanningTree {
    static List<Edge> compute(List<Vertex> graph) {
        List<Edge> spanningEdges = new ArrayList<>();
        if (graph.size() == 0) {
            return spanningEdges;
        }
        IndexHeap<Vertex> heap = new IndexHeap<>();
        Set<Vertex> spannedVertices = new HashSet<>();
        // initial item in the set
        Vertex first = graph.get(0);
        spannedVertices.add(first);
        updateVertexWeights(first, heap, spannedVertices);


        while (spannedVertices.size() < graph.size()) {
            Vertex min = heap.delMin();
            spanningEdges.add(min.minEdge);
            spannedVertices.add(min);
            updateVertexWeights(min, heap, spannedVertices);
        }
        return spanningEdges;
    }

    static List<Edge> computeSlow(List<Vertex> graph) {
        List<Edge> spanningEdges = new ArrayList<>();
        if (graph.size() == 0) {
            return spanningEdges;
        }
        Set<Vertex> spannedVertices = new HashSet<>();
        // pick first spanning vertex
        Vertex initialVertex = graph.get(0);
        spannedVertices.add(initialVertex);
        while(spannedVertices.size() < graph.size()) {
            Edge min = findMinCrossing(spannedVertices);
            Vertex next = spannedVertices.contains(min.head) ? min.tail : min.head;
            spannedVertices.add(next);
            spanningEdges.add(min);
        }
        return spanningEdges;
    }

    private static Edge findMinCrossing(Set<Vertex> spannedVertices) {
        Edge min = null;
        for (Vertex v : spannedVertices) {
            for (Edge e : v.edges) {
                if (!spannedVertices.contains(e.other(v)) && (min == null || e.weight < min.weight)) {
                    min = e;
                }
            }
        }
        return min;
    }

    static long weight(List<Edge> tree) {
        long sum = 0;
        for (Edge e : tree) {
            sum += e.weight;
        }
        return sum;
    }

    private static void updateVertexWeights(Vertex head, IndexHeap<Vertex> heap, Set<Vertex> visited) {
        for (Edge e : head.edges) {
            Vertex other = e.other(head);
            if (!visited.contains(other)) {
                // if already in heap, will have to update it
                boolean inHeap = heap.contains(other);
                if (inHeap) {
                    heap.delItem(other);
                }
                // either no min edge associated (first time in heap)
                // or need to update heap position
                if (!inHeap || e.weight < other.minWeight) {
                    other.minWeight = e.weight;
                    other.minEdge = e;
                }
                heap.insert(other);
            }
        }
    }
}
