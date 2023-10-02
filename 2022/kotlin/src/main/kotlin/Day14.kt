
import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import arrow.core.None
import common.Point
import common.Resource


class Day14(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day14.txt"
    override fun part1() : Int {
        var container = SandContainer(Resource.asList(data).map { Line.of(it) })
        do {
            when (val s = container.drop()) {
                is Left -> return container.sand.size
                is Right -> container = container.copy(sand = container.sand + s.value)
            }
        } while (true)
    }

    override fun part2() : Int {
        val input = SandContainer(Resource.asList(data).map { Line.of(it) })

        val floorY = input.max.y + 2
        val floorXMin = 500 - floorY
        val floorXMax = 500 + floorY

        val floor = "" + floorXMin + "," + floorY + " -> " + floorXMax + "," + floorY

        var container = input.copy(lines = input.lines + Line.of(floor))
        do {
            when (val s = container.drop()) {
                is Left -> return -1
                is Right -> when (s.value) {
                    Point(500, 0) -> return container.sand.size + 1 // +1 to account for the last dropped sand
                    else -> container = container.copy(sand = container.sand + s.value)
                }
            }
        } while (true)
    }

    data class SandContainer(val lines: List<Line>, val sand: Set<Point> = setOf()) {
        companion object {
            fun List<Line>.contains(p: Point): Boolean = this.map { e -> e.contains(p) }.any { it }
        }

        fun drop(position: Point = Point(500, 0)): Either<None, Point> {
            """
                Returns:
                    None if the sand fell out of the container.
                    Point if sand came to rest in the container.
            """.trimIndent()
            var currentPosition = position.copy()
            while (true) {
                if (currentPosition.y > max.y) { return Left(None) }
                when (val next = step(currentPosition)) {
                    is Right -> return Right(next.value)
                    is Left -> currentPosition = next.value
                }
            }
        }

        private val points: List<Point> = lines.flatMap { e -> e.points }
        val min = Point(points.minOf { it.x }, 0)
        val max = Point(points.maxOf { it.x }, points.maxOf { it.y })

        fun step(position: Point): Either<Point, Point> {
            """
                Returns:
                    Left<Point> if point moved, 
                    Right<Point> if it stays.
            """.trimIndent()

            val directions = listOf(
                Point(0, 1),  // Down
                Point(-1, 1), // Down + left
                Point(1, 1)   // Down + right
            )

            directions
                .map { position + it }
                .forEach {
                    if (!isOccupied(it)) return Left(it)
            }
            return Right(position)
        }

        override fun toString(): String {
            return (min.y..max.y).flatMap{ y ->
                (min.x..max.x).map { x ->
                    val p = Point(x, y)
                    fun addNewLineOnLineEnd(string: String) = when(x) {
                        max.x -> string + "\n"
                        else -> string
                    }

                    if (lines.contains(p)) {
                        addNewLineOnLineEnd("#")
                    } else if (sand.contains(p)) {
                        addNewLineOnLineEnd("o")
                    } else {
                        addNewLineOnLineEnd(".")
                    }
                }
            }.joinToString(separator = "")
        }
        private fun isOccupied(position: Point): Boolean = sand.contains(position) || lines.contains(position)
    }

    data class Line(val points: Set<Point>) {
        companion object {
            fun of(s: String): Line {
                return Line(s.split("->")
                        .map { Point.of(it.trim())}
                    .zipWithNext {a, b -> Point.between(a, b) }.flatten().toSet())
            }
        }
        fun contains(p: Point): Boolean = points.contains(p)
        fun containsAll(elements: Collection<Point>): Boolean = elements.map { this.contains(it) }.all { it }
    }

}
