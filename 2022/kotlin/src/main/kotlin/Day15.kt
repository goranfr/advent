import common.Point
import common.Resource

class Day15(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day15.txt"

    companion object {
        fun Point.isCoveredBy(sensors: Collection<Sensor>): Boolean =
            sensors.any { it.range >= it.location.manhattan(this) }

        fun Point.isCoveredBy(sensor: Sensor): Boolean = this.isCoveredBy(listOf(sensor))
    }

    override fun part1() : Int {
        val data = Resource.asList(data).map { Sensor.of(it) }

        val minX = data.minOf { it.location.x - it.range }
        val maxX = data.maxOf { it.location.x + it.range }

        val yValue = if (isExample) 10 else 2000000
        return (minX..maxX)
            .map { x -> Point(x, yValue) }
            .filter { it.isCoveredBy(data) }
            .filterNot { data.map { sensor -> sensor.beacon }.contains(it) }
            .size
    }

    val Collection<Point>.visual: String
        get() =
            (this.min.y..this.max.y).flatMap { y ->
                (min.x..max.x).map { x ->
                    val p = Point(x, y)
                    fun addNewLineOnLineEnd(string: String) = when (x) {
                        max.x -> string + "\n%-3s: ".format(y)
                        else -> string
                    }
                    addNewLineOnLineEnd (
                        when (this.contains(p)) {
                            true -> "x"
                            false -> " "
                        }
                    )
                }
            }.joinToString(separator = "")

    val Collection<Point>.min: Point
        get() = Point(this.minOf { it.x }, this.minOf { it.y })
    val Collection<Point>.max: Point
        get() = Point(this.maxOf { it.x }, this.maxOf { it.y })

    override fun part2() : Int {
        val data = Resource.asList(data)
        TODO("Not Implemented")
    }

    data class Sensor(val location: Point, val beacon: Point) {
        companion object {
            fun of(string: String): Sensor {
                val exp = Regex("""x=(-?\d+), y=(-?\d+)""")
                val matches = exp.findAll(string).take(2)
                val (sensorX, sensorY) = matches.first().groupValues.drop(1).take(2).map { it.toInt() }
                val (beaconX, beaconY) = matches.drop(1).first().groupValues.drop(1).take(2).map { it.toInt() }
                return Sensor(location = Point(sensorX, sensorY),
                              beacon = Point(beaconX, beaconY))
            }
        }

        val range = location.manhattan(beacon)

        fun covers(): Set<Point> {
            fun iter(coveredPoints: Collection<Point>, workingSet: Collection<Point>, distance: Int = 0): Collection<Point> {
                if (distance >= range) return coveredPoints
                return workingSet
                    .flatMap { it.neighbors }
                    .toSet()
                    .let { neighbors -> iter(coveredPoints + neighbors, neighbors, distance + 1) }
            }

            return iter(setOf(this.location), setOf(this.location)).toSet()
        }

    }
}