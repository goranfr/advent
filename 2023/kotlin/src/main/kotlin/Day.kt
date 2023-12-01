import java.io.File

interface Day {
    val inputFile: String
    val isExample: Boolean
    private val example: File
        get() = File("src/main/resources/examples/" + inputFile)
    private val input: File
        get() = File("src/main/resources/input/" + inputFile)


    // todo: as getter
    val data: File
        get() = if (isExample) example else input

    fun part1(): Any
    fun part2(): Any
}