import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day13Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day13(true).part1()
            Assertions.assertEquals(13, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day13().part1()
            Assertions.assertEquals(6046, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day13(true).part2()
            Assertions.assertEquals(140, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day13().part2()
            Assertions.assertEquals(21423, answer)
        }
    }
}