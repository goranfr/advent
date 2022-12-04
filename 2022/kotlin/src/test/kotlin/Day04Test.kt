import org.junit.Assert.assertEquals
import org.junit.Test


class Day04Test {

    @Test
    fun `Part 1 matches example`() {
        val answer = Day04(true).part1()
        assertEquals(2, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day04().part1()
        assertEquals(494, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day04(true).part2()
        assertEquals(4, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day04().part2()
        assertEquals(-1, answer)
    }
}