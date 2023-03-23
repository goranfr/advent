
import Resource.asGrid
import java.util.*

class Day12(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day12.txt"

    fun parseHeightMap(): HeightMap {
        var start: Point? = null
        var end: Point? = null
        val m = Resource.asGrid(data(), "") { it }
            .map { it.filter{ it.isNotEmpty() } }
            .flatMapIndexed { yIndex, yIt ->
                yIt.mapIndexed { xIndex, xIt ->
                    val pos = Point(xIndex, yIndex)
                    pos to when (xIt) {
                        "S" -> 0.also { start = pos }
                        "E" -> 25.also { end = pos }
                        else -> xIt.toCharArray().first() - 'a'
                    }
                }
            }.toMap()

        return HeightMap(m, start!!, end!!)
    }
    override fun part1() : Int {
        val heightMap = parseHeightMap()
        return heightMap.bfs(
            heightMap.start,
            fun (p) = (p == heightMap.end),
            fun (from, to) = (from <= to + 1))!!
    }

    data class Point(val x: Int = 0, val y: Int = 0) {
        fun cardinalNeighbors(): Set<Point> =
            setOf(
                copy(x = x - 1),
                copy(x = x + 1),
                copy(y = y - 1),
                copy(y = y + 1)
            )
    }

    class HeightMap(val points: Map<Point, Int>, val start: Point, val end: Point) {

        fun bfs(
            begin: Point,
            isEnd: (Point) -> Boolean,
            canMove: (Int, Int) -> Boolean
        ): Int? {
            tailrec fun bfsHelper(seen: Set<Point>, queue: PriorityQueue<PathCost>): Int? {
                if (queue.isEmpty()) return null

                val nextPoint = queue.poll()
                if (nextPoint.point !in seen) {
                    val neighbors = nextPoint.point.cardinalNeighbors()
                        .filter { it in points }
                        .filter { canMove(points.getValue(it), points.getValue(nextPoint.point)) }
                    if (neighbors.any { isEnd(it) }) return nextPoint.cost + 1
                    queue.addAll(neighbors.map { PathCost(it, nextPoint.cost + 1) })
                }
                return bfsHelper(seen + nextPoint.point, queue)
            }

            val queue = PriorityQueue<PathCost>().apply { add(PathCost(begin, 0)) }
            return bfsHelper(setOf(), queue)
        }
    }

    private data class PathCost(val point: Point, val cost: Int): Comparable<PathCost> {
        override fun compareTo(other: PathCost): Int = this.cost.compareTo(other.cost)
    }

    override fun part2() : Int {
        """
            We could try every possible start position. 
            However, a BFS from end to any 'a' will find the minimal distance 'a' first, so there's no need to go through all that extra work.
        """.trimIndent()
        val heightMap = parseHeightMap()
        return heightMap.bfs(
            heightMap.end,
            fun(p) = (heightMap.points.getValue(p) == 0),
            fun(from, to) = (to <= from + 1))!!
    }
}