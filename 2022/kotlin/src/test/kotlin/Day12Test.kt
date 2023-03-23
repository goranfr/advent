import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day12Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day12(true).part1()
            Assertions.assertEquals(31, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day12().part1()
            Assertions.assertEquals(350, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day12(true).part2()
            Assertions.assertEquals(29, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day12().part2()
            Assertions.assertEquals(349, answer)
        }
    }
}