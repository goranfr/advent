package common
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `between returns all points between`() {
        val points = Point.between(Point(498, 6), Point(496, 6))
        val expected = setOf(Point(498, 6), Point(497, 6), Point(496, 6))
        assertEquals(expected, points)
    }

    @Test
    fun `neighbors returns all neighbors`() {
        val point = Point(5, 5)
        val expected = setOf(
            Point(4, 5),
            Point(5, 4),
            Point(5, 6),
            Point(6, 5)
        )
        assertEquals(expected, point.neighbors.toSet())
    }

    @Nested
    inner class Manhattan {
        @Test
        fun `returns correct distance for straight line`() {
            val pointA = Point(5, 5)
            val pointB = Point(5, 10)
            val expected = 5

            assertEquals(expected, pointA.manhattan(pointB))
        }

        @Test
        fun `returns correct distance for curved line`() {
            val pointA = Point(5, 5)
            val pointB = Point(12, 10)
            val expected = 12

            assertEquals(expected, pointA.manhattan(pointB))
        }

        @Test
        fun `returns same distance both ways`() {
            val pointA = Point(5, 5)
            val pointB = Point(10, 10)

            assertEquals(pointA.manhattan(pointB), pointB.manhattan(pointA))
        }
    }

    @Test
    fun `operator plus works with negative numbers`() {
        val a = Point(-2, 5)
        val b = Point(-4, -2)
        val expected = Point(-6, 3)
        assertEquals(expected, a + b)
    }

    @Test
    fun `operator minus works with negative numbers`() {
        val a = Point(-2, 5)
        val b = Point(-4, -2)
        val expected = Point(2, 7)
        assertEquals(expected, a - b)
    }
}