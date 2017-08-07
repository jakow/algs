import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by jakub on 24/01/2017.
 */
public class PercolationStatsTest {
    @Test
    public void basicTest() {
        PercolationStats.main(new String[]{"8", "1"});
    }

    @Test
    public void test_N8_trials100() {
        PercolationStats.main(new String[]{"8", "100"});
    }
    @Test
    public void test_N20_trials100() {
        PercolationStats.main(new String[]{"20", "100"});

    }

    @Test
    public void test_N100_trials1000() {
        PercolationStats.main(new String[]{"1000", "1"});
    }
}