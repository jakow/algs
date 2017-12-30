import com.google.common.collect.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by jakub on 30/12/2017.
 */
public class HammingDistanceClustering {
    private static final char ASCII_ZERO = 48;

    public static void main(String[] args) {
        Scanner s;
        try {
            s = new Scanner(new File("src/main/resources/clustering_big.txt"));
        } catch (IOException e) {
            System.out.println("not found");
            return;
        }

        int nPoints = s.nextInt();
        s.nextInt();
        s.nextLine();
        // associate given values with indices where they occur
        SetMultimap<Integer, Integer> positions = HashMultimap.create(nPoints, 2);
        for (int i = 0; i < nPoints; ++i) {
            String line = s.nextLine();
            positions.put(parseBits(line), i);
        }

        System.out.println(getClustersForMinimumSpacing(positions, 3));
    }

    private static int getClustersForMinimumSpacing(Multimap<Integer, Integer> set, int spacing) {
        UnionFind uf = new UnionFind(set.size());
        // generate neighbours
        for (Map.Entry<Integer, Integer> vertex : set.entries()) {
            Set<Integer> neighbors = getNeighbors(vertex.getKey(), spacing - 1);
            for (Integer neighbor : neighbors) {
                for (Integer neighborIndex : set.get(neighbor)) {
                    uf.union(vertex.getValue(), neighborIndex);
                }
            }
        }
        return uf.count();
    }

    private static int parseBits(String line) {
        int num = 0;
        for (char c : line.toCharArray()) {
            if (c != '0' && c != '1') {
                continue;
            }
            int bit = c - ASCII_ZERO;
            num = (num << 1) | bit;
        }
        return num;
    }

    public static Set<Integer> getNeighbors(int num, int maxDistance) {
        Set<Integer> acc = new HashSet<>();
        getNeighbors(num, maxDistance, 0, acc);
        return acc;
    }

    private static void getNeighbors(int num, int distance, int bit, Set<Integer> acc) {
        if (distance < 0) return;
        if (bit == 24) {
            acc.add(num);
            return;
        }
        int toggle = 1 << bit;
        getNeighbors(num, distance, bit + 1, acc);
        getNeighbors(num ^ toggle, distance - 1, bit + 1, acc);
    }
}
