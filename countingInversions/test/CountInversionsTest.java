import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 09/02/2017.
 */

public class CountInversionsTest {
    @Test
    public void test() {
        Integer[] a = new Integer[] {2,4,1,3,5};
        Assert.assertEquals(3, CountInversions.countInversions(a));
        Integer[] b = new Integer[] {2,1,4,5,0};
        Assert.assertEquals(5, CountInversions.countInversions(b));
        Integer[] c = new Integer[] {2,1,4,5,3,0};
        Assert.assertEquals(8, CountInversions.countInversions(c));
    }
//    CountInversions.countInversions
}
