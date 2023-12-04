import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day02Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day02(true).part1()
            Assertions.assertEquals(8, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day02().part1()
            Assertions.assertEquals(2085, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day02(true).part2()
            Assertions.assertEquals(2286, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day02().part2()
            Assertions.assertEquals(79315, answer)
        }
    }
}