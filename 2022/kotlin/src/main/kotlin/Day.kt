import java.io.File

interface Day {
    val inputFile : String
    fun Example() : File {
        return File("src/main/resources/examples/" + inputFile)
    }
    fun Input() : File {
        return File("src/main/resources/input/" + inputFile)
    }

    fun Data(example : Boolean = false) : File {
        if (example) return Example() else return Input()

    }
    fun Part1(example : Boolean = false) : Int
    fun Part2(example : Boolean = false) : Int
}