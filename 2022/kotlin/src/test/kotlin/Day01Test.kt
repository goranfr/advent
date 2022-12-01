import org.junit.Assert.assertEquals
import org.junit.Test


class Day01Test {

    @Test
    fun `Part 1 matches example`() {
        val answer : Int = Day01().Part1(true)
        assertEquals(24_000, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day01().Part1()
        assertEquals(75501, answer)
    }

}