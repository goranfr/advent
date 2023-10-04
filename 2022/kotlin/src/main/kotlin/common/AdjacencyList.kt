package common

class AdjacencyList<T> : Graph<T> {
    private val adjacencies: HashMap<Vertex<T>, ArrayList<Edge<T>>> = HashMap()

    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(adjacencies.count(), data)
        adjacencies[vertex] = ArrayList()
        return vertex
    }

    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Int) {
        val edge = Edge(source, destination, weight)
        adjacencies[source]?.add(edge)
    }

    override fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Int) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    override fun add(source: Vertex<T>, destination: Vertex<T>, weight: Int, directed: Boolean) {
        when (directed) {
            true -> addDirectedEdge(source, destination, weight)
            false -> addUndirectedEdge(source, destination, weight)
        }
    }
    override fun edges(source: Vertex<T>): ArrayList<Edge<T>> = adjacencies[source] ?: arrayListOf()

    override fun weight(source: Vertex<T>, destination: Vertex<T>): Int? {
        return edges(source).firstOrNull() { it.destination == destination }?.weight
    }

    override fun get(data: T): Vertex<T>? = adjacencies.keys.firstOrNull { it.data == data }

    override val size: Int
        get() = adjacencies.size

    override fun distances(source: Vertex<T>): Map<Vertex<T>, Int> {
        """
            find distances between from a vertex
        """.trimIndent()
        val distances = mutableMapOf(source to 0)
        val settledNodes = mutableSetOf<Vertex<T>>()
        val unsettledNodes = mutableSetOf(source)
        while (unsettledNodes.size != 0) {
            val evalNode = unsettledNodes.minBy { distances[it]?: Int.MAX_VALUE}
            val evalDist = distances[evalNode] ?: 0
            (adjacencies[evalNode] ?: arrayListOf())
                .filterNot { settledNodes.contains(it.destination) }
                .forEach {
                    val distance = it.weight + evalDist
                    val currentBest = distances[it.destination]?: Int.MAX_VALUE
                    if (distance < currentBest) {
                        distances[it.destination] = distance
                        unsettledNodes.add(it.destination)
                    }
            }
            unsettledNodes.remove(evalNode)
            settledNodes.add(evalNode)
        }
        return distances
    }

    override fun toString() : String {
        return buildString {
            adjacencies.forEach { (vertex, edges) ->
                val edgeString = edges.joinToString { it.destination.data.toString() }
                append("${vertex.data} ---> [ ${edgeString} ]\n")
            }
        }
    }


}
