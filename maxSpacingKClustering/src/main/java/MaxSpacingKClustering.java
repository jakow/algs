import java.io.File;
import java.io.IOException;
import java.util.*;

public class MaxSpacingKClustering {
    public static void main(String[] args) {
        Scanner s;
        try {
            s = new Scanner(new File("src/main/resources/clustering1.txt"));
        } catch (IOException e) {
            System.out.println("not found");
            return;
        }
        int nPoints = s.nextInt();
        List<Distance> distances = new ArrayList<>();
        while (s.hasNextInt()) {
            // convert to zero-based indices
            int from = s.nextInt() - 1;
            int to = s.nextInt() - 1;
            int distance = s.nextInt();
            distances.add(new Distance(from, to, distance));
        }
        List<Set<Integer>> result = computePoints(distances, nPoints, 4);
//        result.forEach((r) -> System.out.println(r.size()));
//        result.forEach(System.out::println);
    }

    static List<Set<Integer>> computePoints(List<Distance> distances, int nPoints, int nClusters) {
        // 1. compute used 'edges' using Kruskal's MST algorithm with early termination
        distances.sort(Comparator.comparingInt((d) -> d.distance));
//        System.out.println(distances);
        UnionFind uf = new UnionFind(nPoints);
        int i = 0;
        while (uf.count() > nClusters) {
            Distance d = distances.get(i);
            if (!uf.connected(d.from, d.to)) {
                uf.union(d.from, d.to);
            }
            i++;
        }
        Map<Integer, Set<Integer>> clusters = new HashMap<>();
        for (int point = 0; point < nPoints; ++point) {
            clusters.computeIfAbsent(uf.find(point), (key) -> new HashSet<>()).add(point);
        }
        // print distances
        System.out.println(clusters);
        System.out.println("Unconnected distances");
        distances.stream().filter(d -> !uf.connected(d.from, d.to)).forEach(System.out::println);
        return new ArrayList<>(clusters.values());

    }
}
