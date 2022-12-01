class Day01 : Day {
    override val inputFile: String = "Day01.txt"

    private fun elfList(data: List<String>) : List<Int> {
        return data.map {
            it.split("\n").sumOf { it.toInt() }
        }
    }
    override fun part1(example: Boolean) : Int {
        val data = Resource.asListGroupedByDelimiter(data(example), "\n\n")
        return elfList(data).max()
    }

    override fun part2(example: Boolean) : Int{
        val data = Resource.asListGroupedByDelimiter(data(example), "\n\n")
        return elfList(data).sortedDescending().take(3).sum()
    }
}