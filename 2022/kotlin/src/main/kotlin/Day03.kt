class Day03 : Day {
    override val inputFile: String = "Day03.txt"

    override fun part1(example: Boolean) : Int {
        val data = Resource.asList(data(example))
        return data
            .map {
                findCommonItem(it.subSequence(0, it.length / 2),
                               it.subSequence(it.length / 2, it.length))
            }.sumOf {
            getCharValue(it)
        }
    }

    override fun part2(example: Boolean) : Int{
        return 42
    }

    companion object {
        private fun compartmentMap(compartment: CharSequence): Map<Char, Int> {
            val map = mutableMapOf<Char, Int>()
            compartment.forEach {
                map[it] = map.getOrDefault(it, 0) + 1
            }
            return map
        }

        private fun findCommonItem(first: CharSequence, second: CharSequence) : Char? {
            val f = compartmentMap(first)
            val s = compartmentMap(second)
            return f.keys.find { it in s.keys }
        }

        fun getCharValue(c: Char?): Int {
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