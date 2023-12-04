import common.Resource
import kotlin.math.max

class Day02(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day02.txt"

    data class GameRound(val red: Int, val green: Int, val blue: Int) {
        companion object {
            fun of(str: String): GameRound {
                fun String.findColor(color: String): Int = ("(\\d+) $color").toRegex().find(this)?.groupValues?.last()?.toInt() ?: 0

                val red = str.findColor("red")
                val green = str.findColor("green")
                val blue = str.findColor("blue")

                return GameRound(red, green, blue)
            }
        }
        fun isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean = red <= maxRed && green <= maxGreen && blue <= maxBlue
    }
    data class CubeGame(val id: Int, val rounds: List<GameRound>) {
        fun isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean {
            return rounds.all { it.isPossible(maxRed, maxGreen, maxBlue) }
        }

        fun minRequired(): GameRound = rounds.reduce { acc, e ->
            GameRound(
                max(e.red, acc.red),
                max(e.green, acc.green),
                max(e.blue, acc.blue)
            )
        }

        companion object {
            fun of(str: String): CubeGame =
                str.split(": ").let {
                    CubeGame(
                        it.first().dropWhile { !it.isDigit() }.toInt(),
                        it.last().split(";").map { GameRound.of(it) })
            }
        }
    }


    override fun part1() : Int {
        val data = Resource.asSequence(data)
        return data.map { CubeGame.of(it) }
            .filter { it.isPossible(12, 13, 14) }
            .sumOf { it.id }
    }

    override fun part2() : Int {
        val data = Resource.asSequence(data)
        return data.map { CubeGame.of(it) }
            .map { it.minRequired() }
            .map { it.red * it.green * it.blue}
            .sum()
    }
}
