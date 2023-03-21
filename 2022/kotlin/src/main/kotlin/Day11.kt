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
        val items: MutableList<Long>,
        val operation: (Long) -> Long,
        val test: Long,
        private val whenTrue: Int,
        private val whenFalse: Int
    ) {
        var inspections = 0L
        fun turn(monkeys: List<Monkey>, boredFunc: (Long) -> Long = fun(i) = i.floorDiv(3)): Unit {
            items.forEach {
                inspections += 1
                val afterInspection = operation(it)
                val afterBored = boredFunc(afterInspection)
                val targetMonkey = if (doTest(afterBored)) whenTrue else whenFalse
                monkeys[targetMonkey].items.add(afterBored)
            }
            items.clear()
        }

        fun doTest(i: Long): Boolean {
            return (i % test) == 0L
        }

        companion object {
            fun of(match: MatchResult): Monkey {
                val (monkeyNumber, items, operation, test, whenTrue, whenFalse) = match.destructured
                val parsedOperation = fun(i: Long): Long {
                    val (v1, op, v2) = Regex("""(\d+) ([*\/+-]) (\d+)""").matchEntire(
                        operation.replace("old", "$i")
                    )!!.destructured

                    return when (op) {
                        "*" -> v1.toLong() * v2.toLong()
                        "+" -> v1.toLong() + v2.toLong()
                        "-" -> v1.toLong() - v2.toLong()
                        "/" -> v1.toLong() / v2.toLong()
                        else -> throw IllegalArgumentException("Invalid operation: $op")
                    }
                }
                val parsedTest = fun(i: Long): Boolean = (i % test.toLong()) == 0L

                return Monkey(
                    items.split(", ").map { it.toLong() }.toMutableList(),
                    parsedOperation,
                    test.toLong(),
                    whenTrue.toInt(),
                    whenFalse.toInt()
                )
            }

        }
    }
    override fun part1() : Long {
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
            .reduce { acc, it -> acc * it }
    }

    override fun part2() : Long {
        val data = Resource.asText(data()).split("\n\n").map { parseMonkey(it) }
        val monkeys = data.filterNotNull()
            .map { Monkey.of(it) }

        // Find a common denominator to void using BigDecimal or the like.
        val modulus = monkeys.map { it.test }
            .reduce(Long::times)

        repeat(10_000) {
            monkeys.forEach {
                it.turn(monkeys, boredFunc = fun(i) = i % modulus)
            }
        }

        return monkeys.asSequence()
            .map { it.inspections }
            .sortedBy { -it }
            .take(2)
            .reduce { acc, it -> acc * it }
    }
}