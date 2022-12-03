import org.junit.Assert.assertEquals
import org.junit.Test


class Day03Test {

    @Test
    fun `char value of a is 1`() {
        assertEquals(1, Day03.getCharValue('a'))
    }

    @Test
    fun `char value of 'B' is 28`() {
        ('a' to 'z')
        assertEquals(28, Day03.getCharValue('B'))
    }

    @Test
    fun `Part 1 matches example`() {
        val answer = Day03().part1(true)
        assertEquals(157, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day03().part1()
        assertEquals(75_501, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day03().part2(true)
        assertEquals(45_000, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day03().part2()
        assertEquals(215_594, answer)
    }


}