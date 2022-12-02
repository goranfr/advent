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

        fun gameScore(oppMove: Char, playerMove: Char): Int {
            return when (oppMove - playerMove) {
                -1, 2   -> 6
                0       -> 3
                1, -2   -> 0
                else -> throw IllegalArgumentException("invalid set of moves '${oppMove}–${playerMove}'")
            }
        }

        fun calculateScore(oppMove: Char, playerMove: Char ): Int {
            val moveScore = values.getValue(playerMove)
            val winScore = gameScore(oppMove, playerMove)
            return winScore + moveScore
        }

        fun findCorrectMove(oppMove: Char, result: Char): Char {
            val modifier = ((result.code - 2) % 3) + 1
            val calculateMove = { e: Int -> ((e + modifier) % 3) + 65 }
            return calculateMove(oppMove.code).toChar()
        }
    }
}