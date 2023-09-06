class Day06(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day06.txt"
    private fun indexOfFirstUniqueSubstring(data: String, length: Int): Int {
        return data.withIndex()
            .windowed(length)
            .dropWhile { it.map { it.value }.toSet().size < length }
            .map { it.last() }
            .first().index + 1
    }
    override fun part1() : Int {
        val data = Resource.asText(data)
        return indexOfFirstUniqueSubstring(data, 4)
    }

    override fun part2() : Int{
        val data = Resource.asText(data)
        return indexOfFirstUniqueSubstring(data, 14)
    }

}