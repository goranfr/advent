class Day05(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day05.txt"
    override fun part1() : String {
        val data = Resource.asListGroupedByDelimiter(data(), "\n\n")
        val stackData = data[0]
        val instructionData = data[1]

        val stacks = stackData.split("\n")
            .reversed()
            .drop(1)
            .map {
                it.filterIndexed { index, _ -> (index % 4) - 1 == 0 }
                .mapIndexed { index, char -> Pair(index + 1, char)}
                .filter { it.second != ' '} }
            .flatten()
            .groupBy { it.first }
            .map { it.value.map { it.second } }
            .mapIndexed { index, list -> Pair(index + 1, ArrayDeque(list)) }
            .toMap()
        val instructionRegex = Regex("""move (\d+) from (\d+) to (\d+)""")
        instructionRegex.findAll(instructionData)
            .forEach { stacks.moveCrates(
                it.groupValues[2].toInt(),
                it.groupValues[3].toInt(),
                it.groupValues[1].toInt())}

        return stacks.values.map { it.last() }.joinToString("")

    }

    override fun part2() : String {
        val data = Resource.asList(data())
        TODO("Not Implemented")
    }
}

typealias Stack = ArrayDeque<Char>
typealias Stacks = Map<Int, Stack>

fun Stacks.moveTopCrate(from: Int, to: Int) {
    val fromStack = this.get(from)!!
    val toStack = this.get(to)!!
    toStack.addLast(fromStack.removeLast())
}

fun Stacks.moveCrates(from: Int, to: Int, n: Int = 1) {
    val fromStack = this.get(from)!!
    val toStack = this.get(to)!!
    repeat(n) { this.moveTopCrate(from, to) }
}