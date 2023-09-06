import common.Grid
import common.Resource
import common.Resource.asGrid
import common.transpose

class Day08(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day08.txt"

    override fun part1() : Int {
        return Resource.asGrid(data) { it.toInt() }
            .visibilities()
            .sumOf { it.count { it } }
    }

    override fun part2() : Int{
        val data = Resource.asGrid(data) {it.toInt() }
        return data.scenicScores().max()
    }
    companion object {
        fun List<Int?>.visibility(): List<Boolean> {
            return this.mapIndexed { index, e ->
                (e != null) && ((index == 0)
                                || subList(0, index).none { (it ?: 0) >= e })
            }
        }

        fun viewingDistanceInSingleDirection(trees: List<Int>): Int {
            if (trees.isEmpty()) return 0
            val height = trees.first()
            val distance = trees.drop(1).takeWhile { it < height }.size
            // return distance
            return if (distance == trees.size - 1) distance else distance + 1
        }

        private fun List<Int>.maxIndex(): Int = this.indices.maxBy { this[it] }
        fun Grid<Int>.max(): Int {
            return this.map {
                val maxIndex = it.maxIndex()
                Pair(it[maxIndex], it.maxIndex())
            }.fold(Pair(-1, -1)) { acc, pair: Pair<Int, Int> ->
                run {
                    if (pair.first > acc.first) {
                        pair
                    } else {
                        acc
                    }
                }
            }.first
        }

        fun Grid<Int>.getSubList(dir: Direction, index: Pair<Int, Int>): List<Int> {
            return when (dir) {
                Direction.NORTH -> this.transpose()[index.second].subList(0, index.first + 1).reversed()
                Direction.SOUTH -> this.transpose()[index.second].subList(index.first, this[index.second].size)
                Direction.EAST -> this[index.first].subList(index.second, this[index.first].size)
                Direction.WEST -> this[index.first].subList(0, index.second + 1).reversed()
            }
        }
        private fun Grid<Int>.scenicScoreOfPoint(index: Pair<Int, Int>): Int {
            val viewingDistances = Direction.values().map {
                viewingDistanceInSingleDirection(this.getSubList(it, index))
            }
            return viewingDistances.reduce { acc, i -> acc * i }
        }

        fun Grid<Int>.scenicScores(): Grid<Int> {
            return this.mapIndexed { rowIndex, list ->
                list.mapIndexed { colIndex, _ ->
                    this.scenicScoreOfPoint(Pair(rowIndex, colIndex))
                }
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