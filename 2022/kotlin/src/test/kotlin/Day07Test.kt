import org.junit.Assert.assertEquals
import org.junit.Test


class Day07Test {

    @Test
    fun `Part 1 matches example`() {
        val answer = Day07(true).part1()
        assertEquals(95437, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day07().part1()
        assertEquals(1315285, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day07(true).part2()
        assertEquals(24933642, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day07().part2()
        assertEquals(9847279, answer)
    }
}