import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 27/01/2018.
 */
public class TripleStepTest {
    @Test
    public void tripleStepTest1Recursive() {
        // 1 1 1 1
        // 2 1 1
        // 1 2 1
        // 1 1 2
        // 2 2
        // 3 1
        // 1 3
        Assert.assertEquals(7, TripleStep.tripleStep(4));
    }


    @Test
    public void tripleStepTest1Dp() {
        // 1 1 1 1
        // 2 1 1
        // 1 2 1
        // 1 1 2
        // 2 2
        // 3 1
        // 1 3
        Assert.assertEquals(7, TripleStep.tripleStepDp(4));
    }


    @Test
    public void tripleStepTestLargeRec() {
        TripleStep.tripleStep(30);
    }

    @Test
    public void tripleStepTestLargeDp() {
        TripleStep.tripleStepDp(30);
    }

    @Test
    public void tripleStepEquality() {
        Assert.assertTrue(TripleStep.tripleStepDp(25) == TripleStep.tripleStep(25));
    }
}
