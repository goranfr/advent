import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day01Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day01(true).part1()
            Assertions.assertEquals(142, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day01().part1()
            Assertions.assertEquals(53334, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day01(true).part2()
            Assertions.assertEquals(281, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day01().part2()
            Assertions.assertEquals(52834, answer)
        }
    }
}