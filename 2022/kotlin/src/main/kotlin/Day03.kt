class Day03 : Day {
    override val inputFile: String = "Day03.txt"

    override fun part1(example: Boolean) : Int {
        val data = Resource.asList(data(example))
        return data
            .map {
                findCommonItem(
                    listOf(it.subSequence(0, it.length / 2),
                           it.subSequence(it.length / 2, it.length))
                )
            }.sumOf {
            getItemPriority(it)
        }
    }

    override fun part2(example: Boolean) : Int {
        val data = Resource.asList(data(example))
        return data.chunked(3)
            .map { findCommonItem(it) }
            .map { getItemPriority(it)}
            .sum()
    }

    companion object {
        private fun compartmentMap(compartment: CharSequence): Map<Char, Int> {
            val map = mutableMapOf<Char, Int>()
            compartment.forEach {
                map[it] = map.getOrDefault(it, 0) + 1
            }
            return map
        }

        private fun findCommonItem(listOfCompartments: List<CharSequence>) : Char? {
            val r = listOfCompartments.map {
                compartmentMap(it)
            }.reduce{a, b -> (a.keys.intersect(b.keys).associateWith { 1 }) }
            return r.keys.first()
        }

        fun getItemPriority(c: Char?): Int {
            if (c == null) {
                return 0
            } else if (c.isUpperCase()) {
                return c - 'A' + 1 + 26
            } else if (c.isLowerCase()) {
                return c - 'a' + 1
            }
            return 0

        }
    }
}