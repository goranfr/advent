import org.junit.jupiter.api.*

class DayDAY_NUMBERTest {

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = DayDAY_NUMBER(true).part1()
            Assertions.assertEquals(-1, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = DayDAY_NUMBER().part1()
            Assertions.assertEquals(-1, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = DayDAY_NUMBER(true).part2()
            Assertions.assertEquals(-1, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = DayDAY_NUMBER().part2()
            Assertions.assertEquals(-1, answer)
        }
    }
}