import graph.Graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MinCuts {
    int minCut;

    MinCuts() {
        minCut = Integer.MAX_VALUE;
    }
    public static void main(String[] args) {
        MinCuts m = new MinCuts();
        File f = new File("data/kargerMinCut.txt");

        Graph g = Graph.fromFile(f);
        int n = g.vertices.size();
        int numRuns = (int) (n * n * Math.log(n));

        System.out.print(String.format("Number of vertices is %d. ", g.vertices.size()));
        System.out.println(String.format("MinCut procedure will be applied %d times.", numRuns));

        int processors = Runtime.getRuntime().availableProcessors();
        Counter counter = new Counter();
        Object lock = new Object();

        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Callable<Integer> task = () -> {
            Graph graph = g.clone();
            int k = randomMinCut(graph);
            counter.increment();
            int value = counter.getValue();
            if (value % 1000 == 0) {
                System.out.println(String.format("Progress %d/%d", value, numRuns));
            }

            synchronized (lock) {
                m.minCut = Math.min(m.minCut, k);
            }

            return k;
        };

        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < numRuns; ++i) {
            tasks.add(task);
        }

        try {
            executor.invokeAll(tasks);
            executor.shutdown();
            System.out.println(String.format("Final min cut is %d", m.minCut));
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
    }

    public static int randomMinCut(Graph g) {
        while(g.vertices.size() > 2) {
            randomContract(g);
        }
        return g.edges.size() / 2;
    }

    public static void randomContract(Graph g) {
        int edgeIndex = (int) Math.round(Math.random() * (g.edges.size() - 1));
        g.contract(g.edges.get(edgeIndex));
    }

    static class Counter {
        private int value;

        synchronized void increment() {
            value++;
        }

        synchronized int getValue() {
            return value;
        }
    }
}
