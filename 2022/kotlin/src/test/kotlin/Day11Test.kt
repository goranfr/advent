import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day11Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day11(true).part1()
            Assertions.assertEquals(10605, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day11().part1()
            Assertions.assertEquals(62491, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day11(true).part2()
            Assertions.assertEquals(2713310158L, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day11().part2()
            Assertions.assertEquals(17408399184, answer)
        }
    }
}