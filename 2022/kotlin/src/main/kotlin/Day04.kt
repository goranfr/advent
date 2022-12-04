class Day04(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day04.txt"

    override fun part1() : Int {
        val data = Resource.asList(data())
        return data.map { it.split(",").map{ it.split("-").map { it.toInt() }}}
            .filter { anyContainedInOther(it[0], it[1]) }.size
    }

    fun isContainedIn(first: List<Int>, second: List<Int>): Boolean {
        return first[0] >= second[0] && first[1] <= second[1]
    }

    fun anyContainedInOther(first: List<Int>, second: List<Int>): Boolean {
        return isContainedIn(first, second) || isContainedIn(second, first)
    }

    override fun part2() : Int{
        val data = Resource.asList(data())
        TODO("Not Implemented")
    }
}