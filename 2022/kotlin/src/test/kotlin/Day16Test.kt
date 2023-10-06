import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day16Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day16(true).part1()
            Assertions.assertEquals(1651, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day16().part1()
            Assertions.assertEquals(2056, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day16(true).part2()
            Assertions.assertEquals(1707, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day16().part2()
            Assertions.assertEquals(2513, answer)
        }
    }
}