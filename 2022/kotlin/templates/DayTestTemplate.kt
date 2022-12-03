import org.junit.Assert.assertEquals
import org.junit.Test


class DayDAY_NUMBERTest {

    @Test
    fun `Part 1 matches example`() {
        val answer = DayDAY_NUMBER().part1(true)
        assertEquals(-1, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = DayDAY_NUMBER().part1()
        assertEquals(-1, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = DayDAY_NUMBER().part2(true)
        assertEquals(-1, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = DayDAY_NUMBER().part2()
        assertEquals(-1, answer)
    }


}