import Day08.Companion.visibility
import Day08.Companion.visibilityFromSingleDirection
import Day08.Companion.Direction
import Day08.Companion.getSubList
import Day08.Companion.scenicScores
import Day08.Companion.viewingDistanceInSingleDirection
import common.get
import common.transpose
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class Day08Test {
    private val grid = listOf(
        listOf(3, 0, 3, 7, 3),
        listOf(2, 5, 5, 1, 2),
        listOf(6, 5, 3, 3, 2),
        listOf(3, 3, 5, 4, 9),
        listOf(3, 5, 3, 9, 0)
    )
    @Nested
    inner class Visibility {
        @Test
        fun `visibility of single line of trees all visible`() {
            val l = listOf(1, 2, 3, 4, 5, 6)
            Assertions.assertEquals(6, l.visibility().count { it })
        }

        @Test
        fun `visibility of single line of trees only first visible`() {
            val l = listOf(6, 5, 4, 3, 2, 1)
            Assertions.assertEquals(1, l.visibility().count { it })
        }

        @Test
        fun `visibility from west direction is correct`() {
            val expectedVisibility = listOf(
                listOf(true, false, false, true, false),
                listOf(true, true, false, false, false),
                listOf(true, false, false, false, false),
                listOf(true, false, true, false, true),
                listOf(true, true, false, true, false),
            )
            Assertions.assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.WEST))
        }

        @Test
        fun `visibility from east direction is correct`() {
            val expectedVisibility = listOf(
                2,
                2,
                4,
                1,
                2
            )
            Assertions.assertEquals(
                expectedVisibility,
                grid.visibilityFromSingleDirection(Direction.EAST).map { it.count { it } })
        }

        @Test
        fun `visibility from north direction is correct`() {
            val expectedVisibility = listOf(
                listOf(true, true, true, true, true),
                listOf(false, true, true, false, false),
                listOf(true, false, false, false, false),
                listOf(false, false, false, false, true),
                listOf(false, false, false, true, false),
            )
            Assertions.assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.NORTH))
        }

        @Test
        fun `visibility from south direction is correct`() {
            val expectedVisibility = listOf(
                listOf(false, false, false, false, false),
                listOf(false, false, false, false, false),
                listOf(true, false, false, false, false),
                listOf(false, false, true, false, true),
                listOf(true, true, true, true, true),
            )

            Assertions.assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.SOUTH))
        }
    }

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day08(true).part1()
            Assertions.assertEquals(21, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day08().part1()
            Assertions.assertEquals(1798, answer)
        }
    }

    @Nested
    inner class ViewingDistance {
        @Test
        fun `viewing distance of middle 5 row two north is 1`() {
            val sublist = grid.transpose().reversed()[3].subList(3, 5)
            assertEquals(1, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row two south is 1`() {
            val sublist = grid.transpose()[1].subList(3, 5)
            assertEquals(1, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row two east is 2`() {
            val sublist = grid[1].subList(2, 5)
            assertEquals(2, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row two west is 2`() {
            val sublist = grid[1].reversed().subList(2, 5)
            assertEquals(1, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row four north is 2`() {
            val sublist = grid.transpose().reversed()[2].subList(1, 5)
            assertEquals(2, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row four south is 1`() {
            val sublist = grid.transpose()[1].subList(3, 5)
            assertEquals(1, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row four east is 2`() {
            val sublist = grid[1].subList(2, 5)
            Assertions.assertEquals(2, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of middle 5 row four west is 2`() {
            val sublist = grid[3].reversed().subList(2, 5)
            Assertions.assertEquals(2, viewingDistanceInSingleDirection(sublist))
        }

        @Test
        fun `viewing distance of left 6 row 3 west is 0`() {
            val sublist = grid[2].reversed().subList(4, 5)
            Assertions.assertEquals(0, viewingDistanceInSingleDirection(sublist))
        }
    }

    @Nested
    inner class ScenicScore {
        @Test
        fun `scenic score of middle 5 in row two is 4`() {
            Assertions.assertEquals(4, grid.scenicScores().get(1, 2))
        }

        @Test
        fun `scenic score of middle 5 in row four is 8`() {
            Assertions.assertEquals(8, grid.scenicScores().get(3, 2))
        }
    }

    @Nested
    inner class SubList {
        @Test
        fun `getSubList returns correct sublist north`() {
            val point = Pair(3, 2)
            Assertions.assertEquals(listOf(5, 3, 5, 3), grid.getSubList(Direction.NORTH, point))
        }

        @Test
        fun `getSubList returns correct sublist south`() {
            val point = Pair(3, 2)
            Assertions.assertEquals(listOf(5, 3), grid.getSubList(Direction.SOUTH, point))
        }

        @Test
        fun `getSubList returns correct sublist east`() {
            val point = Pair(3, 2)
            Assertions.assertEquals(listOf(5, 4, 9), grid.getSubList(Direction.EAST, point))
        }

        @Test
        fun `getSubList returns correct sublist west`() {
            val point = Pair(3, 2)
            Assertions.assertEquals(listOf(5, 3, 3), grid.getSubList(Direction.WEST, point))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day08(true).part2()
            Assertions.assertEquals(8, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day08().part2()
            Assertions.assertEquals(259308, answer)
        }
    }
}