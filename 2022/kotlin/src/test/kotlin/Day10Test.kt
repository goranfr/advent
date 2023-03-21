import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day10Test {
    private val day = Day10()

    @Nested
    inner class `helper functions`() {
        @Test
        fun `isInterestingCycle gives the correct six values for example`() {
            val isInteresting = (0..220).filter {
                day.isInterestingCycle(it)
            }
            Assertions.assertEquals(listOf(20, 60, 100, 140, 180, 220), isInteresting)
        }
    }

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 matches example`() {
            val answer = Day10(true).part1()
            Assertions.assertEquals(13140, answer)
        }

        @Test
        fun `Part 1 matches input`() {
            val answer = Day10().part1()
            Assertions.assertEquals(13740, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 matches example`() {
            val answer = Day10(true).part2()
            Assertions.assertEquals(-1, answer)
        }

        @Test
        fun `Part 2 matches input`() {
            val answer = Day10().part2()
            Assertions.assertEquals(-1, answer)
        }
    }
}