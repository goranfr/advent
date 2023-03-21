class Day10(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day10.txt"
    fun isInterestingCycle(n: Int): Boolean = ((n + 20) % 40) == 0

    override fun part1() : Int {
        val instructions = Resource.asList(data()) { Instruction.parse(it) }
        println(instructions)
        val result: List<Register> = instructions.runningFold(listOf(Register("X", 1))) { acc, op ->
            op.apply(acc.last())
        }.flatten()

        println(result)
        println(result.size)
        val signalStrengths = result
            .withIndex()
            .filter { isInterestingCycle(it.index + 1) }
            .map { e ->
                run {
                    println("${e.index}: ${e.value.value}")
                    (e.index + 1) * e.value.value
                } }

        println(signalStrengths)
        // println(result.last().registers.get("X"))
        return signalStrengths.sum()
    }

    override fun part2() : Int{
        val data = Resource.asList(data())
        TODO("Not Implemented")
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
