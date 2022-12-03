import java.io.File

interface Day {
    val inputFile: String
    val example: Boolean
    private fun example(): File {
        return File("src/main/resources/examples/" + inputFile)
    }
    private fun input(): File {
        return File("src/main/resources/input/" + inputFile)
    }

    fun data(): File = if (example) example() else input()

    fun part1(): Int
    fun part2(): Int
}