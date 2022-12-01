import java.io.File

object Resource {
    fun asList(inputFile: File): List<String> {
        return inputFile.readLines()
    }

    fun asText(inputFile: File): String {
        return inputFile.readText()
    }

}