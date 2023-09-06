package common
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `beteween returns all points between`() {
        val points = Point.between(Point(498, 6), Point(496, 6))
        val expected = setOf(Point(498, 6), Point(497, 6), Point(496, 6))
        assertEquals(expected, points)
    }
}