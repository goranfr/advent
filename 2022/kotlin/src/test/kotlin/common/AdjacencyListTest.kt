package common
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AdjacencyListTest {
    val graph = AdjacencyList<String>()
    val largeGraph = AdjacencyList<String>().also {
        val singapore = it.createVertex("Singapore")
        val tokyo = it.createVertex("Tokyo")
        val hongKong = it.createVertex("Hong Kong")
        val detroit = it.createVertex("Detroit")
        val sanFrancisco = it.createVertex("San Francisco")
        val washingtonDC = it.createVertex("Washington DC")
        val austinTexas = it.createVertex("Austin Texas")
        val seattle = it.createVertex("Seattle")

        it.add(singapore, hongKong, 300, directed = false)
        it.add(singapore, tokyo, 500, directed = false)
        it.add(hongKong, tokyo, 250, directed = false)
        it.add(tokyo, detroit, 450, directed = false)
        it.add(tokyo, washingtonDC, 300, directed = false)
        it.add(hongKong, sanFrancisco, 600, directed = false)
        it.add(detroit, austinTexas, 50, directed = false)
        it.add(austinTexas, washingtonDC, 292, directed = false)
        it.add(sanFrancisco, washingtonDC, 337, directed = false)
        it.add(washingtonDC, seattle, 277, directed = false)
        it.add(sanFrancisco, seattle, 218, directed = false)
        it.add(austinTexas, sanFrancisco, 297, directed = false)

    }
    @Test
    fun `create single vertex`() {
        graph.createVertex("Tokyo")
        assertEquals(1, graph.size)
    }

    @Test
    fun `create multiple vertices`() {
        listOf("Tokyo", "Oslo", "Berlin", "Philadelphia")
            .forEach { graph.createVertex(it) }
        assertEquals(4, graph.size)
    }

    @Test
    fun `create multiple vertices with same name`() {
        listOf("Tokyo", "Oslo", "Berlin", "Tokyo", "Tokyo")
            .forEach { graph.createVertex(it) }
        assertEquals(5, graph.size)
    }

    @Test
    fun `add directed edge`() {
        val source = graph.createVertex("Tokyo")
        val dest = graph.createVertex("Berlin")
        graph.add(source, dest)
        assertEquals(1, graph.edges(source).size)
        assertEquals(0, graph.edges(dest).size)
    }

    @Test
    fun `add multiple directed edges`() {
        val source = graph.createVertex("Tokyo")
        listOf("Berlin", "Oslo", "Utrecht").forEach {
            val dest = graph.createVertex(it)
            graph.add(source, dest)
            assertEquals(0, graph.edges(dest).size)
        }
        assertEquals(3, graph.edges(source).size)
    }

    @Test
    fun `add undirected edge`() {
        val source = graph.createVertex("Tokyo")
        val dest = graph.createVertex("Berlin")
        graph.add(source, dest, directed = false)
        assertEquals(1, graph.edges(source).size)
        assertEquals(1, graph.edges(dest).size)
    }

    @Test
    fun `weight of undirected edges of neighboring vertices `() {
        val source = graph.createVertex("Tokyo")
        val dest = graph.createVertex("Berlin")
        graph.add(source, dest, weight = 500, directed = false)
        assertEquals(500, graph.weight(source, dest))
        assertEquals(500, graph.weight(dest, source))
    }

    @Test
    fun `weight of directed edges of neighboring vertices `() {
        val source = graph.createVertex("Tokyo")
        val dest = graph.createVertex("Berlin")
        graph.add(source, dest, weight = 500, directed = true)
        assertEquals(500, graph.weight(source, dest))
        assertEquals(null, graph.weight(dest, source))
    }

    @Test
    fun `add multiple undirected edges`() {
        val source = graph.createVertex("Tokyo")
        listOf("Berlin", "Oslo", "Utrecht").forEach {
            val dest = graph.createVertex(it)
            graph.add(source, dest, directed = false)
            assertEquals(1, graph.edges(dest).size)
        }
        assertEquals(3, graph.edges(source).size)
    }

    @Test
    fun `distance is 0 when source is dest`() {
        val source = largeGraph.get("Tokyo")!!
        val distance = largeGraph.distances(source)[source]
        assertEquals(0, distance)
    }

    @Test
    fun `correct distance between neighbors`() {
        val source = largeGraph.get("Tokyo")!!
        val dest = largeGraph.get("Singapore")
        val distance = largeGraph.distances(source)[dest]
        assertEquals(500, distance)
    }

    @Test
    fun `correct distance between non-neighbors`() {
        val source = largeGraph.get("Hong Kong")!!
        val dest = largeGraph.get("Washington DC")
        val distance = largeGraph.distances(source)[dest]
        assertEquals(550, distance)
    }

    @Test
    fun `create and print larger example`() {
        println(largeGraph)
    }
}