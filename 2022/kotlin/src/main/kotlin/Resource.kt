import java.io.File

object Resource {
    fun asList(inputFile: File): List<String> {
        return inputFile.readLines()
    }

    fun asText(inputFile: File): String {
        return inputFile.readText()
    }

    fun asIntList(inputFile: File): List<Int> {
        return asList(inputFile).map { it.toInt() }
    }

    fun asListGroupedByDelimiter(inputFile: File, delimiter: String = "\n\n"): List<String> {
        return asText(inputFile).split(delimiter)
    }
}