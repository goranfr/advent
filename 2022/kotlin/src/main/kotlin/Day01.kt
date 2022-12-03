class Day01 (override val example: Boolean = false) : Day {
    override val inputFile: String = "Day01.txt"

    private fun elfList(data: List<String>) : List<Int> {
        return data.map {
            it.split("\n").sumOf { it.toInt() }
        }
    }
    override fun part1() : Int {
        val data = Resource.asListGroupedByDelimiter(data(), "\n\n")
        return elfList(data).max()
    }

    override fun part2() : Int{
        val data = Resource.asListGroupedByDelimiter(data(), "\n\n")
        return elfList(data).sortedDescending().take(3).sum()
    }
}