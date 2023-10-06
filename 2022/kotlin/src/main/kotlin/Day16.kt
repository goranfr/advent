
import com.github.shiguruikai.combinatoricskt.combinations
import common.AdjacencyList
import common.Resource

typealias Cache<T> = Map<String, Map<String, T>>

class Day16(override val isExample: Boolean = false) : Day {
    override val inputFile: String = "Day16.txt"
    override fun part1() : Int {
        val valves = Resource.asList(data)
            .map { Valve.of(it) }

        val setup = setup(valves)
        return findBestPathValue("AA", setup.valves, setup.cache)
    }

    companion object {
        private operator fun Map<String, Map<String, Int>>.get(key1: String, key2: String, default: Int = Int.MAX_VALUE): Int =
            get(key1)?.get(key2) ?: default
        private operator fun Map<String, MutableMap<String, Int>>.set(key1: String, key2: String, value: Int) {
            getValue(key1).set(key2, value)
        }
    }

    data class SetupContainer(
        val valves: Map<String, Valve>,
        val cache: Cache<Int>
    )

    private fun setup(valves: Collection<Valve>): SetupContainer {
        val graph = AdjacencyList<Valve>()
        val valvesMap = valves.associate { it.label to graph.createVertex(it)}
        valvesMap.values.forEach {valve ->
            valve.data.connections.forEach {
                graph.add(valve, valvesMap[it]!!)
            }
        }
        val cache =
            valvesMap.map { from ->
                from.key to graph.distances(from.value)
                    // important step to reduce the search space
                    // we don't care about destination rooms that don't do anything
                    .filter { (vert, _) -> vert.data.flowRate > 0 }
                    .map { it.key.data.label to it.value }
                    .toMap() }
            .toMap()
        return SetupContainer(valves.filter { it.flowRate > 0 }.associateBy { it.label }, cache)
    }

    private fun findBestPathValue(initialFrom: String, valves: Map<String, Valve>, distances: Cache<Int>, timeMax: Int = 30, alreadySeen: Set<String> = setOf()): Int {
        fun search(from: String, time: Int = 0, seen: Set<String> = alreadySeen, total: Int = 0): Int {

            val remainingTimeToFlow = timeMax - time
            return distances.getValue(from)
                .asSequence()
                .filterNot { it.key in seen }
                .filter { it.value + time + 1 < timeMax}
                .maxOfOrNull { (next, cost) ->
                    val totalFlowForNext = (remainingTimeToFlow - cost - 1) * valves.getValue(next).flowRate
                    search(next, time + cost + 1, seen + next, total + totalFlowForNext )
                } ?: total
        }
        return search(initialFrom)
    }

    private fun searchPaths(
        location: String,
        timeAllowed: Int,
        cheapestPathCosts: Cache<Int>,
        rooms: Map<String, Valve>,
        seen: Set<String> = emptySet(),
        timeTaken: Int = 0,
        totalFlow: Int = 0,
    ): Int = cheapestPathCosts
        .getValue(location)
        .asSequence()
        .filterNot { (nextRoom, _) -> nextRoom in seen }
        .filter { (_, traversalCost) -> timeTaken + traversalCost + 1 < timeAllowed }
        .maxOfOrNull { (nextRoom, traversalCost) ->
            searchPaths(
                nextRoom,
                timeAllowed,
                cheapestPathCosts,
                rooms,
                seen + nextRoom,
                timeTaken + traversalCost + 1,
                totalFlow + ((timeAllowed - timeTaken - traversalCost - 1) * rooms.getValue(nextRoom).flowRate)
            )
        } ?: totalFlow

    private val bestDistances = mapOf<Pair<Valve, Valve>, Int>()

    override fun part2() : Int {
        val valves = Resource.asList(data)
            .map { Valve.of(it) }

        val setup = setup(valves)
        val valveSet = setup.valves.keys.toSet()
        // all combinations of "half-of-the-rooms"
        return valveSet.combinations(valveSet.size / 2)
            .map { it.toSet() }
            .maxOf {
                // assign this half to us, the other half to the elephant
                // the best result for this room distribution is the sum of the most efficient
                // path through each half.
                findBestPathValue("AA", setup.valves, setup.cache, 26, alreadySeen = it) +
                findBestPathValue("AA", setup.valves, setup.cache, 26, valveSet - it)
            }
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