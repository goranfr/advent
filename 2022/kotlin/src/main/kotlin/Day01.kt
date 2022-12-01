import java.io.File

class Day01 : Day {
    override val inputFile: String = "Day01.txt"

    override fun Part1(example : Boolean) : Int {
        val s = Resource.asText(Data(example))
        val groups = s.split("\n\n")
        val sumGroups = groups.map {
            it.split("\n").sumOf { it.toInt() }
        }
        return sumGroups.max()
    }

    override fun Part2(example : Boolean) : Int{
        TODO("Not yet implemented")
    }
}