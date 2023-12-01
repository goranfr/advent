package common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GeneratorUtilsTest {

    @Test
    fun `cycled list take 10 has length 10`() {
        assertEquals(10, cycle(listOf(1,2,3)).take(10).count())
    }

    @Test
    fun `cycled list first element is correct`() {
        assertEquals(1, cycle(listOf(1,2,3)).take(8).first())
    }

    @Test
    fun `cycled list last element is correct`() {
        assertEquals(2, cycle(listOf(1,2,3)).take(8).last())
    }
}