import java.io.File

interface Day {
    val inputFile : String
    fun Example() : File {
        return File("examples/" + inputFile)
    }
    fun Input() : File {
        return File("input/" + inputFile)
    }
    fun Part1(file: File) : Int
    fun Part2(file: File) : Int
}