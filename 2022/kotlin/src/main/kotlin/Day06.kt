class Day06(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day06.txt"
    override fun part1() : Int {
        val data = Resource.asText(data())
        println(data)
        return data.withIndex()
            .windowed(4)
            .dropWhile {it.map{it.value}.toSet().size < 4}
            .drop(4)
            .first()
            .first().index
    }

    override fun part2() : Int{
        val data = Resource.asList(data())
        TODO("Not Implemented")
    }
}