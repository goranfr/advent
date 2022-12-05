import org.junit.Assert.assertEquals
import org.junit.Test

import Stack

class Day05Test {

    @Test
    fun `moving 1 item works as expected`() {
        val stacks = mapOf(
            1 to Stack(listOf('A','B','C')),
            2 to Stack(listOf('D', 'E', 'F'))
        )
        val expectedStacks = mapOf(
            1 to Stack(listOf('A', 'B')),
            2 to Stack(listOf('D', 'E', 'F', 'C'))
        )
        stacks.moveTopCrate(1, 2)
        assertEquals(expectedStacks, stacks)
    }

    @Test
    fun `movin 3 items works as expected`() {
        val stacks = mapOf(
            1 to Stack(listOf('A','B','C')),
            2 to Stack(listOf('D', 'E', 'F'))
        )
        val expectedStacks = mapOf(
            1 to Stack(listOf()),
            2 to Stack(listOf('D', 'E', 'F', 'C', 'B', 'A'))
        )
        stacks.moveCrates(1, 2, 3)
        assertEquals(expectedStacks, stacks)
    }

    @Test
    fun `Part 1 matches example`() {
        val answer = Day05(true).part1()
        assertEquals("CMZ", answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day05().part1()
        assertEquals("VJSFHWGFT", answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day05(true).part2()
        assertEquals("MCD", answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day05().part2()
        assertEquals("LCTQFBVZV", answer)
    }
}