import java.io.File

class Day01 : Day {
    override val inputFile: String = "Day01.txt"

    fun elfList(data : String) : List<Int> {
        val groups = data.split("\n\n")
        return groups.map {
            it.split("\n").sumOf { it.toInt() }
        }
    }
    override fun Part1(example : Boolean) : Int {
        val data = Resource.asText(Data(example))
        return elfList(data).max()
    }

    override fun Part2(example : Boolean) : Int{
        val data = Resource.asText(Data(example))
        return elfList(data).sortedDescending().take(3).sum()
    }
}