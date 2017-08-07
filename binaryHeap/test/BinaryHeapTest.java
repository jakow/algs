import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 26/02/2017.
 */
public class BinaryHeapTest {
    @Test
    public void randomSorted() {
        BinaryHeap<Integer> hp = new BinaryHeap<>();
        final int N_RAND = 10000000;
        int[] randomNumbers = new int[N_RAND];
        for (int i = 0; i < N_RAND; ++i) {
            randomNumbers[i] = (int) StdRandom.uniform() * 32000;
        }

        List<Integer> list = new ArrayList<>();
        while (hp.size() > 0) {
            list.add(hp.remove());
        }
        Assert.assertEquals(true, isSorted(list));


    }

    private static boolean isSorted(List<Integer> list) {
        if (list.size() < 1) return true;
        int value = list.get(0);

        for (Integer i : list) {
            if (i > value) return false;
            else value = i;
        }
        return true;
    }
}
