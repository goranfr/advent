class Day03 (override val isExample: Boolean = false) : Day  {
    override val inputFile: String = "Day03.txt"

    override fun part1() : Int {
        val data = Resource.asList(data)
        return data.mapNotNull {
            findCommonItem(
                listOf(
                    it.subSequence(0, it.length / 2),
                    it.subSequence(it.length / 2, it.length)
                )
            )}
            .sumOf {getItemPriority(it)
        }
    }

    override fun part2() : Int {
        val data = Resource.asList(data)
        return data.chunked(3).mapNotNull { findCommonItem(it) }
            .sumOf { getItemPriority(it) }
    }

    companion object {

        private fun findCommonItem(listOfCompartments: List<CharSequence>) : Char? {
            return listOfCompartments.map { it.toSet() }
                .reduce {a , b -> a.intersect(b)}
                .first()
        }

        fun getItemPriority(c: Char): Int {
            if (c.isUpperCase()) {
                return c - 'A' + 1 + 26
            } else if (c.isLowerCase()) {
                return c - 'a' + 1
            }
            return 0
        }
    }
}