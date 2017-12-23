import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrimsMinimumSpanningTreeTest {
    @Test
    public void mstTest() {
        List<Vertex> inputGraph = TestFixture.graph();
        List<Edge> expectedMst = TestFixture.mst();
        List<Edge> actualMst = PrimsMinimumSpanningTree.compute(inputGraph)
                .stream()
                .sorted(TestFixture.sortComparator())
                .collect(Collectors.toList());
        Assert.assertEquals(expectedMst, actualMst);
    }

    @Test
    public void slowAndFastImplementationEquivalence() {
        try {
            List<Edge> mst;
            File f = new File("data/edges.txt");
            List<EdgeInput> edges = EdgeInput.fromScanner(new Scanner(f));
            List<Vertex> graph = Graph.create(edges);
            List<Edge> mstFast = PrimsMinimumSpanningTree.compute(graph);
            mstFast.sort(TestFixture.sortComparator());
            List<Edge> mstSlow = PrimsMinimumSpanningTree.computeSlow(graph);
            mstSlow.sort(TestFixture.sortComparator());
            Assert.assertEquals(mstSlow, mstFast);
            Assert.assertEquals(PrimsMinimumSpanningTree.weight(mstSlow), PrimsMinimumSpanningTree.weight(mstFast));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
