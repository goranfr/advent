package common

import java.io.File

object Resource {
    fun asList(inputFile: File): List<String> {
        return inputFile.readLines()
    }

    fun <T> asList(inputFile: File, transform: (String) -> T): List<T> {
        return asList(inputFile).map { transform(it) }
    }

    fun asSequence(inputFile: File): Sequence<String> = inputFile.bufferedReader().lineSequence()

    fun <T> asSequence(inputFile: File, transform: (String) -> T): Sequence<T> = asSequence(inputFile).map { transform(it) }
    fun asText(inputFile: File): String {
        return inputFile.readText()
    }

    fun asIntList(inputFile: File): List<Int> {
        return asList(inputFile).map { it.toInt() }
    }

    fun asListGroupedByDelimiter(inputFile: File, delimiter: String = "\n\n"): List<String> {
        return asText(inputFile).split(delimiter)
    }

    fun <T> Resource.asGrid(inputFile: File, delimiter: String? = null, transform: (String) -> T): Grid<T> {
        return if (delimiter == null) {
            asList(inputFile).map { it.toCharArray().map { it.toString() }.map { transform(it) } }
        } else {
            asList(inputFile).map { it.split(delimiter).map { transform(it) } }
        }
    }
}
typealias Grid<T> = List<List<T>>
fun <T> Grid<T>.get(row: Int, col: Int) = this.getOrNull(row)?.getOrNull(col)
fun <T> Grid<T>.transpose(): Grid<T> {
    val columnIndices = this.indices

    val maxRowSize = this.maxOf { it.size }
    val rowIndices = 0 until maxRowSize
    return rowIndices.map { rowIndex ->
        columnIndices.map { columnIndex ->
            this.get(columnIndex).get(rowIndex)
        }
    }
}