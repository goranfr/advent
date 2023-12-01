import common.Resource
import java.io.File

class Day01(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day01.txt"

    override fun part1() : Int {
        val data = Resource.asList(data)
        return data.map {
            it.filter { it.isDigit() }
        }.sumOf {
            "${it.first()}${it.last()}".toInt()
        }

    }

    override fun part2() : Int {

        val data = if (isExample) Resource.asList(File("src/main/resources/examples/" +  "Day01part2.txt")) else Resource.asList(data)
        val digitsSubstitutions = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )

        val digits = digitsSubstitutions.keys + digitsSubstitutions.values
        fun sub(l: Pair<Int, String>?): String = digitsSubstitutions.entries.fold(l?.second ?: "") { acc, (key, value) -> acc.replace(key, value) }
        return data.map { line ->
            "" + sub(line.findAnyOf(digits)) + sub(line.findLastAnyOf(digits))
        }.sumOf { it.toInt() }
    }
}