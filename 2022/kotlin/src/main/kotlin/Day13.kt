class Day13(override val example: Boolean = false) : Day {
    override val inputFile: String = "Day13.txt"

    private sealed class Packet: Comparable<Packet> {
        companion object {
            fun of(input: String): Packet {
                return of(
                    input.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                        .asSequence()
                        .filter { it.isNotBlank() }
                        .drop(1)
                        .filter { it != "," }
                        .iterator()
                )
            }

            fun of(input: Iterator<String>): Packet {
                val packets = mutableListOf<Packet>()
                while (input.hasNext()) {
                    when (val symbol = input.next()) {
                        "]" -> return ListPacket(packets)
                        "[" -> packets.add(of(input))
                        else -> packets.add(IntPacket(symbol.toInt()))
                    }
                }
                return ListPacket(packets)
            }
        }
    }

    private class IntPacket(val value: Int): Packet() {
        fun asList(): ListPacket = ListPacket(listOf(this))
        override fun compareTo(other: Packet): Int {
            return when (other) {
                is IntPacket -> value.compareTo(other.value)
                is ListPacket -> asList().compareTo(other)
            }
        }

        override fun toString(): String {
            return "$value"
        }

        override fun equals(other: Any?): Boolean {
            return when (other) {
                is IntPacket -> value == other.value
                else -> false
            }

        }
    }

    private class ListPacket(val packets: List<Packet>): Packet() {
        override fun compareTo(other: Packet): Int {
            return when (other) {
                is IntPacket -> compareTo(other.asList())
                is ListPacket -> packets.zip(other.packets)
                    .map { it.first.compareTo(it.second) }
                    .firstOrNull { it != 0} ?: packets.size.compareTo(other.packets.size)
            }
        }
        override fun toString(): String {
            return packets.joinToString(",", "[", "]" )
        }
        override fun equals(other: Any?): Boolean {
            return when (other) {
                is ListPacket -> packets == other.packets
                else -> false
            }
        }
    }

    override fun part1() : Int {
        return Resource.asSequence(data())
            .filter { it.isNotBlank() }
            .map { Packet.of(it) }
            .chunked(2)
            .mapIndexed { index, it -> if (it.first() < it.last()) index + 1 else 0 }
            .sum()
    }

    override fun part2(): Int {
        val dividerPackets = listOf(Packet.of("[[2]]"), Packet.of("[[6]]"))

        return (Resource.asSequence(data())
            .filter { it.isNotBlank() }
            .map { Packet.of(it) } + dividerPackets)
            .sorted()
            .mapIndexed { index, it -> IndexedValue(index + 1, it) }
            .filter { it.value in dividerPackets }
            .map { it.index }
            .reduce(Int::times)
    }
}