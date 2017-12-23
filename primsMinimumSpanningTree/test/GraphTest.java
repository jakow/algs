import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by jakub on 23/12/2017.
 */

public class GraphTest {
    @Test
    public void constructTest() {
        List<EdgeInput> in = TestFixture.input();
        List<Vertex> expected = TestFixture.graph();
        List<Vertex> actual = Graph.create(in);
        Assert.assertEquals(expected, actual);
        for (int i = 0; i < actual.size(); ++i) {
            List<Edge> expectedEdges = expected.get(i).edges;
            List<Edge> actualEdges = actual.get(i).edges;
            Assert.assertEquals(expectedEdges, actualEdges);
        }

    }
}
