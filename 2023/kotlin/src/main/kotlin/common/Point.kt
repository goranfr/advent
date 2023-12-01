package common

import kotlin.math.abs
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {
    companion object {
        fun of(s: String, delimiter: String = ","): Point {
            val (x, y) = s.split(delimiter).map { it.toInt() }
            return Point(x, y)
        }

        fun between(a: Point, b: Point): Set<Point> {
            fun pointsBetween(lower: Point, higher: Point): Set<Point> {
                val xs = (lower.x .. higher.x)
                val ys = (lower.y .. higher.y)

                return ys.flatMap { y ->
                    xs.map { x ->
                        Point(x, y)
                    }
                }.toSet()
            }

            val dx = b.x - a.x
            val dy = b.y - a.y
            return if (dx < 0 || dy < 0) {
                pointsBetween(b, a)
            } else {
                pointsBetween(a, b)
            }
        }

    }

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)
    fun distance(other: Point): Float {
        """
            Euclidean distance
        """.trimIndent()
        val xDiff = (other.x - x)
        val yDiff = (other.y - y)
        return sqrt ((xDiff * xDiff).toFloat() + (yDiff * yDiff) )
    }

    fun manhattan(other: Point): Int {
        """
            Manhattan (taxi-cab) distance
        """.trimIndent()
        return abs(other.x - x) + abs(other.y - y)
    }

    val neighbors: Set<Point>
        get() = setOf(
            Point(x - 1, y),
            Point(x + 1, y),
            Point(x, y - 1),
            Point(x, y + 1),
        )
}

