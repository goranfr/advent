import Day08.Companion.visibilities
import Day08.Companion.visibility
import Day08.Companion.visibilityFromSingleDirection
import Resource.asGrid
import Day08.Companion.Direction
import org.junit.Assert.assertEquals
import org.junit.Test


class Day08Test {

    @Test
    fun `visibility of single line of trees all visible`() {
        val l = listOf(1,2,3,4,5,6)
        assertEquals(6, l.visibility().count {it})
    }

    @Test
    fun `visibility of single line of trees only first visible`() {
        val l = listOf(6,5,4,3,2,1)
        assertEquals(1, l.visibility().count {it} )
    }

    @Test
    fun `visibility from west direction is correct`() {
        val grid: Grid<Int?> = listOf(
            listOf(3,0,3,7,3),
            listOf(2,5,5,1,2),
            listOf(6,5,3,3,2),
            listOf(3,3,5,4,9),
            listOf(3,5,3,9,0)
        )
        val expectedVisibility = listOf(
            listOf(true, false, false, true, false),
            listOf(true, true, false, false, false),
            listOf(true, false, false, false, false),
            listOf(true, false, true, false, true),
            listOf(true, true, false, true, false),
        )
        assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.WEST))
    }

    @Test
    fun `visibility from east direction is correct`() {
        val grid: Grid<Int?> = listOf(
            listOf(3,0,3,7,3),
            listOf(2,5,5,1,2),
            listOf(6,5,3,3,2),
            listOf(3,3,5,4,9),
            listOf(3,5,3,9,0)
        )
        val expectedVisibility = listOf(
            2,
            2,
            4,
            1,
            2
        )
        assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.EAST).map {it.count { it } } )
    }

    @Test
    fun `visibility from north direction is correct`() {
        val grid: Grid<Int?> = listOf(
            listOf(3,0,3,7,3),
            listOf(2,5,5,1,2),
            listOf(6,5,3,3,2),
            listOf(3,3,5,4,9),
            listOf(3,5,3,9,0)
        )
        val expectedVisibility = listOf(
            listOf(true, true, true, true, true),
            listOf(false, true, true, false, false),
            listOf(true, false, false, false, false),
            listOf(false, false, false, false, true),
            listOf(false, false, false, true, false),
        )
        assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.NORTH))
    }

    @Test
    fun `visibility from south direction is correct`() {
        val grid: Grid<Int?> = listOf(
            listOf(3,0,3,7,3),
            listOf(2,5,5,1,2),
            listOf(6,5,3,3,2),
            listOf(3,3,5,4,9),
            listOf(3,5,3,9,0)
        )
        val expectedVisibility = listOf(
            listOf(false, false, false, false, false),
            listOf(false, false, false, false, false),
            listOf(true, false, false, false, false),
            listOf(false, false, true, false, true),
            listOf(true, true, true, true, true),
        )
        assertEquals(expectedVisibility, grid.visibilityFromSingleDirection(Direction.SOUTH))
    }


    @Test
    fun `Part 1 matches example`() {
        val answer = Day08(true).part1()
        assertEquals(21, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day08().part1()
        assertEquals(1798, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day08(true).part2()
        assertEquals(-1, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day08().part2()
        assertEquals(-1, answer)
    }
}