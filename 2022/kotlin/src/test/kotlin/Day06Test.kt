import org.junit.Assert.assertEquals
import org.junit.Test


class Day06Test {

    @Test
    fun `Part 1 matches example`() {
        val answer = Day06(true).part1()
        assertEquals(7, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day06().part1()
        assertEquals(1198, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day06(true).part2()
        assertEquals(19, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day06().part2()
        assertEquals(3120, answer)
    }
}