
import Day14.SandContainer.Companion.contains
import arrow.core.Either.Left
import arrow.core.Either.Right
import arrow.core.left
import arrow.core.right
import common.Point
import common.Resource
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
class Day14Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day14(true).part1()
            assertEquals(24, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day14().part1()
            assertEquals(825, answer)
        }
    }

    @Nested
    inner class Line {


        @Test
        fun `Line does not contain point`() {
            val line = Day14.Line.of("0,0->5,0")
            assertFalse(line.contains(Point(-5, -5)))
        }

        @Test
        fun `List of lines contains point`() {
            val lines = listOf(
                Day14.Line.of("0,0 -> 0,3 -> 3,3"),
                Day14.Line.of("0,0 -> 0,5 -> 5,5")
            )
            assertTrue(lines.contains(Point(2,5)))
        }

        @Test
        fun `List of lines does not contain point`() {
            val lines = listOf(
                Day14.Line.of("0,0 -> 0,3 -> 3,3"),
                Day14.Line.of("0,0 -> 0,5 -> 5,5")
            )
            assertFalse(lines.contains(Point(4,4)))
        }

        @Test
        fun `Line with single segment has all points`() {
            val line = Day14.Line.of("0,0->5,0")
                val points = (0..5).map { Point(it, 0)}
                assertTrue(line.containsAll(points))
        }

        @Test
        fun `Line with multiple segments has all points`() {
            val line = Day14.Line.of("0,0 -> 3,0 -> 3,3 -> 6,3")
            val points = ((0..3).map { Point(it, 0) }
                + (0..3).map { Point(3, it) }
                + (3..6).map { Point(it, 3)}).toSet()
            assertTrue(line.containsAll(points))
            assertEquals(points, line.points)
        }

        @Test
        fun `Line with multiple segments has all points 2`() {
            val s = "498,4 -> 498,6 -> 496,6"
            val line = Day14.Line.of(s)
            val points = setOf(Point(498, 4), Point(498,5), Point(498, 6),
                                Point(497, 6), Point(496, 6))
            assertEquals(points, line.points)
        }
        
        @Test
        fun `example input parses into correct lines`() {
            val day = Day14(isExample = true)
            val linelist = Resource.asList(day.data).map { Day14.Line.of(it) }
            assertEquals(5, linelist.first().points.size)
            assertEquals(20, linelist.map { it.points.size }.sum())
            val points = setOf(
                Point(498, 4), Point(498,5), Point(498, 6),
                Point(497, 6), Point(496, 6),
                Point(503, 4), Point(502,4),
                Point(502,5), Point(502,6), Point(502, 7), Point(502,8), Point(502,9),
                Point(501, 9), Point(500, 9), Point(499, 9), Point(498, 9), Point(497, 9), Point(496, 9), Point(495, 9), Point(494, 9)
            )
            assertEquals(points, linelist.flatMap { it.points }.toSet())
        }
    }

    @Nested
    inner class SandContainer {
        private val container = Day14.SandContainer(lines = listOf(
            Day14.Line.of("0,10 -> 2,10")
        ))

        @Test
        fun `toString returns correct representation`() {
            val container = Day14.SandContainer(lines = listOf(
                Day14.Line.of("0,0 -> 2,0"),
                Day14.Line.of("0,10 -> 2,10")
            ))
            val expected = """
                ###
                ...
                ...
                ...
                ...
                ...
                ...
                ...
                ...
                ...
                ###
                
            """.trimIndent()
            assertEquals(expected, container.toString())
        }

        @Test
        fun `step single grain moves downward`() {
            val container = Day14.SandContainer(lines = listOf(
                Day14.Line.of("0,10 -> 0,10"),
                Day14.Line.of("2,10 -> 2,10")
            ))
            val stepped = container.step(Point(1, 9))
            assertEquals(Point(1, 10).left(), stepped)
        }

        @Test
        fun `step single grain moves down-left`() {
            val stepped = container.step(Point(0, 9))
            assertEquals(Point(-1,10).left(), stepped)
        }

        @Test
        fun `step single grain moves down-right`() {
            val stepped = container.step(Point(2, 9))
            assertEquals(Point(3, 10).left(), stepped)
        }

        @Test
        fun `step single grain does not move`() {
            val stepped = container.step(Point(1, 9))
            assertEquals(Point(1,9).right(), stepped)
        }

        @Test
        fun `step single grain moves only one tile`() {
            val stepped = container.step(Point(1, 0))
            assertEquals(Point(1,1).left(), stepped)
        }

        @Test
        fun `drop straight down`() {
            val dropped = container.drop(Point(1, 0))
            when (dropped) {
                is Left -> assertEquals(setOf(Point(1, 9)), dropped.value)
                is Right -> assertEquals(1, dropped.value.x)
            }
        }

        @Test
        fun `drop falling out of bounds`() {
            val dropped = container.drop(Point(-5, 0))
            when (dropped) {
                is Left -> assert(true)
                is Right -> assert(false)
            }
        }

        @Test
        fun `drop rolling off a ledge before coming to rest`(){
            val container = Day14.SandContainer(lines = listOf(
                Day14.Line.of("0,4->0,5->2,5->2,4->3,4->3,3->4,3")
            ))
            when (val dropped = container.drop(Point(2,0))) {
                is Right -> assertEquals(Point(1,4), dropped.value)
                is Left -> fail("sand dropped out of the container")
            }
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day14(true).part2()
            assertEquals(93, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day14().part2()
            assertEquals(26729, answer)
        }
    }
}