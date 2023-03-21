class Day11(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day11.txt"
    private fun parseMonkey(string: String): MatchResult? {
        val match =  """Monkey (\d+):
  Starting items: (.+)
  Operation: new = (.+)
  Test: divisible by (\d+)
    If true: throw to monkey (\d+)
    If false: throw to monkey (\d+)""".toRegex().matchEntire(string)
        return match
    }

    class Monkey(
        val items: MutableList<Int>,
        val operation: (Int) -> Int,
        val test: (Int) -> Boolean,
        private val whenTrue: Int,
        private val whenFalse: Int
    ) {
        var inspections = 0
        fun turn(monkeys: List<Monkey>): Unit {
            items.forEach {
                inspections += 1
                val afterInspection = operation(it)
                val afterBored = afterInspection.floorDiv(3)
                val targetMonkey = if (test(afterBored)) whenTrue else whenFalse
                monkeys[targetMonkey].items.add(afterBored)
            }
            items.clear()
        }

        companion object {
            fun of(match: MatchResult): Monkey {
                val (monkeyNumber, items, operation, test, whenTrue, whenFalse) = match.destructured
                val parsedOperation = fun(i: Int): Int {
                    val (v1, op, v2) = Regex("""(\d+) ([*\/+-]) (\d+)""").matchEntire(
                        operation.replace("old", "$i")
                    )!!.destructured

                    return when (op) {
                        "*" -> v1.toInt() * v2.toInt()
                        "+" -> v1.toInt() + v2.toInt()
                        "-" -> v1.toInt() - v2.toInt()
                        "/" -> v1.toInt() / v2.toInt()
                        else -> throw IllegalArgumentException("Invalid operation: $op")
                    }
                }
                val parsedTest = fun(i: Int): Boolean = (i % test.toInt()) == 0

                return Monkey(
                    items.split(", ").map { it.toInt() }.toMutableList(),
                    parsedOperation,
                    parsedTest,
                    whenTrue.toInt(),
                    whenFalse.toInt()
                )
            }
        }
    }
    override fun part1() : Int {
        val data = Resource.asText(data()).split("\n\n").map { parseMonkey(it) }
        val monkeys = data.filterNotNull()
            .map { Monkey.of(it) }

        repeat(20) {
            monkeys.forEach {
                it.turn(monkeys)
            }
        }

        return monkeys.asSequence()
            .map { it.inspections }
            .sortedBy { -it }
            .take(2)
            .reduce {acc, it -> acc * it}
    }

    override fun part2() : Int{
        val data = Resource.asList(data())
        TODO("Not Implemented")
    }
}