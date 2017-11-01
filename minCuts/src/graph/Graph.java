package graph;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    public Map<Integer, List<Edge>> vertices;
    public List<Edge> edges;

    Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public static Graph fromFile(File f) {
        Graph g = new Graph();
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (IOException e) {
            return g;
        }
        while (s.hasNextLine()) {
            Scanner line = new Scanner(s.nextLine());
            int v1 = line.nextInt();
            List<Edge> v1Edges = new ArrayList<>();
            while (line.hasNextInt()) {
                int v2 = line.nextInt();
                Edge e = new Edge(v1, v2);
                v1Edges.add(e);
                g.edges.add(e);
            }
            g.vertices.put(v1, v1Edges);
        }
        return g;
    }

    public Graph clone() {
        Graph g = new Graph();
        for (int v : vertices.keySet()) {
            List<Edge> vEdges = new ArrayList<>();
            for (Edge e : vertices.get(v)) {
                Edge f = new Edge(e.v1, e.v2);
                vEdges.add(f);
                g.edges.add(f);
            }
            g.vertices.put(v, vEdges);
        }
        return g;
    }

    public void contract(Edge e) {
        int kept = e.v1;
        List<Edge> keptEdges = vertices.get(kept);
        int discarded = e.v2;
        List<Edge> discardedEdges = vertices.get(discarded);
        // point edges adjacent to the discarded vertex to the kept vertex
        for (Edge f : discardedEdges) {
            int other = f.other(discarded);
            for (Edge g : vertices.get(other)) {
                if (g.v1 == discarded) {
                    g.v1 = kept;
                }
                if (g.v2 == discarded) {
                    g.v2 = kept;
                }
            }
        }

        // point edges incident on the discarded vertex to the kept vertex
        // and copy to kept vertex
        for (Edge g : discardedEdges) {
            if (g.v1 == discarded) {
                g.v1 = kept;
            } else if (g.v2 == discarded) {
                g.v2 = kept;
            }
            keptEdges.add(g);
        }

        vertices.remove(discarded);
        vertices.put(kept, removeSelfLoops(keptEdges));
        edges = removeSelfLoops(edges);
    }

    public static List<Edge> removeSelfLoops(List<Edge> edges) {
        return edges.stream().filter((e) -> e.v1 != e.v2).collect(Collectors.toList());
    }

}
