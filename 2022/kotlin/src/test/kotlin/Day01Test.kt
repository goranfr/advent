import org.junit.Assert.assertEquals
import org.junit.Test


class Day01Test {

    @Test
    fun `Part 1 matches example`() {
        val answer = Day01(true).part1()
        assertEquals(24_000, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day01().part1()
        assertEquals(75_501, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day01(true).part2()
        assertEquals(45_000, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day01().part2()
        assertEquals(215_594, answer)
    }


}