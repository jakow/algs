import com.google.common.graph.MutableValueGraph
import com.google.common.graph.NetworkBuilder
import com.google.common.graph.ValueGraphBuilder
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ShortestPathsTest : StringSpec() {
    init {
        "should do stuff" {
            val g = NetworkBuilder
                    .directed()
                    .allowsSelfLoops(false)
                    .build<String, Int>()
            g.addNode("A")
            g.addNode("B")
            g.addNode("C")
            g.addNode("D")
            g.addEdge("A", "B", 4)
            g.addEdge("A", "C", 3)
            g.addEdge("B", "D", 2)
            g.addEdge("C", "D", 8)
            g.addEdge("A", "D", 7)
            shortestPaths(g, "A") shouldBe mapOf(
                    "A" to 0,
                    "B" to 4,
                    "C" to 3,
                    "D" to 6
            )
        }
    }
}