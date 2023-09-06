import common.Resource

class Day07(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day07.txt"

    override fun part1() : Int {
        val tree = makeDirectoryTree(Resource.asList(data))
        return tree.traverse()
            .filter { it.value is Directory }
            .filter { it.size() < 100_000 }
            .sumOf{it.size()}
    }

    override fun part2() : Int{
        val tree = makeDirectoryTree(Resource.asList(data))
        val totalDiskSpace = 70_000_000
        val totalFreeSpaceNeeded = 30_000_000
        val unusedDiskSpace = totalDiskSpace - tree.size()

        return tree.traverse()
            .filter { it.value is Directory }
            .filter { it.size() + unusedDiskSpace > totalFreeSpaceNeeded  }
            .sortedBy { it.size() }
            .first().size()
    }
    companion object {
        fun makeDirectoryTree(data: List<String>): Node<FileSystemObject> {
            val root: Node<FileSystemObject> = Node(Directory("/"))
            data.map {
                when (it.startsWith("$")) {
                    true -> when (it.startsWith("$ cd")) {
                        true -> ChangeDirCommand(it.subSequence(5, it.length).toString())
                        false -> ListCommand()
                    }

                    false -> when (it.startsWith("dir")) {
                        true -> Directory(it.subSequence(4, it.length).toString())
                        false -> File(it)
                    } } }
                .drop(1)
                .fold(root) { acc, e ->
                    when (e) {
                        is ListCommand -> acc
                        is ChangeDirCommand -> {
                            when (e.args) {
                                ".." -> acc.parent!!
                                else -> acc.children.find { it.value.name == e.args }!!
                            }
                        }

                        is Directory -> acc.add(e)
                        is File -> acc.add(e)
                        else -> throw IllegalArgumentException(e.toString())
                    }
                }
            return root
        }
    }
}

interface Output
interface Command: Output
interface FileSystemObject {
    val name: String
    fun size(): Int
}
class ListCommand: Command
data class ChangeDirCommand(val args: String): Command

class Directory(override val name: String): FileSystemObject {

    override fun toString(): String {
        return "Directory(name='${this.name}', size=${this.size()})"
    }

    override fun size(): Int {
        return 0
    }
}

data class File(override val name: String): Output, FileSystemObject {
    override fun size(): Int {
        return name.split(" ").first().toInt()
    }

    override fun toString(): String {
        return "File(name='${this.name}', size=${this.size()})"
    }
}

class Node<T: FileSystemObject>(val value: T, val parent: Node<T>? = null) {

    var children: MutableList<Node<T>> = mutableListOf()

    fun add(e: T): Node<T> {
        children.add(Node(e, this))
        return this
    }

    fun traverse(): List<Node<T>> {
        return listOf(this) + children.flatMap{ it.traverse() }
    }

    fun size(): Int {
        return children.sumOf {it.size()} + this.value.size()
    }

}