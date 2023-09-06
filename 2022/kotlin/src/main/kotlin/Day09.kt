
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day09(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day09.txt"
    override fun part1() : Int {
        val data = Resource.asList(data) { parseInputLine(it) }
        val result = applyMoves(data)
        return result.points.size
    }

    fun applyMoves(moveList: List<Pair<Direction, Int>>): Grid {
        return moveList.fold(Grid()) {
            grid, instruction -> grid.move(instruction)
        }
    }

    override fun part2() : Int{
        val data = Resource.asList(data)
        TODO("Not Implemented")
    }

    inner class Grid(
        val points: Set<Point> = setOf(Point(0, 0)),
        val head: Point = Point(0,0),
        val tail: Point = Point(0, 0)) {

        fun move(point: Pair<Direction, Int>): Grid {
            val dir = point.first
            val length = point.second
            val moves = createPoints(point)
            val newGrid =  moves.fold(this) {
                acc, p -> run {
                    val newTail = acc.tail.follow(p)
                    Grid(acc.points + newTail , p, newTail)
                }
            }
            return newGrid
        }

        override fun toString(): String {
            return this.points.fold("") {
                acc, p -> acc + p.toString() + ", "
            }
        }

        fun createPoints(p: Pair<Direction, Int>): List<Point> {
            return (1 .. p.second).fold(listOf( this.head )) {
                    acc, _ -> acc + acc.last().createPointInDirection(p.first)
            }.drop(1)
        }

        fun moveTailTowards(tail: Point, p: Point): Point {
            val diff = tail - p
            val x = if ((-1 <= diff.x) && (diff.x <= 1)) 0 else 1 * diff.x.sign * -1
            val y = if ((-1 <= diff.y) && (diff.y <= 1)) 0 else 1 * diff.y.sign * -1
            return Point(x, y)

        }

    }

    inner class Point(val x: Int, val y: Int) {
        override fun toString(): String {
            return "Point($x, $y)"
        }

        fun createPointInDirection(dir: Direction): Point {
            return when (dir) {
                Direction.UP -> Point(this.x, this.y + 1)
                Direction.DOWN -> Point(this.x, this.y - 1)
                Direction.RIGHT -> Point(this.x + 1, this.y)
                Direction.LEFT -> Point(this.x - 1, this.y)
            }
        }

        fun isAdjacent(other: Point): Boolean {
            return (other.x - this.x in (-1..1))
                    && (other.y - this.y in (-1..1))

        }

        fun follow(other: Point): Point {
            if (this.isAdjacent(other)) return this
            val diff = this - other

            val x = when (diff.x.absoluteValue) {
                2 -> -1 * diff.x.sign
                1 -> when (diff.y.absoluteValue) {
                    2 -> -1 * diff.x.sign
                    else -> 0
                }
                else -> 0
            }

            val y = when (diff.y.absoluteValue) {
                2 ->  -1 * diff.y.sign
                1 -> when (diff.x.absoluteValue) {
                    2 -> -1 * diff.y.sign
                    else -> 0
                }
                else -> 0
            }

            return this + Point(x, y)
        }

        operator fun plus(other: Point): Point {
            return Point(other.x + this.x, other.y + this.y)
        }

        operator fun minus(other: Point): Point {
            return Point(this.x - other.x, this.y - other.y)
        }

        override operator fun equals(other: Any?) = (other is Point) && x == other.x && y == other.y
        override fun hashCode() = x.hashCode() * y.hashCode()

    }

    companion object {
        enum class Direction {
            UP, DOWN, LEFT, RIGHT
        }

        fun parseInputLine(line: String): Pair<Direction, Int> {
            val parts = line.split(" ")
            val dir = when (parts.first()) {
                "U" -> Direction.UP
                "D" -> Direction.DOWN
                "L" -> Direction.LEFT
                "R" -> Direction.RIGHT
                else -> throw IllegalArgumentException(parts.first())
            }
            val length = parts.last().toInt()
            return Pair(dir, length)
        }
    }
}