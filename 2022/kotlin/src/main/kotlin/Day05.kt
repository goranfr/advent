class Day05(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day05.txt"
    private val instructionRegex = Regex("""move (\d+) from (\d+) to (\d+)""")
    fun parseCrates(stackInput: String): Stacks {
        return stackInput.split("\n")
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
    }
    override fun part1() : String {
        val data = Resource.asListGroupedByDelimiter(data(), "\n\n")
        val stacks = parseCrates(data[0])
        val instructionData = data[1]

        instructionRegex.findAll(instructionData)
            .forEach { stacks.moveCrates(
                it.groupValues[2].toInt(),
                it.groupValues[3].toInt(),
                it.groupValues[1].toInt())}

        return stacks.values.map { it.last() }.joinToString("")

    }

    override fun part2() : String {
        val data = Resource.asListGroupedByDelimiter(data(), "\n\n")
        val stacks = parseCrates(data[0])
        val instructionData = data[1]
        instructionRegex.findAll(instructionData)
            .forEach {
                stacks.moveMultipleCrates(
                    it.groupValues[2].toInt(),
                    it.groupValues[3].toInt(),
                    it.groupValues[1].toInt()
                )
            }
        return stacks.values.map { it.last() }.joinToString("")
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
    repeat(n) { this.moveTopCrate(from, to) }
}

fun Stacks.moveMultipleCrates(from: Int, to: Int, n: Int = 1) {
    var fromStack = this.get(from)!!
    val toStack = this.get(to)!!

    val crates = fromStack.subList(fromStack.size - n, fromStack.size)
    toStack.addAll(crates)

    repeat(n) { fromStack.removeLast() }
}