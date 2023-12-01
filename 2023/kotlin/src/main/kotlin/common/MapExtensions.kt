package common

typealias MapBasedGrid<T> = Map<Point, T>

val Collection<Point>.min: Point
    get() = Point(this.minOf { it.x }, this.minOf { it.y })
val Collection<Point>.max: Point
    get() = Point(this.maxOf { it.x }, this.maxOf { it.y })

val <T> MapBasedGrid<T>.min: Point
    get() = this.keys.min
val <T> MapBasedGrid<T>.max: Point
    get() = this.keys.max

fun <T> MapBasedGrid<T>.get(x: Int, y: Int) = get(Point(x, y))

val <T> MapBasedGrid<T>.visual: String
    get() {
        val min = keys.min
        val max = keys.max
        return (min.y..max.y).flatMap { y ->
            (min.x..max.x).map { x ->
                val p = Point(x, y)
                fun addNewLineOnLineEnd(string: String) = when (x) {
                    max.x -> string + "\n"
                    else -> string
                }
                addNewLineOnLineEnd (
                    when (this.contains(p)) {
                        true -> this[p].toString()
                        false -> "-"
                    }
                )
            }
        }.joinToString(separator = "").dropLast(1)
    }