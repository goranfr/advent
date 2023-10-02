
import Day15.Companion.isCoveredBy
import common.Point
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue

class Day15Test {

    @Nested
    inner class Sensor {
        private val sensor = Day15.Sensor.of("Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n")
        @Test
        fun `of creates correct sensor location from string`() {
            val expectedSensorLocation = Point(2, 18)
            assertEquals(expectedSensorLocation, sensor.location)
        }

        @Test
        fun `of creates correct beacon location from string`() {
            val expectedBeaconLocation = Point(-2, 15)
            assertEquals(expectedBeaconLocation, sensor.beacon)

        }

        @Test
        fun `range returns correct distance`() {
            assertEquals(7, sensor.range)
        }

        @Test
        fun `furthest covered tile straight up is correct distance`() {
            val sensor = Day15.Sensor.of("Sensor at x=8, y=7: closest beacon is at x=2, y=10")
            val actual = sensor.covers().minBy { it.y }
            val expected = Point(8, -2)
            assertEquals(expected, actual)

        }

        private fun Day15.Sensor.validationPoints(): Set<Point> {
            return (-1 * this.range..this.range).flatMap { y ->
                val d = this.range - y.absoluteValue
                (-1 * d .. d).map { x ->
                    Point(this.location.x + x, this.location.y + y)
                }
            }.toSet()
        }

        @Test
        fun `covers returns correct set`() {
            val sensor = Day15.Sensor.of("Sensor at x=0, y=0: closest beacon is at x=2, y=-1")
            val cover = sensor.covers()

            val expectedPoints = sensor.validationPoints()
            assertEquals(expectedPoints.size, cover.size)
            assertEquals(expectedPoints, cover)
        }

        @Test
        fun `covers returns correct set for larger distance`() {
            val cover = sensor.covers()
            val expectedPoints = sensor.validationPoints()
            assertEquals(expectedPoints, cover)
        }

        @Test
        fun `covers returns correct set with overlapping sensors`() {
            val sensors = listOf(
                Day15.Sensor.of("Sensor at x=0, y=0: closest beacon is at x=2, y=-1"),
                Day15.Sensor.of("Sensor at x=2, y=1: closest beacon is at x=2, y=2")
            )

            val total_cover = sensors.map { it.covers() }.reduce { acc, e -> acc + e }
            assertEquals(27, total_cover.size)
        }

        @Test
        fun `covers returns 0 points with y outside range`() {
            assertEquals(0, sensor.covers().filter { it.y == 100}.size)
        }

        @Test
        fun `covers returns correct amount of points on y=1`() {
            val sensor = Day15.Sensor.of("Sensor at x=0, y=0: closest beacon is at x=2, y=-1")
            val cover = sensor.covers()
            assertEquals("#####".length, cover.filter { it.y == 1}.size)
        }

        @Test
        fun `covers returns correct amount of points on y=7`() {
            val sensor = Day15.Sensor.of("Sensor at x=8, y=7: closest beacon is at x=2, y=10")
            val cover = sensor.covers()
            assertEquals("#########S#######S#".length, cover.filter { it.y == 7}.size)
        }

    }

    @Nested
    inner class PointTests {
        @Test
        fun `isCoveredBy neighboring point on same y`() {
            val point = Point(2,2)
            val sensor = Day15.Sensor(location = Point(3, 2),  beacon = Point(100, 100))
            assertTrue(point.isCoveredBy(sensor))
        }

        @Test
        fun `not isCoveredBy far-away point on same y`() {
            val point = Point(2, 2)
            val sensor = Day15.Sensor(location = Point(100, 2),  beacon = Point(105, 2))

            println(point)
            println("location: " + sensor.location)
            println("range:    " + sensor.range)
            println("distance: " + sensor.location.manhattan(point))
            val covered = point.isCoveredBy(sensor)
            assertFalse(covered)
        }

        @Test
        fun `isCoveredBy set of sensors`() {
            val point = Point(5, 5)
            val sensors = listOf(
                Day15.Sensor(
                    location = Point(4, 4),
                    beacon = Point(100, 100)
                ),
                Day15.Sensor(
                    location = Point(6, 6),
                    beacon = Point(100, 100)
                )
            )

        }
    }

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day15(true).part1()
            assertEquals(26, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day15().part1()
            assertEquals(5108096, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day15(true).part2()
            assertEquals(-1, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day15().part2()
            assertEquals(-1, answer)
        }
    }
}