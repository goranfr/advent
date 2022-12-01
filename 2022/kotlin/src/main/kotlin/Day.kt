import java.io.File

interface Day {
    val inputFile: String
    fun example(): File {
        return File("src/main/resources/examples/" + inputFile)
    }
    fun input(): File {
        return File("src/main/resources/input/" + inputFile)
    }

    fun data(example : Boolean = false): File = if (example) example() else input()

    fun part1(example: Boolean = false): Int
    fun part2(example: Boolean = false): Int
}