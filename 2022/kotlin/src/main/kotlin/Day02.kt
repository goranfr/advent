class Day02 : Day {
    override val inputFile: String = "Day02.txt"

    override fun part1(example: Boolean) : Int {
        val data = Resource.asList(data(example))
        return data.map { it.toCharArray()}
            .map {calculateScore(it[0], it[2]) }
            .sum()
    }

    override fun part2(example: Boolean) : Int{
        val data = Resource.asText(data(example))
        return -1
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
                -1, 2 -> 6
                0      -> 3
                1, -2   -> 0
                else -> throw IllegalArgumentException("invalid set of moves '${oppMove}–${playerMove}'")
            }
        }

        fun calculateScore(oppMove: Char, playerMove: Char ): Int {
            val adjustedPlayerMove = playerMove - 23
            val moveScore = values.getValue(adjustedPlayerMove)
            val winScore = calculateWin(oppMove, adjustedPlayerMove)
            return winScore + moveScore
        }
    }
}