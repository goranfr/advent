import Day08.Companion.visibilities
import Day08.Companion.visibility
import Resource.asGrid

class Day08(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day08.txt"

    override fun part1() : Int {
        return Resource.asGrid(data()) { it.toInt() }
            .visibilities()
            .sumOf { it.count { it } }
    }

    override fun part2() : Int{
        val data = Resource.asGrid(data()) {it.toInt() }


    }
    companion object {
        fun List<Int?>.visibility(): List<Boolean> {
            return this.mapIndexed { index, e ->
                (e != null) && ((index == 0)
                                || subList(0, index).none { (it ?: 0) >= e })
            }
        }

        enum class Direction {
            EAST, WEST, NORTH, SOUTH
        }

        fun Grid<Int?>.visibilityFromSingleDirection(direction: Direction): Grid<Boolean> {
            return when (direction) {
                Direction.EAST ->  this.map { it.reversed().visibility().reversed() }
                Direction.WEST ->  this.map { it.visibility() }
                Direction.NORTH -> this.transpose().map { it.visibility() }.transpose()
                Direction.SOUTH -> this.transpose().map {it.reversed().visibility().reversed() }.transpose()
            }
        }


        fun Grid<Int?>.visibilities(): Grid<Boolean> {
            fun combineLists(a: List<Boolean>, b: List<Boolean>) = a.mapIndexed {index, i -> i || b[index]}
            fun combineGrids(a: Grid<Boolean>, b: Grid<Boolean>) = a.mapIndexed { index, l -> combineLists(l, b[index])}
            return Direction.values().map {
                this.visibilityFromSingleDirection(it)
            }.reduce {acc, g -> combineGrids(acc, g)}

        }
    }

}