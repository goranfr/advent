import common.Grid
import common.transpose
import org.junit.Assert.assertEquals
import org.junit.Test

class ResourceTest {

    @Test
    fun testGet() {}

    @Test
    fun testTranspose() {
        val grid: Grid<Int> = listOf(
            listOf(1, 2, 3, 4),
            listOf(5, 6, 7, 8),
            listOf(9,10,11,12),
        )
        val expectedGrid: Grid<Int> = listOf(
            listOf(1,5, 9),
            listOf(2,6,10),
            listOf(3,7,11),
            listOf(4,8,12)
        )
        assertEquals(expectedGrid, grid.transpose())
    }
}