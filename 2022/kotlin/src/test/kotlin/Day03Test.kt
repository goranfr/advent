import org.junit.Assert.assertEquals
import org.junit.Test


class Day03Test {

    @Test
    fun `char value of a is 1`() {
        assertEquals(1, Day03.getItemPriority('a'))
    }

    @Test
    fun `char value of 'B' is 28`() {
        assertEquals(28, Day03.getItemPriority('B'))
    }

    @Test
    fun `Part 1 matches example`() {
        val answer = Day03(true).part1()
        assertEquals(157, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day03().part1()
        assertEquals(8202, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day03(true).part2()
        assertEquals(70, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day03().part2()
        assertEquals(2_864, answer)
    }


}