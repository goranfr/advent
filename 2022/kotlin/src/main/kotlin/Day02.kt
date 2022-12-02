class Day02 : Day {
    override val inputFile: String = "Day02.txt"

    override fun part1(example: Boolean) : Int {
        val data = Resource.asList(data(example))
        return data.map { it.toCharArray() }
            .sumOf { calculateScore(it[0], it[2] - 23) }
    }

    override fun part2(example: Boolean) : Int{
        val data = Resource.asList(data(example))
        return data.map { it.toCharArray() }
            .sumOf { calculateScore(it[0], findCorrectMove(it[0], it[2])) }
    }

    companion object RPSRound {
        /*
            Round score = shape_value + outcome_value
         */
        private val values = mapOf (
            'A' to 1, // Rock
            'B' to 2, // Paper
            'C' to 3, // Scissors
        )

        fun calculateWin(oppMove: Char, playerMove: Char): Int {
            return when (oppMove - playerMove) {
                -1, 2   -> 6
                0       -> 3
                1, -2   -> 0
                else -> throw IllegalArgumentException("invalid set of moves '${oppMove}–${playerMove}'")
            }
        }

        fun calculateScore(oppMove: Char, playerMove: Char ): Int {
            val moveScore = values.getValue(playerMove)
            val winScore = calculateWin(oppMove, playerMove)
            return winScore + moveScore
        }

        fun findCorrectMove(oppMove: Char, result: Char): Char {
            // X -> win
            // Y -> draw
            // Z -> lose
            return when (result) {
                'Y' -> oppMove
                'X' -> when (oppMove) {
                    'A' -> 'C'
                    'B' -> 'A'
                    'C' -> 'B'
                    else -> throw IllegalArgumentException("Illegal opponent move '${oppMove}")
                }
                'Z' -> when (oppMove) {
                    'A' -> 'B'
                    'B' -> 'C'
                    'C' -> 'A'
                    else -> throw IllegalArgumentException("Illegal opponent move '${oppMove}")
                }
                else -> throw IllegalArgumentException("Illegal result '${result}")
            }
        }
    }
}