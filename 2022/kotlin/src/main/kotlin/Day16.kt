
import common.AdjacencyList
import common.Resource

class Day16(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day16.txt"
    override fun part1() : Int {
        val graph = AdjacencyList<Valve>()
        val valves = Resource.asList(data)
            .map { Valve.of(it) }
            .associate { it.label to graph.createVertex(it)}

        valves.values.forEach {valve ->
            valve.data.connections.forEach {
                graph.add(valve, valves[it]!!)
            }
        }

        val distanceCache =
            valves
                .map { from ->
                    from.key to graph.distances(from.value)
                        // important step to reduce the search space
                        // we don't care about destination rooms that don't do anything
                        .filter {it.key.data.flowRate > 0}
                        .map { it.key.data.label to it.value }
                        .filterNot {from.key == it.first}
                        .toMap() }
                .toMap()

        fun findBestPathValue(initialFrom: String, timeMax: Int = 30): Int {
            fun search(from: String, time: Int = 0, seen: Set<String> = setOf(), total: Int = 0): Int {
                """
                    
                """.trimIndent()
                val remainingTimeToFlow = timeMax - time
                return distanceCache.getValue(from)
                    .asSequence()
                    .filterNot { it.key in seen }
                    .filter { it.value + time + 1 < timeMax}
                    .maxOfOrNull { (next, cost) ->
                        val totalFlowForNext = (remainingTimeToFlow - cost - 1) * valves.getValue(next).data.flowRate
                        search(next, time + cost + 1, seen + next, total + totalFlowForNext )
                    } ?: total
            }
            return search(initialFrom)
        }

        return findBestPathValue("AA")
    }

    companion object {
        private operator fun Map<String, Map<String, Int>>.get(key1: String, key2: String, default: Int = Int.MAX_VALUE): Int =
            get(key1)?.get(key2) ?: default
        private operator fun Map<String, MutableMap<String, Int>>.set(key1: String, key2: String, value: Int) {
            getValue(key1).set(key2, value)
        }
    }

    override fun part2() : Int {
        val data = Resource.asList(data)
        TODO("Not Implemented")
    }

    data class Valve(val label: String, val flowRate: Int, val connections: Set<String>) {
        companion object {
            fun of(string: String): Valve {
                val exp = Regex("""Valve (..) has flow rate=(\d+); tunnels? leads? to valves? (.+)""")
                exp.matchEntire(string)?.let {
                    val (label, flowRate, connectionsString) = it.groupValues.drop(1).take(3)
                    val connections = connectionsString.split(", ").toSet()
                    return Valve(label, flowRate.toInt(), connections)
                }
                throw IllegalStateException(string)
            }
        }
        override fun toString(): String = "Valve(${this.label}, ${this.flowRate}, ${this.connections})"

    }
}