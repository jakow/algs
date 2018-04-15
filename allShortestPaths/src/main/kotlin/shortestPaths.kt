import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.graph.Network
import kotlin.math.min

fun <T> shortestPaths(graph: Network<T, Int>, source: T): Map<T, Int> {
    val sz = graph.nodes().size
    // need a way to associate nodes with their index
    val nodeIndices = HashMap<T, Int>()
    val nodes = graph.nodes().toList()
    for ((idx, node) in nodes.withIndex()) {
        nodeIndices[node] = idx
    }

    // build 2d array for dp, will optimize later
    val dp = Array(sz, {  IntArray(sz) { Int.MAX_VALUE } })
    for (i in 0 until sz) {
        dp[0][i] = Int.MAX_VALUE
    }

    val srcIdx = nodes.indexOf(source)
    dp[0][srcIdx] = 0

    for (i in 1 until sz) {
        for (v in 0 until sz) {
            val node = nodes[v]
            val minOfNodes = minOfConnectingNodes(dp, graph, node, i - 1, nodeIndices)
            dp[i][v] = min(dp[i - 1][v], minOfNodes)
        }
    }
    val results = dp[sz - 1]
    val map = mutableMapOf<T, Int>()
    for (i in 0 until sz) {
        map[nodes[i]] = results[i]
    }
    return map
}


private fun <T> minOfConnectingNodes(
        dp: Array<IntArray>,
        graph: Network<T, Int>,
        node: T,
        edgeBudget: Int,
         indices: Map<T, Int>): Int {
    var currMin = Int.MAX_VALUE
    for (predecessor in graph.predecessors(node)) {
        val prevBudget = dp[edgeBudget][indices.getValue(predecessor)]
        if (prevBudget != Int.MAX_VALUE) {
            currMin = min(currMin, prevBudget + graph.edgeConnectingOrNull(predecessor, node)!!)
        }
    }
    return currMin
}

