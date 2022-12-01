import org.junit.Assert.assertEquals
import org.junit.Test


class Day01Test {

    @Test
    fun `Part 1 matches example`() {
        val day = Day01()
        val answer : Int = day.Part1(day.Example())
        assertEquals(24_000, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val day = Day01()
        val answer = day.Part1(day.Input())
        assertEquals(50, answer)
    }

}