class Day04(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day04.txt"

    private fun makePairList(data: List<String>): List<List<List<Int>>> {
        return data.map { it.split(",").map{ it.split("-").map { it.toInt() }}}
    }

    override fun part1() : Int {
        val data = makePairList(Resource.asList(data()))
        return data.filter { anyContainedInOther(it[0], it[1]) }.size
    }

    private fun isContainedIn(first: List<Int>, second: List<Int>): Boolean {
        return first[0] >= second[0] && first[1] <= second[1]
    }

    private fun anyContainedInOther(first: List<Int>, second: List<Int>): Boolean {
        return isContainedIn(first, second) || isContainedIn(second, first)
    }

    private fun isOverlapping(first: List<Int>, second: List<Int>): Boolean {
        return (first[0] >= second[0] && first[0] <= second[1])
            || (first[1] <= second[1] && first[0] >= second[0])
    }

    private fun isAnyOverlap(first: List<Int>, second: List<Int>): Boolean {
        return isOverlapping(first, second) || isOverlapping(second, first)
    }

    override fun part2() : Int{
        val data = makePairList(Resource.asList(data()))
        return data.filter{ isAnyOverlap(it[0], it[1]) }.size
    }
}