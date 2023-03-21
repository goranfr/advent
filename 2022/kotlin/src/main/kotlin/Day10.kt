class Day10(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day10.txt"
    fun isInterestingCycle(n: Int): Boolean = ((n + 20) % 40) == 0

    private fun Sequence<Instruction>.runInstructions(): Sequence<Register> {
        return this.runningFold(listOf(Register("X", 1))) { acc, op -> op.apply(acc.last()) }
            .flatten()
    }
    override fun part1() : Int {
        val instructions = Resource.asSequence(data()) { Instruction.parse(it) }
        return instructions.runInstructions()
            .withIndex()
            .filter { isInterestingCycle(it.index + 1) }
            .sumOf { e -> (e.index + 1) * e.value.value }
    }

    override fun part2() : Int {
        val instructions = Resource.asSequence(data()) { Instruction.parse(it) }

        fun Int.isLit(spriteLocation: Int): Boolean {
            val modIndex = this % 40
            return (spriteLocation - 1 <= modIndex) && (modIndex <= spriteLocation + 1)
        }
        instructions.runInstructions()
            .withIndex()
            .map { it.index.isLit(it.value.value) }
            .map { if (it) '#' else '.'}
            .chunked(40)
            .forEach {
                it.forEach { print(it) }
                    .apply { println() }
        }

        return 15
    }

}
data class Register (val name: String, val value: Int)


sealed interface Instruction {
    val cycles: Int
    fun apply(register: Register): List<Register>
    companion object {
        fun parse(s: String): Instruction {
            return when {
                s.startsWith("addx ") -> s.split(" ").let { (_, count) -> Instruction.AddX(count.toInt()) }
                s == "noop" -> Instruction.Noop()
                else -> throw IllegalArgumentException("Invalid instruction ${s}")
            }
        }
    }

    data class AddX(val value: Int) : Instruction {
        override val cycles: Int = 2
        override fun apply(register: Register): List<Register> {

            return buildList {
                add(register.copy())
                add(Register(register.name, register.value + value))
            }
        }
    }

    class Noop : Instruction {
        override val cycles: Int = 1
        override fun apply(register: Register): List<Register> {
            return listOf(register.copy())
        }
    }

}
