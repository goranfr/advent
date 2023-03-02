import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random
import Day09.Point
import kotlin.math.absoluteValue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day09Test {
    private val day = Day09()

    @Nested
    inner class `Tail moves` {
        @Test
        fun `up when head moves two up`() {
            val head = day.Point(0, 2)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(0, newTail.x)
            Assertions.assertEquals(1, newTail.y)
        }

        @Test
        fun `down when head moves two down`() {
            val head = day.Point(0, -2)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(0, newTail.x)
            Assertions.assertEquals(-1, newTail.y)
        }

        @Test
        fun `left when head moves two left`() {
            val head = day.Point(-2, 0)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(-1, newTail.x)
            Assertions.assertEquals(0, newTail.y)
        }

        @Test
        fun `right when head moves two right`() {
            val head = day.Point(2, 0)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(1, newTail.x)
            Assertions.assertEquals(0, newTail.y)
        }

        @Test
        fun `nowhere when head moves one left one down`() {
            val head = day.Point(-1, -1)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(0, newTail.x)
            Assertions.assertEquals(0, newTail.y)
        }

        @Test
        fun `down left when head moves two down one left`() {
            val head = day.Point(-1, -2)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(-1, newTail.x)
            Assertions.assertEquals(-1, newTail.y)
        }

        @Test
        fun `up right when head moves two right one up`() {
            val head = day.Point(2, 1)
            val tail = day.Point(0, 0)
            val newTail = tail.follow(head)
            Assertions.assertEquals(1, newTail.x)
            Assertions.assertEquals(1, newTail.y)
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `Point isAdjacent` {

        private fun generateOverlappingPoints(): List<Arguments> {
            return (origin().take(1) + pointSequence().take(15)).map { Arguments.of(it, it) }.toList()
        }


        @ParameterizedTest
        @MethodSource("generateOverlappingPoints")
        fun `true if overlapping`(p1: Point, p2: Point) {
            Assertions.assertTrue(p1.isAdjacent(p2))
        }

        private fun orthogonalsOf(p: Point): Sequence<Point> {
            return sequenceOf(
                day.Point(p.x, p.y + 1),
                day.Point(p.x, p.y - 1),
                day.Point(p.x + 1, p.y),
                day.Point(p.x - 1, p.y),
            )
        }
        private fun orthogonalPoints(): List<Arguments> {
            return (origin().take(1) + pointSequence().take(15)).flatMap { p ->
                orthogonalsOf(p).map {
                    Pair(p, it)
                }
            }.map {Arguments.of(it.first, it.second)}.toList()
        }

        @ParameterizedTest
        @MethodSource("orthogonalPoints")
        fun `true if orthogonally connected`(p1: Point, p2: Point) {
            Assertions.assertTrue(p1.isAdjacent(p2))
        }

        private fun spacedOutPoints(): List<Arguments> {
            return pointPairSequence().filter {
                (it.first.x - it.second.x).absoluteValue >= 2 || (it.first.y - it.second.y).absoluteValue >= 2
            }.map {
                Arguments.of(it.first, it.second)
            }.take(16).toList()
        }

        @ParameterizedTest
        @MethodSource("spacedOutPoints")
        fun `false if distance more than or equal to 2 in any direction`(p1: Point, p2: Point) {
            Assertions.assertFalse(p1.isAdjacent(p2))
        }

        private fun diagonalPoints(): List<Arguments> {
            val day = Day09()
            val points = listOf(
                Pair(day.Point(0, 0), day.Point(1, 1)),
                Pair(day.Point(0, 0), day.Point(-1, -1)),
                Pair(day.Point(0, 0), day.Point(1, -1)),
                Pair(day.Point(0, 0), day.Point(-1, 1)),
                Pair(day.Point(-2, -2), (day.Point(-3, -3))),
                Pair(day.Point(-2, -2), (day.Point(-3, -1))),
                Pair(day.Point(-2, -2), (day.Point(-1, -3))),
                Pair(day.Point(-2, -2), (day.Point(-1, -1)))
            )
            return points.map { Arguments.of(it.first, it.second) }
        }

        @ParameterizedTest
        @MethodSource("diagonalPoints")
        fun `true if diagonally connected`(p1: Point, p2: Point) {
            Assertions.assertTrue(p1.isAdjacent(p2))
        }

        private fun origin(): Sequence<Point> = generateSequence { day.Point(0, 0) }

        private fun point(): Point {
            val x = Random.nextInt(-100, 100)
            val y = Random.nextInt(-100, 100)
            return day.Point(x, y)
        }

        private fun pointSequence(): Sequence<Point> {
            return generateSequence { point() }
        }

        private fun pointPairSequence(): Sequence<Pair<Point, Point>> {
            return pointSequence().zipWithNext()
        }

        private fun generateNonAdjacentPoints(): List<Arguments> {
            return List(15) {
                val x = Random.nextInt(-100, 100)
                val y = Random.nextInt(-100, 100)
                Arguments.of(day.Point(x, y), day.Point(x, y))
            }
        }

    }

    @Nested
    inner class Moves {

        @Test
        fun `4 up`() {
            val head = day.Point(0, 0)
            val tail = day.Point(0, 0)
            val grid = day.Grid(setOf(head), head, tail)
            val gridAfterMove = grid.move(Pair(Day09.Companion.Direction.UP, 4))
            Assertions.assertEquals(day.Point(0, 4), gridAfterMove.head)
            Assertions.assertEquals(day.Point(0, 3), gridAfterMove.tail)

        }

        @Test
        fun `4 down`() {
            val head = day.Point(0, 0)
            val tail = day.Point(0, 0)
            val grid = day.Grid(setOf(head), head, tail)
            val gridAfterMove = grid.move(Pair(Day09.Companion.Direction.DOWN, 4))
            Assertions.assertEquals(day.Point(0, -4), gridAfterMove.head)
            Assertions.assertEquals(day.Point(0, -3), gridAfterMove.tail)

        }

        @Test
        fun `4 left`() {
            val head = day.Point(0, 0)
            val tail = day.Point(0, 0)
            val grid = day.Grid(setOf(head), head, tail)
            val gridAfterMove = grid.move(Pair(Day09.Companion.Direction.LEFT, 4))
            Assertions.assertEquals(day.Point(-4, 0), gridAfterMove.head)
            Assertions.assertEquals(day.Point(-3, 0), gridAfterMove.tail)

        }


        @Test
        fun `4 right`() {
            val head = day.Point(0, 0)
            val tail = day.Point(0, 0)
            val grid = day.Grid(setOf(head), head, tail)
            val gridAfterMove = grid.move(Pair(Day09.Companion.Direction.RIGHT, 4))
            Assertions.assertEquals(day.Point(4, 0), gridAfterMove.head)
            Assertions.assertEquals(day.Point(3, 0), gridAfterMove.tail)

        }

        @Test
        fun `2 down, 2 right`() {
            val head = day.Point(0, 0)
            val tail = day.Point(0, 0)
            val grid = day.Grid(setOf(head), head, tail)
            val gridAfterFirstMove = grid.move(Pair(Day09.Companion.Direction.DOWN, 2))
            val gridAfterSecondMove = gridAfterFirstMove.move(Pair(Day09.Companion.Direction.RIGHT, 2))
            Assertions.assertEquals(day.Point(2, -2), gridAfterSecondMove.head)
        }

        @Test
        fun `6 down, 6 right`() {
            val head = day.Point(0, 0)
            val tail = day.Point(0, 0)
            val grid = day.Grid(setOf(head), head, tail)
            val gridAfterFirstMove = grid.move(Pair(Day09.Companion.Direction.DOWN, 6))
            val gridAfterSecondMove = gridAfterFirstMove.move(Pair(Day09.Companion.Direction.RIGHT, 6))
            Assertions.assertEquals(day.Point(6, -6), gridAfterSecondMove.head)
            Assertions.assertEquals(day.Point(5, -6), gridAfterSecondMove.tail)
        }

        @Test
        fun `6 down, 6 right using fold operation`() {
            val moves = listOf(Pair(Day09.Companion.Direction.DOWN, 6),
                               Pair(Day09.Companion.Direction.RIGHT, 6))
            val grid = day.applyMoves(moves)
            Assertions.assertEquals(day.Point(6, -6), grid.head)
            Assertions.assertEquals(day.Point(5, -6), grid.tail)

        }

    }

    @Nested
    inner class Part1 {
        @Test
        fun `matches example`() {
            val answer = Day09(true).part1()
            Assertions.assertEquals(13, answer)
        }

        @Test
        fun `matches input`() {
            val answer = Day09().part1()
            Assertions.assertEquals(6384, answer)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `matches example`() {
            val answer = Day09(true).part2()
            Assertions.assertEquals(-1, answer)
        }

        @Test
        fun `matches input`() {
            val answer = Day09().part2()
            Assertions.assertEquals(-1, answer)
        }
    }
}