import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakub on 30/12/2017.
 */
public class HammingDistanceClusteringTest {
    @Test
    public void testHammingDistance0() {
        Set<Integer> expected = new HashSet<>();
        expected.add(7);
        Assert.assertEquals(expected, HammingDistanceClustering.getNeighbors(7, 0));
    }

    @Test
    public void testHammingDistance1() {
        // test for 7dec = 0000 0000 0000 0000 0000 0111bin
        Set<Integer> expected = new HashSet<>();
        expected.add(7); // 0000 0000 0000 0000 0000 0110
        expected.add(6); // 0000 0000 0000 0000 0000 0110
        expected.add(5); // 0000 0000 0000 0000 0000 0101
        expected.add(3); // 0000 0000 0000 0000 0000 0011
        expected.add(15); // 0000 0000 0000 0000 0000 1111
        expected.add(16 + 7); // 0000 0000 0000 0000 0001 0111
        expected.add(32 + 7); // 0000 0000 0000 0000 0010 0111
        expected.add(64 + 7);
        expected.add(128 + 7);
        expected.add(256 + 7);
        expected.add(512 + 7);
        expected.add(1024 + 7);
        expected.add(2048 + 7);
        expected.add(4096 + 7);
        expected.add(8192 + 7);
        expected.add(16384 + 7);
        expected.add(32768 + 7);
        expected.add(65536 + 7);
        expected.add(131072 + 7);
        expected.add(262144 + 7);
        expected.add(524288 + 7);
        expected.add(1048576 + 7);
        expected.add(2097152 + 7);
        expected.add(4194304 + 7);
        expected.add(8388608 + 7);
        Assert.assertEquals(expected, HammingDistanceClustering.getNeighbors(7, 1));
    }
}
