package common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestMapExtensions {
    @Nested
    inner class min {
        @Test
        fun `min of empty map throws NoSuchElementException`() {
            val map: MapBasedGrid<Char> = mapOf()
            assertThrows<NoSuchElementException> { map.min }
        }

        @Test
        fun `min of simple map is min single point`() {
            val map: MapBasedGrid<Char> = mapOf(
                Point(0, 0) to '.',
                Point(1, 1) to '-',
                Point(2, 2) to '#',
            )
            assertEquals(Point(0, 0), map.min)
        }

        @Test
        fun `min of map is min x and min y`() {
            val map: MapBasedGrid<Char> = mapOf(
                Point(3, 0) to '.',
                Point(1, 1) to '-',
                Point(2, 2) to '#',
            )
            assertEquals(Point(1, 0), map.min)
        }

    }
    @Nested
    inner class max {
        @Test
        fun `max of empty map throws NoSuchElementException`() {
            val map: MapBasedGrid<Char> = mapOf()
            assertThrows<NoSuchElementException> { map.max }
        }

        @Test
        fun `max of simple map is max single point`() {
            val map: MapBasedGrid<Char> = mapOf(
                Point(0, 0) to '.',
                Point(1, 1) to '-',
                Point(2, 2) to '#',
            )
            assertEquals(Point(2, 2), map.max)
        }

        @Test
        fun `max of map is max x and max y`() {
            val map: MapBasedGrid<Char> = mapOf(
                Point(3, 0) to '.',
                Point(1, 1) to '-',
                Point(2, 2) to '#',
            )
            assertEquals(Point(3, 2), map.max)
        }
    }

    @Nested
    inner class visual {
        private val exampleMap: MapBasedGrid<Char> = mapOf(
            Point(1,3) to 'i',
            Point(4,2) to '0',
            Point(2,1) to 'x',
            Point(0,0) to '@',
            Point(1,0) to '@',
            Point(2,0) to '@',
            Point(3,0) to '@',
            Point(4,0) to '@',
        )

        @Test
        fun `toString has correct number of lines`() {
            val lines = exampleMap.visual.split("\n")
            assertEquals(4, lines.size)
        }

        @Test
        fun `toString has correct width`() {
            val firstLine = exampleMap.visual.split("\n").first()
            assertEquals(5, firstLine.length)
        }

        @Test
        fun `first line is correct`() {
            val firstLine = exampleMap.visual.split("\n").first()
            assertEquals("@@@@@", firstLine)
        }

        @Test
        fun `last line is correct`() {
            val lastLine = exampleMap.visual.split("\n").last()
            assertEquals("-i---", lastLine)
        }

    }
}