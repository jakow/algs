import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class StronglyConnectedComponents {
    static List<List<Integer>> findSCC(Graph graph) {
        // 1. Reverse the graph
        Graph reversed = graph.reverse();
        // 2. Find finishing times using the reverse graph
        Map<Integer, Integer> finishingTimes = new HashMap<>();
        int maxFinishingTime = findFinishingTimes(reversed, finishingTimes);
        assert maxFinishingTime == graph.size();
        // iterate in order of finishingTimes
        // 3. Run dfs loop starting from leaders and find sccs
        return dfsGetSccsLoop(graph, finishingTimes, maxFinishingTime);
    }

    public static int findFinishingTimes(Graph g, Map<Integer, Integer> finishingTimes) {
        int finishingTime = 0;
        for (int vertex : g.vertices()) {
            if (!g.wasVisited(vertex)) {
                finishingTime = dfsFinishingTimes(g, vertex, finishingTime, finishingTimes);
            }
        }
        return finishingTime;
    }

    public static int dfsFinishingTimes(Graph g,
                                        int vertex, int finishingTime,
                                        Map<Integer, Integer> finishingTimes
                                        ) {
        g.markVisited(vertex);
        for (int adj : g.getEdges(vertex)) {
            if (!g.wasVisited(adj)) {
                finishingTime = dfsFinishingTimes(g, adj, finishingTime, finishingTimes);
            }
        }
        finishingTime++;
        finishingTimes.put(finishingTime, vertex);
        return finishingTime;
    }


    public static List<List<Integer>> dfsGetSccsLoop(Graph g, Map<Integer, Integer> finishingTimes, int maxFinishingTime) {
        List<List<Integer>> sccList = new ArrayList<>();
        for (int finishingTime = maxFinishingTime; finishingTime >= 1; --finishingTime) {
            int leader = finishingTimes.get(finishingTime);
            if (!g.wasVisited(leader)) {
                List<Integer> sccs = new ArrayList<>();
                dfsGetSccs(g, leader, sccs);
                sccList.add(sccs);
            }
        }
        return sccList;
    }

    public static void dfsGetSccs(Graph g, int vertex, List<Integer> sccs) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            vertex = stack.pop();
            if (!g.wasVisited(vertex)) {
                g.markVisited(vertex);
                sccs.add(vertex);
                for (int adj : g.getEdges(vertex)) {
                    stack.push(adj);
                }
             }
        }
//        g.markVisited(vertex);
//        sccs.add(vertex);
//        for (int adj : g.getEdges(vertex)) {
//            if (!g.wasVisited(adj)) {
//                dfsGetSccs(g, adj, sccs);
//            }
//        }
    }

    public static void main(String[] args) {
        File file = new File("data/data.txt");
        Graph graph = Graph.fromFile(file);
        System.out.println("Graph built.");
        if (graph == null) {
            return;
        }
        long sizeBytes = 0;
        for (int vertex : graph.vertices()) {
            sizeBytes += 4;
            sizeBytes += 4 * graph.getEdges(vertex).size();
        }
        System.out.println(String.format("Approximate size is %f MB.", ((double) sizeBytes) / Math.pow(10, 6)));
        List<List<Integer>> sccs = findSCC(graph);
        System.out.println("Done. Strongly components are are:");
        List<Integer> sccSizes = sccs.stream()
                .map(List::size)
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Sizes = " + sccSizes);
    }
}
