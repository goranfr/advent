import org.junit.Assert.assertEquals
import org.junit.Test

class Day02Test {


    @Test
    fun `Win calculation returns 0 for loss`() {
        val rock_beats_scissors = Day02.RPSRound.calculateWin('A', 'C')
        val paper_beats_rock = Day02.RPSRound.calculateWin('B', 'A')
        val scissors_beats_paper = Day02.RPSRound.calculateWin('C', 'B')
        assertEquals(0, rock_beats_scissors)
        assertEquals(0, paper_beats_rock)
        assertEquals(0, scissors_beats_paper)
    }

    @Test
    fun `Win calculation returns 3 for draw`() {
        val rock_rock = Day02.RPSRound.calculateWin('A', 'A')
        val paper_paper = Day02.RPSRound.calculateWin('B', 'B')
        val scissors_scissors = Day02.RPSRound.calculateWin('C', 'C')
        assertEquals(3, rock_rock)
        assertEquals(3, rock_rock)
        assertEquals(3, scissors_scissors)
    }

    @Test
    fun `Win calculation returns 6 for victory`() {
        val rock_beats_scissors = Day02.RPSRound.calculateWin('C', 'A')
        val paper_beats_rock = Day02.RPSRound.calculateWin('A', 'B')
        val scissors_beats_paper = Day02.RPSRound.calculateWin('B', 'C')
        assertEquals(6, rock_beats_scissors)
        assertEquals(6, paper_beats_rock)
        assertEquals(6, scissors_beats_paper)
    }



    @Test
    fun `Part 1 matches example`() {
        val answer = Day02().part1(true)
        assertEquals(15, answer)
    }

    @Test
    fun `Part 1 matches input`() {
        val answer = Day02().part1()
        assertEquals(15632, answer)
    }

    @Test
    fun `Part 2 matches example`() {
        val answer = Day02().part2(true)
        assertEquals(12, answer)
    }

    @Test
    fun `Part 2 matches input`() {
        val answer = Day02().part2()
        assertEquals(0, answer)
    }
}