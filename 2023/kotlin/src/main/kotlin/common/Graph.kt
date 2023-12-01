package common

data class Vertex<T> (val index: Int, val data: T)
data class Edge<T> (val source: Vertex<T>, val destination: Vertex<T>, val weight: Int = 1)

interface Graph<T> {
    fun createVertex(data: T): Vertex<T>
    fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Int = 1)
    fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Int = 1)
    fun add(source: Vertex<T>, destination: Vertex<T>, weight: Int = 1, directed: Boolean = true)
    fun get(data: T): Vertex<T>?
    fun edges(source: Vertex<T>): ArrayList<Edge<T>>
    fun weight(source: Vertex<T>, destination: Vertex<T>): Int?
    fun distances(source: Vertex<T>): Map<Vertex<T>, Int>
    val size: Int
}


